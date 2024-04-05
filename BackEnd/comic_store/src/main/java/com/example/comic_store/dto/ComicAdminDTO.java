package com.example.comic_store.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ComicAdminDTO {
    private Long id;
    private String comicName;
    private String authorName;
    private String imgComic;
    private String typeName;
    private LocalDate releaseDate;
    private Long price;
    private Long sale;
    private String contents;
    private Long residualQuantity;
}
