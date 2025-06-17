package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.service.MediaFileStorageService;
import com.MediaStack.MediaStack.ui.components.ExportPathComponent;
import com.MediaStack.MediaStack.ui.components.MediaUploadComponent;
import com.MediaStack.MediaStack.ui.components.buttons.MediaPreviewButton;
import com.MediaStack.MediaStack.ui.components.MediaGridComponent;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import com.MediaStack.MediaStack.service.MediaFileService;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;


@Route("")
public class MainView extends VerticalLayout {

    private final MediaFileService mediaService;
    private final MediaFileStorageService mediaFileStorageService;

    private ExportPathComponent export;
    private MediaUploadComponent upload;
    private MediaGridComponent grid;

    @Autowired
    public MainView(MediaFileService mediaService) {
        this.mediaService = mediaService;
        this.upload = new MediaUploadComponent(mediaService);
        this.grid = new MediaGridComponent(mediaService);

        this.mediaFileStorageService = new MediaFileStorageService(mediaService);
        this.export = new ExportPathComponent(mediaFileStorageService);

        add(new H1("MediaStack"));

        setupColoringButton();
        setupExportAndUploadRow();

        grid.setupGrid();
        grid.refreshGrid();
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
        HorizontalLayout row = new HorizontalLayout(upload, export);
        row.setWidthFull();
        row.setAlignItems(FlexComponent.Alignment.CENTER);

        add(row);
    }
}