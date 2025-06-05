package com.MediaStack.MediaStack.entity.model.builders;

import java.time.LocalDateTime;

import java.lang.Long;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;

public class MediaFileModelBuilder {
    private Long id;
    private String name;
    private MediaFileTypeEnum fileType;
    private LocalDateTime uploadDate;
    private String path;

    public MediaFileModelBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MediaFileModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MediaFileModelBuilder setFileType(MediaFileTypeEnum fileType) {
        this.fileType = fileType;
        return this;
    }

    public MediaFileModelBuilder setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public MediaFileModelBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public MediaFileModel build() {
        return new MediaFileModel(id, name, fileType, uploadDate, path);
    }
}
