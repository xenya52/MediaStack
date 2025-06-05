package com.MediaStack.MediaStack.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediaStack.MediaStack.repository.MediaFileRepository;
import com.MediaStack.MediaStack.entity.model.director.Director;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;

@Service
public class MediaFileService {

    @Autowired
    private Director director;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    public MediaFileModel createMediaFile(MediaFileModel mediaFile) {
        if (mediaFile.getName() == null || mediaFile.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (mediaFile.getPath() == null || mediaFile.getPath().isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        if (mediaFile.getFileType() == null) {
            throw new IllegalArgumentException("File type cannot be null");
        }
        mediaFile.setUploadDate(LocalDateTime.now());
        return mediaFileRepository.save(mediaFile);
    }

    public MediaFileModel getMediaFileById(Long id) {
        return mediaFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Media file not found with id: " + id));
    }

    public List<MediaFileModel> getAllMediaFiles() {
        return mediaFileRepository.findAll();
    }

    public boolean deleteMediaFileById(Long id) {
        if (!mediaFileRepository.existsById(id)) {
            throw new IllegalArgumentException("Media file not found with id: " + id);
        }
        mediaFileRepository.deleteById(id);
        return true;
    }
}
