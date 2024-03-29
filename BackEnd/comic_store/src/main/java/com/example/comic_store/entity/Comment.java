package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Column(name = "date_post")
    private Date datePost;

    @Column(name = "content")
    private String content;

    @Column(name = "img_comment")
    private String imgComment;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
