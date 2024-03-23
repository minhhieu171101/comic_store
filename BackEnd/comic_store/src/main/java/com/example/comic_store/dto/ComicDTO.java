package com.example.comic_store.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class ComicDTO {
    private Long id;

    private String comicName;

    private String authorName;

    private Date releaseDate;

    private Long episode;

    private String contents;

    private Long price;

    private Long sale;

    private Long residualQuantity;

    private String imgComic;
}
