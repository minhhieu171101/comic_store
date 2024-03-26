package com.example.comic_store.dto;

import java.util.Date;
import lombok.Data;

@Data
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
