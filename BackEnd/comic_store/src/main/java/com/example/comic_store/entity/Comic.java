package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "comic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Comic {
    @Id
    private Long id;

    @Column(name = "comic_name")
    private String comicName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "episode")
    private Long episode;

    @Column(name = "contents")
    private String contents;

    @Column(name = "price")
    private Long price;

    @Column(name = "sale")
    private Long sale;

    @Column(name = "residual_quantity")
    private Long residualQuantity;

    @Column(name = "img_comic")
    private String imgComic;

}
