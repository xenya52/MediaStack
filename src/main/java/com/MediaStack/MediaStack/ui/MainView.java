package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.entity.model.director.Director;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.service.MediaFileService;
import com.MediaStack.MediaStack.service.MediaFileStorageService;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

@Route("")
public class MainView extends VerticalLayout {

    private final MediaFileService mediaService;
    private final MediaFileStorageService mediaFileStorageService;
    private final Grid<MediaFileModel> grid = new Grid<>(MediaFileModel.class);

    @Autowired
    public MainView(MediaFileService mediaService) {
        this.mediaService = mediaService;
        this.mediaFileStorageService = new MediaFileStorageService(mediaService);

        add(new H1("MediaStack"));

        setupColoringButton();
        setupExportAndUploadRow();
        setupGrid();

        refreshGrid();
    }

    public void setupColoringButton() {
        Button toggleButton = new Button("Toggle theme variant", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        add(toggleButton);
    }

    private void setupExportAndUploadRow() {
        String defaultExportPath = System.getProperty("user.home") + "/Downloads/media-stack-export.db";
        final String[] exportPath = { defaultExportPath };

        // Upload
        MemoryBuffer buffer = new MemoryBuffer();
        Director mediaDirector = new Director();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/*", "video/*", "application/pdf");
        upload.addSucceededListener(event -> {
            try {
                Path uploadDir = Paths.get("uploads");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                Path filePath = uploadDir.resolve(event.getFileName());
                Files.copy(buffer.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                MediaFileModel mediaFile;
                String mimeType = event.getMIMEType();
                if (mimeType.startsWith("image/")) {
                    mediaFile = mediaDirector.constructImageFileModel(event.getFileName(), filePath.toString());
                } else if (mimeType.startsWith("video/")) {
                    mediaFile = mediaDirector.constructVideoFileModel(event.getFileName(), filePath.toString());
                } else if (mimeType.equals("application/pdf")) {
                    mediaFile = mediaDirector.constructPdfFileModel(event.getFileName(), filePath.toString());
                } else {
                    throw new IllegalArgumentException("Unsupported file type: " + mimeType);
                }
                mediaService.createMediaFile(mediaFile);
                Notification.show("File uploaded!");
                refreshGrid();
            } catch (IOException | IllegalArgumentException e) {
                Notification.show("Upload failed: ");
            }
        });

        // Export Button
        Button exportButton = new Button("Export Database", event -> {
            boolean success = mediaFileStorageService.exportAllMediaFilesToFolder(exportPath[0]);
            if (success) {
                Notification.show("Database exported to: " + exportPath[0]);
            } else {
                Notification.show("Export failed");
            }
        });
        exportButton.setWidth("180px");

        // Change Path Button and TextField
        Button changePathButton = new Button("Change Path ");
        changePathButton.setWidth("180px");

        TextField pathField = new TextField();
        pathField.setWidth("400px");
        pathField.setValue(exportPath[0]);
        pathField.setVisible(false);

        changePathButton.addClickListener(e -> {
            pathField.setValue(exportPath[0]);
            changePathButton.setVisible(false);
            pathField.setVisible(true);
            pathField.focus();
        });

        pathField.addBlurListener(e -> {
            exportPath[0] = pathField.getValue();
            changePathButton.setText("Change Path");
            pathField.setVisible(false);
            changePathButton.setVisible(true);
        });
        pathField.addKeyPressListener(key -> {
            if ("Enter".equals(key.getKey())) {
                exportPath[0] = pathField.getValue();
                changePathButton.setText("Change Path");
                pathField.setVisible(false);
                changePathButton.setVisible(true);
            }
        });

        // vertical layout for the buttons
        VerticalLayout buttonColumn = new VerticalLayout(changePathButton, pathField, exportButton);
        buttonColumn.setPadding(false);
        buttonColumn.setSpacing(true);
        buttonColumn.setAlignItems(FlexComponent.Alignment.STRETCH);

        // horizontal layout and center its content
        HorizontalLayout row = new HorizontalLayout(upload, buttonColumn);
        row.setWidthFull();
        row.setAlignItems(FlexComponent.Alignment.CENTER);
        add(row);
    }

    private void setupGrid() {
        grid.setColumns("id", "name", "fileType", "uploadDate");
        grid.addComponentColumn(mediaFile -> {
            Button delete = new Button("Delete", e -> {
                mediaService.deleteMediaFileById(mediaFile.getId());
                refreshGrid();
            });
            return delete;
        }).setHeader("Actions");
        add(grid);
    }

    private void refreshGrid() {
        Collection<MediaFileModel> files = mediaService.getAllMediaFiles();
        grid.setItems(files);
    }
}