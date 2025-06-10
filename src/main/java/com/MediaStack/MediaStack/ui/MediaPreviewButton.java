package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MediaPreviewButton {

    private Image imageComponent;
    private VideoPlayer videoComponent;
    private PdfView pdfComponent;
    private Button previewButton;

    public MediaPreviewButton(MediaFileTypeEnum fileType, String path) {
        previewButton = new Button("Preview", event -> showPreview());
        if (fileType.equals(MediaFileTypeEnum.IMAGE_JPG)) {
            Image image = new Image("/" + path, "Preview Image");
            image.setMaxWidth("400px");
            setImageComponent(image);
        } else if (fileType.equals(MediaFileTypeEnum.VIDEO_MP4)) {
            setVideoComponent(new VideoPlayer("/" + path));
        } else if (fileType.equals(MediaFileTypeEnum.PDF)) {
            setPdfComponent(new PdfView("/" + path));
        }
    }

    private void showPreview() {
        Component preview = null;
        if (imageComponent != null) {
            preview = imageComponent;
        } else if (videoComponent != null) {
            preview = videoComponent;
        } else if (pdfComponent != null) {
            preview = pdfComponent;
        }
        if (preview != null) {
            Dialog dialog = new Dialog(preview);
            dialog.open();
        }
    }

    public Button getPreviewButton() {
        return previewButton;
    }
}