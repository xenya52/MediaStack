package com.MediaStack.MediaStack.model.builders;

import java.time.LocalDateTime;

import java.lang.Long;

import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import lombok.Setter;

@Setter
public class MediaFileModelBuilder {
    private Long id;
    private String name;
    private MediaFileTypeEnum fileType;
    private LocalDateTime uploadDate;
    private String path;

    public MediaFileModel getResult() {
        return new MediaFileModel(id, name, fileType, uploadDate, path);
    }
}
