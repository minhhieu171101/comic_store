package com.example.comic_store.repository;

import com.example.comic_store.entity.Comic;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    @Query(value = "SELECT c " +
                    "FROM Comic c " +
                    "ORDER BY c.createdAt DESC ",
    countQuery =    "SELECT COUNT(c) FROM Comic c")
    Page<Comic> getComicLandingPage(Pageable pageable);

    Page<Comic> getAllByTypeComicIdOrderByCreatedAt(Pageable pageable, Long typeComicId);

    @Query(
            value = "SELECT " +
                    " c.id, " +
                    " c.comicName," +
                    " c.authorName," +
                    " c.imgComic," +
                    " tc.typeName," +
                    " c.releaseDate," +
                    " c.price, " +
                    " c.sale," +
                    " c.contents " +
                    "FROM Comic c " +
                    "INNER JOIN TypeComic tc on c.typeComicId = tc.id " +
                    "ORDER BY c.createdAt DESC",
            countQuery = "SELECT count(c.id) FROM Comic c"
    )
    Page<Object[]> getAllComic(Pageable pageable);
}
