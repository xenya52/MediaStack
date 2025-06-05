package com.MediaStack.MediaStack.MediaFileModel;

import java.time.LocalDateTime;

import java.lang.Long;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;

public class MediaFileModelTest {

    @Test
    void testMediaFileModelSetterAndGetter() {
        MediaFileModel mediaFile = new MediaFileModel();
        LocalDateTime uploadDate = LocalDateTime.of(2025, 5, 30, 0, 0);

        mediaFile.setId(12345L);
        mediaFile.setName("Sample Media Video");
        mediaFile.setFileType(MediaFileTypeEnum.VIDEO_MP4);
        mediaFile.setUploadDate(uploadDate);
        mediaFile.setPath("exampleName/mp4");

        assert mediaFile.getId().equals(12345L);
        assert mediaFile.getName().equals("Sample Media Video");
        assert mediaFile.getFileType().equals(MediaFileTypeEnum.VIDEO_MP4);
        assert mediaFile.getUploadDate().equals(uploadDate);
        assert mediaFile.getPath().equals("exampleName/mp4");
    }

    @Test
    void testMediaFileModelToString() {
        MediaFileModel mediaFile = new MediaFileModel();
        LocalDateTime uploadDate = LocalDateTime.of(2025, 5, 30, 0, 0);

        mediaFile.setId(12345L);
        mediaFile.setName("Sample Media Video");
        mediaFile.setFileType(MediaFileTypeEnum.VIDEO_MP4);
        mediaFile.setUploadDate(uploadDate);
        mediaFile.setPath("exampleName/mp4");

        String expectedString = "MediaFileModel(id=12345, name=Sample Media Video, fileType=VIDEO_MP4, uploadDate=2025-05-30T00:00, path=exampleName/mp4)";

        assert mediaFile.toString().equals(expectedString);
    }

    @Test
    void testMediaFileModelNameExceptionHandling() {
        MediaFileModel mediaFile = new MediaFileModel();

        try {
            mediaFile.setName(null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Name cannot be null or empty");
        }

        try {
            mediaFile.setName("");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Name cannot be null or empty");
        }
    }

    @Test
    void testMediaFileModelInvalidTypeExceptionHandling() {
        MediaFileModel mediaFile = new MediaFileModel();

        try {
            mediaFile.setFileType(null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("File type cannot be null");
        }
    }

    @Test
    void testMediaFileModelUploadDateInFutureExceptionHandling() {
        MediaFileModel mediaFile = new MediaFileModel();
        LocalDateTime invalidFutureDate = LocalDateTime.of(9999, 12, 31, 0, 0);

        try {
            mediaFile.setUploadDate(invalidFutureDate);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Upload date cannot be in the future");
        }
    }
}