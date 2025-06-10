package com.MediaStack.MediaStack.ui;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;

@Tag("video")
public class VideoPlayer extends HtmlContainer {

    private static final PropertyDescriptor<String, String> srcDescriptor = PropertyDescriptors.attributeWithDefault("src", "");

    public VideoPlayer() {
        super();
        getElement().setProperty("controls", true);
    }

    public VideoPlayer(String source) {
        setSrc(source);
    }

    public String getSrc() {
        return get(srcDescriptor);
    }

    public void setSrc(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source cannot be null or empty");
        }
        set(srcDescriptor, source);
    }
}
