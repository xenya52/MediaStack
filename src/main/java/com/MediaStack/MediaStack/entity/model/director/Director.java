package com.MediaStack.MediaStack.entity.model.director;

import java.time.LocalDateTime;

import java.lang.Long;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;

import org.springframework.stereotype.Component;

@Component
public class Director {
    /**
     * Constructs a video file model with the given parameters.
     *
     * @param name      the name of the media file
     * @param path      the path to the media file
     * @return a MediaFileModel representing a video file
     */
    public MediaFileModel constructVideoFileModel(String name, String path) {
        MediaFileModel mediaFile = new MediaFileModel();
        mediaFile.setName(name);
        mediaFile.setFileType(MediaFileTypeEnum.VIDEO_MP4);
        mediaFile.setUploadDate(LocalDateTime.now());
        mediaFile.setPath(path);
        return mediaFile;
    }

    /**
     * Constructs an image file model with the given parameters.
     *
     * @param name      the name of the media file
     * @param path      the path to the media file
     * @return a MediaFileModel representing an image file
     */
    public MediaFileModel constructImageFileModel(String name, String path) {
        MediaFileModel mediaFile = new MediaFileModel();
        mediaFile.setName(name);
        mediaFile.setFileType(MediaFileTypeEnum.IMAGE_JPG);
        mediaFile.setUploadDate(LocalDateTime.now());
        mediaFile.setPath(path);
        return mediaFile;
    }

    /**
     * Constructs a PDF file model with the given parameters.
     *
     * @param name      the name of the media file
     * @param path      the path to the media file
     * @return a MediaFileModel representing a PDF file
     */
    public MediaFileModel constructPdfFileModel(String name, String path) {
        MediaFileModel mediaFile = new MediaFileModel();
        mediaFile.setName(name);
        mediaFile.setFileType(MediaFileTypeEnum.PDF);
        mediaFile.setUploadDate(LocalDateTime.now());
        mediaFile.setPath(path);
        return mediaFile;
    }
}
