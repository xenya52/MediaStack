package com.MediaStack.MediaStack.model.mediaFile;

import java.time.LocalDateTime;

import java.lang.Long;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import com.MediaStack.MediaStack.model.mediaFile.MediaFileTypeEnum;

/*
  Represents a media file with its properties.
  This class is used to encapsulate the details of a media file such as its ID, name, type, upload date, and path.
 */
@Entity
@Table(name = "media_file_model")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MediaFileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INTEGER")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "fileType", nullable = false, columnDefinition = "TEXT")
    private MediaFileTypeEnum fileType;

    @Column(name = "upload", nullable = false, columnDefinition = "TEXT")
    private LocalDateTime uploadDate;

    @Column(name = "saved path", nullable = false, columnDefinition = "TEXT")
    private String path;
}