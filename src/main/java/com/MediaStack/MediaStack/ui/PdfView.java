package com.MediaStack.MediaStack.ui;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;

@Tag("object")
public class PdfView extends HtmlContainer {

    public PdfView() {
        super("object");
        // Set default type and dimensions
        getElement().setAttribute("type", "application/pdf");
        getElement().setAttribute("width", "100%");
        getElement().setAttribute("height", "600px");
    }

    public PdfView(String source) {
        this();
        setSrc(source);
    }

    public String getSrc() {
        return getElement().getAttribute("data");
    }

    public void setSrc(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source cannot be null or empty");
        }
        getElement().setAttribute("data", source); // "data" attribute for <object> tag
    }
}