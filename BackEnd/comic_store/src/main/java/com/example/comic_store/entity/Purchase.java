package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity {
    @Column(name = "total")
    private Long comicId;

    @Column(name = "user_oder_id", nullable = false)
    private Long userOrderId;
}
