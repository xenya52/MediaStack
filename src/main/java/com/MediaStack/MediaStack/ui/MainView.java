package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.model.director.Director;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import com.MediaStack.MediaStack.service.MediaFileService;
import com.MediaStack.MediaStack.service.MediaFileStorageService;
import com.MediaStack.MediaStack.ui.components.ExportPathComponent;
import com.MediaStack.MediaStack.ui.components.MediaUploadComponent;

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
import java.util.logging.Logger;

@Route("")
public class MainView extends VerticalLayout {

    private final MediaFileService mediaService;
    private final MediaFileStorageService mediaFileStorageService;
    private final Grid<MediaFileModel> grid = new Grid<>(MediaFileModel.class);
    private static final Logger logger = Logger.getLogger(MainView.class.getName());

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

        grid.addComponentColumn(mediaFile -> {
            MediaPreviewButton previewButton = new MediaPreviewButton(
                    mediaFile.getType(),
                    mediaFile.getPath()
            );
            return previewButton.getPreviewButton();
        }).setHeader("Preview");

        add(grid);
    }

    private void refreshGrid() {
        logger.info("refreshGrid called");
        Collection<MediaFileModel> files = mediaService.getAllMediaFiles();
        logger.info("Files fetched from mediaService: " + (files != null ? files.size() : "null"));
        if (files != null) {
            for (MediaFileModel file : files) {
                logger.info("File: id=" + file.getId() + ", name=" + file.getName() + ", type=" + file.getType());
            }
        } else {
            logger.warning("mediaService.getAllMediaFiles() returned null");
        }
        grid.setItems(files);
        logger.info("Grid items set");
    }

    private MediaFileTypeEnum getFileType(String mimeType) {
        switch (mimeType) {
            case "image/jpg":
                return MediaFileTypeEnum.IMAGE_JPG;
            case "video/mp4":
                return MediaFileTypeEnum.VIDEO_MP4;
            case "application/pdf":
                return MediaFileTypeEnum.PDF;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + mimeType);
        }
    }

    // Explanation:
    // The refreshGrid method is declared as 'void' because its purpose is to update the UI grid with the latest data.
    // It does not need to return any value; it only performs an action (side effect).
    // This is standard for UI update methods in Java and Vaadin.
}