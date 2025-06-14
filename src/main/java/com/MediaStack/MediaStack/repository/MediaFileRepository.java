package com.MediaStack.MediaStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFileModel, Long> {

}