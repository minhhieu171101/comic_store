package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Images extends BaseEntity {
    @Column(name = "comic_id")
    private Long comicId;

    @Column(name = "path")
    private String path;

    @Column(name = "order")
    private Integer order;
}
