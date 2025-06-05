package com.MediaStack.MediaStack.ui;


import org.springframework.boot.test.context.SpringBootTest;

import com.MediaStack.MediaStack.service.MediaFileService;
import com.MediaStack.MediaStack.ui.MainView;

import org.junit.jupiter.api.Test;

@SpringBootTest
class MainViewTest {

    @org.mockito.Mock
    private MediaFileService mediaFileService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        org.mockito.MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMainViewInitialization() {
        MainView mainView = new MainView(mediaFileService);
        assert mainView != null : "MainView should be initialized";
    }

    @Test
    void testGetFileTypeImage() {
        MainView mainView = new MainView(mediaFileService);
        String type = org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            java.lang.reflect.Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            return (String) method.invoke(mainView, "image/png");
        });
        org.junit.jupiter.api.Assertions.assertEquals("IMAGE", type);
    }

    @Test
    void testGetFileTypeVideo() {
        MainView mainView = new MainView(mediaFileService);
        String type = org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            java.lang.reflect.Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            return (String) method.invoke(mainView, "video/mp4");
        });
        org.junit.jupiter.api.Assertions.assertEquals("VIDEO", type);
    }

    @Test
    void testGetFileTypePdf() {
        MainView mainView = new MainView(mediaFileService);
        String type = org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            java.lang.reflect.Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            return (String) method.invoke(mainView, "application/pdf");
        });
        org.junit.jupiter.api.Assertions.assertEquals("PDF", type);
    }

    @Test
    void testGetFileTypeUnsupported() {
        MainView mainView = new MainView(mediaFileService);
        org.junit.jupiter.api.Assertions.assertThrows(java.lang.reflect.InvocationTargetException.class, () -> {
            java.lang.reflect.Method method = MainView.class.getDeclaredMethod("getFileType", String.class);
            method.setAccessible(true);
            method.invoke(mainView, "text/plain");
        });
    }
}