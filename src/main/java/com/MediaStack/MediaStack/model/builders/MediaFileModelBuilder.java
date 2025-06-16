package com.MediaStack.MediaStack.model.builders;

import java.time.LocalDateTime;

import java.lang.Long;

import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import lombok.Setter;

@Setter
public class MediaFileModelBuilder {
    private Long mediaFileId;
    private String mediaFileName;
    private MediaFileTypeEnum mediaFileType;
    private LocalDateTime mediaFileUploadDate;
    private String mediaFilePath;

    public MediaFileModel getResult() {
        return new MediaFileModel(mediaFileId, mediaFileName, mediaFileType, mediaFileUploadDate, mediaFilePath);
    }
}
