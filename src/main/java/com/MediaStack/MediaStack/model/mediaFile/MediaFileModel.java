package com.MediaStack.MediaStack.model.mediaFile;

import java.time.LocalDateTime;

import java.lang.Long;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "uploadDate", nullable = false)
    private LocalDateTime uploadDate;

    @Column(name = "path", nullable = false, columnDefinition = "TEXT")
    private String path;
}