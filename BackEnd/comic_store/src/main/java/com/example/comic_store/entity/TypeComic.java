package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "type_comic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeComic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comic_id", nullable = false)
    private Long comicId;

    @Column(name = "name")
    private Long typeName;
}
