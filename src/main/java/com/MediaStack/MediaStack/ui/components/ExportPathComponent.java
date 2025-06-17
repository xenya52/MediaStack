package com.MediaStack.MediaStack.ui.components;

import com.MediaStack.MediaStack.service.MediaFileStorageService;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import org.springframework.beans.factory.annotation.Autowired;

public class ExportPathComponent extends VerticalLayout {

    String defaultExportPath = System.getProperty("user.home") + "/Downloads/media-stack-export.db";
    final String[] exportPath = { defaultExportPath };

    @Autowired
    public ExportPathComponent(MediaFileStorageService mediaFileStorageService) {
        Button exportButton = new Button("Export Database", event -> {
            boolean success = mediaFileStorageService.exportAllMediaFilesToFolder(exportPath[0]);
            if (success) {
                Notification.show("Database exported to: " + exportPath[0]);
            } else {
                Notification.show("Export failed");
            }
        });
        exportButton.setWidth("180px");

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

        VerticalLayout buttonColumn = new VerticalLayout(changePathButton, pathField, exportButton);
        buttonColumn.setPadding(false);
        buttonColumn.setSpacing(true);
        buttonColumn.setAlignItems(FlexComponent.Alignment.STRETCH);
        add(buttonColumn);
    }
}
