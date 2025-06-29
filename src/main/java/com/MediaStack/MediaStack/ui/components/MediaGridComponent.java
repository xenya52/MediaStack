package com.MediaStack.MediaStack.ui.components;

import com.MediaStack.MediaStack.ui.MainView;
import com.MediaStack.MediaStack.ui.components.buttons.MediaPreviewButton;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.service.MediaFileService;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.logging.Logger;

public class MediaGridComponent extends VerticalLayout {

    private static final Logger logger = Logger.getLogger(MediaGridComponent.class.getName());
    private Grid<MediaFileModel> grid = new Grid<>(MediaFileModel.class);
    private final MediaFileService mediaService;

    @Autowired
    public MediaGridComponent(MediaFileService mediaService) {
        this.mediaService = mediaService;
        setupGrid();
        add(grid);
    }

    public void refreshGrid() {
        logger.info("refreshGrid called");
        Collection<MediaFileModel> files = mediaService.getAllMediaFiles();
        logger.info("Files fetched from mediaService: " + (files != null ? files.size() : "null"));
        if (files != null) {
            for (MediaFileModel file : files) {
                logger.info("File: id=" + file.getId() + ", name=" + file.getName() + ", type=" + file.getFileType());
            }
        } else {
            logger.warning("mediaService.getAllMediaFiles() returned null");
        }
        grid.setItems(files);
        logger.info("Grid items set");
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
                    mediaFile.getFileType(),
                    mediaFile.getPath()
            );
            return previewButton.getPreviewButton();
        }).setHeader("Preview");
    }
}
