package com.MediaStack.MediaStack.service;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class MediaFileStorageService {

    @Autowired
    private final MediaFileService mediaFileService;

    @Autowired
    public MediaFileStorageService(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    private static final String SQLITE_DB_PATH = "media-stack.db";

    public boolean exportDatabase(String exportPath) {
        try {
            Path source = Paths.get(SQLITE_DB_PATH);
            Path target = Paths.get(exportPath);
            Files.createDirectories(target.getParent());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean exportAllMediaFilesToFolder(String exportFolderPath) {
        try {
            // TODO Remove this line when not debugging
            System.out.println(exportFolderPath);

            List<MediaFileModel> files = mediaFileService.getAllMediaFiles();
            Path exportDir = Paths.get(exportFolderPath);
            if (!Files.exists(exportDir)) {
                Files.createDirectories(exportDir);
            }
            for (MediaFileModel file : files) {
                Path source = Paths.get(file.getPath());
                Path target = exportDir.resolve(source.getFileName());
                Files.copy(source, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}