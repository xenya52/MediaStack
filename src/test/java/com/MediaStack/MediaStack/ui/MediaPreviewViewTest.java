package com.MediaStack.MediaStack.ui;

import com.MediaStack.MediaStack.ui.MediaPreviewView;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MediaPreviewViewTest {

    @Test
    void imagePreviewComponentIsPresent() {
        MediaPreviewView view = new MediaPreviewView();
        Image img = view.getImageComponent();
        assertNotNull(img);
        assertTrue(img.getSrc().endsWith(".jpg") || img.getSrc().endsWith(".png"));
    }

    @Test
    void videoPreviewComponentIsPresent() {
        MediaPreviewView view = new MediaPreviewView();
        VideoJsPlayer video = view.getVideoComponent();
        assertNotNull(video);
        assertTrue(video.getSource().endsWith(".mp4"));
    }

    @Test
    void pdfPreviewComponentIsPresent() {
        MediaPreviewView view = new MediaPreviewView();
        PdfViewer pdf = view.getPdfComponent();
        assertNotNull(pdf);
        assertTrue(pdf.getSrc().endsWith(".pdf"));
    }
}