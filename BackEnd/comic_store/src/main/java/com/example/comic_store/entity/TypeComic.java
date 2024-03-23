package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "type_comic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TypeComic {
    @Id
    private Long id;

    @Column(name = "comic_id")
    private Long comicId;

    @Column(name = "name")
    private Long typeName;
}
