package com.MediaStack.MediaStack.entity.model.mediaFile;

import java.time.LocalDateTime;

import java.lang.Long;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import com.MediaStack.MediaStack.entity.model.mediaFile.MediaFileTypeEnum;

/*
  Represents a media file with its properties.
  This class is used to encapsulate the details of a media file such as its ID, name, type, upload date, and path.
 */
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MediaFileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaFileTypeEnum fileType;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime uploadDate;

    @NotBlank
    @Column(nullable = false)
    private String path;
}
