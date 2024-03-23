package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserOrder {
    @Id
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "note")
    private Long note;

    @Column(name="comic_id")
    private Long comicId;

    @Column(name="user_id")
    private Long userId;
}
