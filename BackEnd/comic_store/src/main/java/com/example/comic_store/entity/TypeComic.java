package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "type_comic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeComic extends BaseEntity {
    @Column(name = "name", length = 100)
    private String typeName;
}
