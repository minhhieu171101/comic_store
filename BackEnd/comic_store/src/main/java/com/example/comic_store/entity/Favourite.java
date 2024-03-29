package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "favourite")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favourite extends BaseEntity {
    @Column(name = "comic_id", nullable = false)
    private Long comicId;
}
