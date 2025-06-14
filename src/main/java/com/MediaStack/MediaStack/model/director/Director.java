package com.MediaStack.MediaStack.model.director;

import java.time.LocalDateTime;

import com.MediaStack.MediaStack.model.builders.MediaFileModelBuilder;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;

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
        MediaFileModelBuilder builder = new MediaFileModelBuilder();

        builder.setName(name);
        builder.setFileType(MediaFileTypeEnum.VIDEO_MP4);
        builder.setUploadDate(LocalDateTime.now());
        builder.setPath(path);

        return builder.getResult();
    }

    /**
     * Constructs an image file model with the given parameters.
     *
     * @param name      the name of the media file
     * @param path      the path to the media file
     * @return a MediaFileModel representing an image file
     */
    public MediaFileModel constructImageFileModel(String name, String path) {
        MediaFileModelBuilder builder = new MediaFileModelBuilder();

        builder.setName(name);
        builder.setFileType(MediaFileTypeEnum.IMAGE_JPG);
        builder.setUploadDate(LocalDateTime.now());
        builder.setPath(path);

        return builder.getResult();
    }

    /**
     * Constructs a PDF file model with the given parameters.
     *
     * @param name      the name of the media file
     * @param path      the path to the media file
     * @return a MediaFileModel representing a PDF file
     */
    public MediaFileModel constructPdfFileModel(String name, String path) {
        MediaFileModelBuilder builder = new MediaFileModelBuilder();

        builder.setName(name);
        builder.setFileType(MediaFileTypeEnum.PDF);
        builder.setUploadDate(LocalDateTime.now());
        builder.setPath(path);

        return builder.getResult();
    }
}
