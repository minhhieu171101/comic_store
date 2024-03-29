package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder extends BaseEntity {
    @Column(name = "amount")
    private Long amount;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "note")
    private Long note;

    @Column(name="comic_id", nullable = false)
    private Long comicId;

    @Column(name="user_id", nullable = false)
    private Long userId;
}
