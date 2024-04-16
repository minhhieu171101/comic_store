package com.example.comic_store.repository;

import com.example.comic_store.entity.Comic;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    @Query(value = "SELECT c " +
                    "FROM Comic c " +
                    "ORDER BY c.createdAt DESC ",
    countQuery =    "SELECT COUNT(c) FROM Comic c")
    Page<Comic> getComicLandingPage(Pageable pageable);

    @Query(
            value = "SELECT c " +
                    "FROM Comic c " +
                    "WHERE c.typeComicIds LIKE CONCAT('%', :typeComicId, '%') " +
                    "ORDER BY c.createdAt",
            countQuery = "SELECT COUNT(c) " +
                         "FROM Comic c " +
                         "WHERE c.typeComicIds LIKE CONCAT('%', :typeComicId, '%') "
    )
    Page<Comic> getAllByTypeComicId(Pageable pageable, @Param("typeComicId") String typeComicId);

    @Query(
            value = "SELECT " +
                    " c.id, " +
                    " c.comicName," +
                    " c.authorName," +
                    " c.imgComic," +
                    " GROUP_CONCAT(tc.typeName, ', ') AS typename," +
                    " c.releaseDate," +
                    " c.price, " +
                    " c.sale," +
                    " c.contents " +
                    "FROM Comic c " +
                    "LEFT JOIN TypeComic tc " +
                    "ON CAST(FIND_IN_SET(CONCAT('s', tc.id, 'e') , c.typeComicIds) AS int) > CAST(0 AS int) " +
                    "GROUP BY " +
                    " c.id," +
                    " c.typeComicIds " +
                    " ORDER BY c.createdAt DESC",
            countQuery = "SELECT count(c.id) FROM Comic c"
    )
    Page<Object[]> getAllComic(Pageable pageable);
}
