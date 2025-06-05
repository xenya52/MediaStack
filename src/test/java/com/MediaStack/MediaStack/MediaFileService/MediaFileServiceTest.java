package com.MediaStack.MediaStack.MediaFileService;

import com.MediaStack.MediaStack.entity.model.director.Director;
import com.MediaStack.MediaStack.service.MediaFileService;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.Media;
import java.time.LocalDateTime;

@SpringBootTest
public class MediaFileServiceTest {

    @Autowired
    private MediaFileService mediaFileService;

    @Test
    void testMediaFileServiceCreateMediaFile() {
        Director director = new Director();
        LocalDateTime uploadDate = LocalDateTime.of(2025, 5, 30, 0, 0);
        MediaFileModel mediaFile = director.constructVideoFileModel( "Test Media File", "test/path/to/media.mp4");

        var createdMediaFile = mediaFileService.createMediaFile(mediaFile);

        assert createdMediaFile != null : "Created media file should not be null";
        assert createdMediaFile.getName().equals("Test Media File") : "Media file name should match";
    }

    @Test
    void testMediaFileServiceGetAllMediaFiles() {
        // Insert test data
        Director director = new Director();
        MediaFileModel mediaFile = director.constructVideoFileModel("Test Video", "test/path/video.mp4");
        mediaFileService.createMediaFile(mediaFile);

        var mediaFiles = mediaFileService.getAllMediaFiles();
        assert mediaFiles != null : "Media files should not be null";
        assert !mediaFiles.isEmpty() : "Media files list should not be empty";
    }

    @Test
    void testMediaFileServiceGetMediaFileById() {
        // Insert test data
        Director director = new Director();
        MediaFileModel mediaFile = director.constructVideoFileModel("Test Video", "test/path/video.mp4");
        MediaFileModel savedMediaFile = mediaFileService.createMediaFile(mediaFile);

        MediaFileModel foundMediaFile = mediaFileService.getMediaFileById(savedMediaFile.getId());
        assert foundMediaFile != null : "Media file should not be null";
        assert foundMediaFile.getId().equals(savedMediaFile.getId()) : "Media file ID should match";
    }

    @Test
    void testMediaFileServiceDeleteMediaFileById() {
        // Insert test data
        Director director = new Director();
        MediaFileModel mediaFile = director.constructVideoFileModel("Test Video", "test/path/video.mp4");
        MediaFileModel savedMediaFile = mediaFileService.createMediaFile(mediaFile);

        mediaFileService.deleteMediaFileById(savedMediaFile.getId());

        try {
            mediaFileService.getMediaFileById(savedMediaFile.getId());
            assert false : "Expected exception not thrown";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("Media file not found with id") : "Exception message should indicate not found";
        }
    }
}