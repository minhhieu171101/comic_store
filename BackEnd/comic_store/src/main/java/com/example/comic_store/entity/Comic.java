package com.example.comic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "comic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comic extends BaseEntity {
//    Tên truyện
    @Column(name = "comic_name")
    private String comicName;

//    Tên tác giả
    @Column(name = "author_name")
    private String authorName;

//    Ngày phát hành
    @Column(name = "release_date")
    private LocalDate releaseDate;

//    Tập của cuốn truyện
    @Column(name = "episode")
    private Long episode;

//    Thông tin giới thiệu về cuốn truyện
    @Column(name = "contents")
    private String contents;

//    Thể loại
    @Column(name = "type_comic_id")
    private Long typeComicId;

//    Giá bán
    @Column(name = "price")
    private Long price;

//    Phần trăm giảm giá
    @Column(name = "sale")
    private Long sale;

//    Số lượng còn lại
    @Column(name = "residual_quantity")
    private Long residualQuantity;

//    Kích thước của cuốn truyện
    @Column(name = "size")
    private String size;

//    Số trang của cuốn truyện
    @Column(name = "total-page")
    private Integer totalPage;

//    Trọng lượng của cuốn truyện
    @Column(name = "weight")
    private Integer weight;

//    Trạng thái của cuốn truyện: 1 còn hàng, 0 hết hàng
    @Column(name = "status")
    private Boolean status;

    @Column(name = "img_comic")
    private String imgComic;

}
