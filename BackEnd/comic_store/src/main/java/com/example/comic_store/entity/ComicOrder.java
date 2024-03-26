package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "comic_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComicOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comic_id", nullable = false)
    private Long comicId;

    @Column(name = "user_oder_id", nullable = false)
    private Long userOrderId;
}
