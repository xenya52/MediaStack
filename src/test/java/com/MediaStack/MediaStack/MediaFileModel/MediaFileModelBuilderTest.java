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

        builder.setMediaFileId(12345L);
        builder.setMediaFileName("Sample Media Video");
        builder.setMediaFileType(MediaFileTypeEnum.VIDEO_MP4);
        builder.setMediaFileUploadDate(uploadDate);
        builder.setMediaFilePath("exampleName/mp4");

        MediaFileModel mediaFile = builder.getResult();

        assert mediaFile.getId().equals(12345L);
        assert mediaFile.getName().equals("Sample Media Video");
        assert mediaFile.getType().equals(MediaFileTypeEnum.VIDEO_MP4);
        assert mediaFile.getUploadDate().equals(uploadDate);
        assert mediaFile.getPath().equals("exampleName/mp4");
    }
}
