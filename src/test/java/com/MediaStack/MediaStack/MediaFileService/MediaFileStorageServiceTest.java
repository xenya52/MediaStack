package com.MediaStack.MediaStack.MediaFileService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.MediaStack.MediaStack.service.MediaFileStorageService;

import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootTest
public class MediaFileStorageServiceTest {

    @Autowired
    private MediaFileStorageService mediaFileStorageService;

    /*
     * Test for exporting the database to a file.
     * This test checks if the export is successful and if the file exists at the specified path.
     */
    @Test
    void testFileStorageServiceExportDatabase() {
        String exportPath = "test/export/path";
        boolean successfulStream;
        boolean exportResponse;

        exportResponse = mediaFileStorageService.exportDatabase(exportPath);
        successfulStream = Files.exists(Paths.get(exportPath));

        assert exportResponse : "Database export should be successful";
        assert successfulStream : "File path exists at the export location";
    }
}
