package com.MediaStack.MediaStack.ui.components;

import com.MediaStack.MediaStack.model.director.Director;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.service.MediaFileService;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.atmosphere.annotation.AnnotationUtil.logger;

public class MediaUploadComponent extends VerticalLayout {

    String uploadDirName = "uploads";

    @Autowired
    public MediaUploadComponent(MediaFileService mediaService) {

        MemoryBuffer buffer = new MemoryBuffer();
        Director mediaDirector = new Director();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/*", "video/*", "application/pdf");
        upload.addSucceededListener(event -> {

            logger.info("Upload succeeded: " + event.getFileName() + ", MIME type: " + event.getMIMEType());
            try {
                Path uploadDir = Paths.get(uploadDirName);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                    logger.info("Created upload directory: " + uploadDir.toAbsolutePath());
                }
                Path filePath = uploadDir.resolve(event.getFileName());
                logger.info("Saving uploaded file to: " + filePath.toAbsolutePath());
                Files.copy(buffer.getInputStream(), filePath, REPLACE_EXISTING);

                MediaFileModel mediaFile;
                String mimeType = event.getMIMEType();
                logger.info("Determining file type for MIME: " + mimeType);
                if (mimeType.startsWith("image/")) {
                    mediaFile = mediaDirector.constructImageFileModel(event.getFileName(), filePath.toString());
                } else if (mimeType.startsWith("video/")) {
                    mediaFile = mediaDirector.constructVideoFileModel(event.getFileName(), filePath.toString());
                } else if (mimeType.equals("application/pdf")) {
                    mediaFile = mediaDirector.constructPdfFileModel(event.getFileName(), filePath.toString());
                } else {
                    logger.warn("Unsupported file type: " + mimeType);
                    throw new IllegalArgumentException("Unsupported file type: " + mimeType);
                }
                logger.info("Constructed MediaFileModel: name=" + mediaFile.getName() + ", path=" + mediaFile.getPath() + ", type=" + mediaFile.getFileType());
                mediaService.createMediaFile(mediaFile);
                logger.info("MediaFileModel persisted to database: " + mediaFile.getName());
                Notification.show("File uploaded!");
            } catch (IOException | IllegalArgumentException e) {
                Notification.show("Upload failed: " + e.getMessage());
            }
        });
        add(upload);
    }
}