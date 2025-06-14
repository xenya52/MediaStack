package com.MediaStack.MediaStack.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediaStack.MediaStack.repository.MediaFileRepository;
import com.MediaStack.MediaStack.model.director.Director;
import com.MediaStack.MediaStack.model.mediaFile.MediaFileModel;

@Service
public class MediaFileService {

    @Autowired
    private Director director;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    private static final Logger logger = Logger.getLogger(MediaFileService.class.getName());

    public void createMediaFile(MediaFileModel mediaFile) {

        isValidMediaFile(mediaFile);

        logger.info("Preparing to save MediaFileModel: name=" + mediaFile.getName() + ", path=" + mediaFile.getPath() + ", type=" + mediaFile.getFileType());

        MediaFileModel entityToSave = switch (mediaFile.getFileType()) {
            case MediaFileTypeEnum.IMAGE_JPG -> director.constructImageFileModel(
                    mediaFile.getName(),
                    mediaFile.getPath()
            );
            case MediaFileTypeEnum.VIDEO_MP4 -> director.constructVideoFileModel(
                    mediaFile.getName(),
                    mediaFile.getPath()
            );
            case MediaFileTypeEnum.PDF -> director.constructPdfFileModel(
                    mediaFile.getName(),
                    mediaFile.getPath()
            );
            default -> throw new IllegalArgumentException("Unsupported file type: " + mediaFile.getFileType());
        };

        logger.info("Constructed entity: name=" + entityToSave.getName() + ", path=" + entityToSave.getPath() + ", type=" + entityToSave.getFileType() + ", id=" + entityToSave.getId());

        try {
            MediaFileModel saved = mediaFileRepository.save(entityToSave);
            logger.info("Saved entity: name=" + saved.getName() + ", path=" + saved.getPath() + ", type=" + saved.getFileType() + ", id=" + saved.getId());
        } catch (Exception e) {
            logger.severe("Error saving MediaFileModel: " + e.getMessage());
            throw e;
        }
    }

    public MediaFileModel getMediaFileById(Long id) {
        return mediaFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Media file not found with id: " + id));
    }

    public List<MediaFileModel> getAllMediaFiles() {
        logger.info("Fetching all media files from repository...");
        List<MediaFileModel> files = mediaFileRepository.findAll();
        logger.info("Repository returned " + (files != null ? files.size() : "null") + " files");
        if (files != null) {
            for (MediaFileModel file : files) {
                logger.info("Repo File: id=" + file.getId() + ", name=" + file.getName() + ", type=" + file.getFileType());
            }
        }
        return files;
    }

    public void deleteMediaFileById(Long id) {
        if (!mediaFileRepository.existsById(id)) {
            throw new IllegalArgumentException("Media file not found with id: " + id);
        }
        mediaFileRepository.deleteById(id);
    }

    private void isValidMediaFile(MediaFileModel mediaFile) {
        if (mediaFile.getName() == null || mediaFile.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (mediaFile.getPath() == null || mediaFile.getPath().isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        if (mediaFile.getFileType() == null) {
            throw new IllegalArgumentException("File type cannot be null");
        }
    }
}