package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
public class MediaPreviewButton {

    private static final Logger logger = Logger.getLogger(MediaPreviewButton.class.getName());

    private Image imageComponent;
    private VideoPlayer videoComponent;
    private PdfView pdfComponent;
    private Button previewButton;

    public MediaPreviewButton(MediaFileTypeEnum fileType, String path) {
        logger.info("Initializing MediaPreviewButton with fileType=" + fileType + ", path=" + path);
        previewButton = new Button("Preview", event -> showPreview());
        if (fileType == null) {
            logger.warning("fileType is null");
            return;
        }
        if (path == null || path.isEmpty()) {
            logger.warning("path is null or empty");
            return;
        }
        switch (fileType) {
            case IMAGE_JPG:
                Image image = new Image("/" + path, "Preview Image");
                image.setMaxWidth("400px");
                setImageComponent(image);
                logger.info("Image component created for path: /" + path);
                break;
            case VIDEO_MP4:
                setVideoComponent(new VideoPlayer("/" + path));
                logger.info("Video component created for path: /" + path);
                break;
            case PDF:
                setPdfComponent(new PdfView("/" + path));
                logger.info("PDF component created for path: /" + path);
                break;
            default:
                logger.warning("Unsupported fileType: " + fileType);
        }
    }

    private void showPreview() {
        logger.info("showPreview called");
        Component preview = null;
        if (imageComponent != null) {
            preview = imageComponent;
            logger.info("Showing image preview");
        } else if (videoComponent != null) {
            preview = videoComponent;
            logger.info("Showing video preview");
        } else if (pdfComponent != null) {
            preview = pdfComponent;
            logger.info("Showing PDF preview");
        } else {
            logger.warning("No preview component available");
        }
        if (preview != null) {
            Dialog dialog = new Dialog(preview);
            dialog.open();
            logger.info("Dialog opened");
        }
    }

    public Button getPreviewButton() {
        logger.info("getPreviewButton called");
        return previewButton;
    }
}
