package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.ui.components.buttons.MediaPreviewButton;
import com.vaadin.flow.component.html.Image;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MediaPreviewButtonTest {

    @Test
    void testImagePreviewComponentIsPresent() {
        MediaPreviewButton view = new MediaPreviewButton();
        Image testImage = new Image("test.jpg", "Test Image");
        view.setImageComponent(testImage);

        Image img = view.getImageComponent();

        System.out.println("Image Source: " + img.getSrc());

        assertNotNull(img);
        assertTrue(img.getSrc().endsWith(".jpg"));
    }

    @Test
    void testVideoPreviewComponentIsPresent() {
        MediaPreviewButton view = new MediaPreviewButton();
        VideoPlayer testVideo = new VideoPlayer("test.mp4");
        view.setVideoComponent(testVideo);

        VideoPlayer video = view.getVideoComponent();

        System.out.println("Video Source: " + video.getSrc());

        assertNotNull(video);
        assertTrue(video.getSrc().endsWith(".mp4"));
    }

    @Test
    void testPdfPreviewComponentIsPresent() {
        MediaPreviewButton view = new MediaPreviewButton();
        PdfView testPdf = new PdfView("test.pdf");
        view.setPdfComponent(testPdf);

        PdfView pdf = view.getPdfComponent();

        System.out.println("PDF Source: " + pdf.getSrc());

        assertNotNull(pdf);
    }
}