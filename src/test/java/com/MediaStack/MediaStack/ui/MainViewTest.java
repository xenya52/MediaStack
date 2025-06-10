package com.MediaStack.MediaStack.ui;


import org.springframework.boot.test.context.SpringBootTest;

import com.MediaStack.MediaStack.service.MediaFileService;
import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class MainViewTest {

    @Mock
    private MediaFileService mediaFileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMainViewInitialization() {
        MainView mainView = new MainView(mediaFileService);
        assert mainView != null : "MainView should be initialized";
    }

    @Test
    void testGetFileTypeImage() {
        MainView mainView = new MainView(mediaFileService);
        MediaFileTypeEnum type = Assertions.assertDoesNotThrow(() -> {
            Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            return (MediaFileTypeEnum) method.invoke(mainView, "image/jpg");
        });
        Assertions.assertEquals(MediaFileTypeEnum.IMAGE_JPG, type);
    }

    @Test
    void testGetFileTypeVideo() {
        MainView mainView = new MainView(mediaFileService);
        MediaFileTypeEnum type = Assertions.assertDoesNotThrow(() -> {
            Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            return (MediaFileTypeEnum) method.invoke(mainView, "video/mp4");
        });
        Assertions.assertEquals(MediaFileTypeEnum.VIDEO_MP4, type);
    }

    @Test
    void testGetFileTypePdf() {
        MainView mainView = new MainView(mediaFileService);
        MediaFileTypeEnum type = Assertions.assertDoesNotThrow(() -> {
            Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            return (MediaFileTypeEnum) method.invoke(mainView, "application/pdf");
        });
        Assertions.assertEquals(MediaFileTypeEnum.PDF, type);
    }

    @Test
    void testGetFileTypeUnsupported() {
        MainView mainView = new MainView(mediaFileService);
        Assertions.assertThrows(InvocationTargetException.class, () -> {
            Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            method.invoke(mainView, "text/plain");
        });
    }
}