package com.MediaStack.MediaStack.MediaFileModel;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;
import com.MediaStack.MediaStack.entity.model.director.Director;

public class MediaFileModelDirectorTest {

    @Test
    void testConstructVideoFileModel() {
        Director mediaFileDirector = new Director();
        MediaFileModel mediaFile = mediaFileDirector.constructVideoFileModel( "Sample Media Video", "exampleName/mp4");

        assert mediaFile.getFileType().equals(MediaFileTypeEnum.VIDEO_MP4);
    }

    @Test
    void testConstructImageFileModel() {
        Director mediaFileDirector = new Director();
        MediaFileModel mediaFile = mediaFileDirector.constructImageFileModel( "Sample Media Image", "exampleName/jpg");

        assert mediaFile.getFileType().equals(MediaFileTypeEnum.IMAGE_JPG);
    }

    @Test
    void testConstructPdfFileModel() {
        Director mediaFileDirector = new Director();
        MediaFileModel mediaFile = mediaFileDirector.constructPdfFileModel("Sample Media PDF", "exampleName/pdf");

        assert mediaFile.getFileType().equals(MediaFileTypeEnum.PDF);
    }
}