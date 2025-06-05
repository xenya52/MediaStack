package com.MediaStack.MediaStack.repository;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepository extends JpaRepository<MediaFileModel, Long> {

}
