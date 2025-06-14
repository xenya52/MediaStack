package com.MediaStack.MediaStack.MediaFileModel;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import com.MediaStack.MediaStack.model.builders.MediaFileModelBuilder;

public class MediaFileModelBuilderTest {

    @Test
    void testMediaFileModelBuilder() {
        MediaFileModelBuilder builder = new MediaFileModelBuilder();
        LocalDateTime uploadDate = LocalDateTime.of(2025, 5, 30, 0, 0);

        builder.setId(12345L);
        builder.setName("Sample Media Video");
        builder.setFileType(MediaFileTypeEnum.VIDEO_MP4);
        builder.setUploadDate(uploadDate);
        builder.setPath("exampleName/mp4");

        MediaFileModel mediaFile = builder.getResult();

        assert mediaFile.getId().equals(12345L);
        assert mediaFile.getName().equals("Sample Media Video");
        assert mediaFile.getFileType().equals(MediaFileTypeEnum.VIDEO_MP4);
        assert mediaFile.getUploadDate().equals(uploadDate);
        assert mediaFile.getPath().equals("exampleName/mp4");
    }
}
