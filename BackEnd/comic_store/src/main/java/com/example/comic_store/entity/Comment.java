package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {
    @Id
    private Long id;

    @Column(name = "date_post")
    private Date datePost;

    @Column(name = "content")
    private String content;

    @Column(name = "img_comment")
    private String imgComment;

    @Column(name = "user_id")
    private Long userId;
}
