package com.example.comic_store.repository;

import com.example.comic_store.entity.ComicOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicOrderRepository extends JpaRepository<ComicOrder, Long> {
    @Query(
            value = "SELECT " +
                    "   u.id," +
                    "   c.id," +
                    "   co.id," +
                    "   c.comicName," +
                    "   c.imgComic," +
                    "   co.quantity," +
                    "   co.totalPrice," +
                    "   tc.typeName " +
                    "FROM UserEntity u " +
                    "LEFT JOIN ComicOrder co ON u.id = co.userId " +
                    "INNER JOIN Comic c ON co.comicId = c.id " +
                    "INNER JOIN TypeComic tc ON c.typeComicId = tc.id " +
                    "WHERE u.username = :username " +
                    "AND co.status = 0 " +
                    "GROUP BY u.id, co.id, c.id " +
                    "ORDER BY co.updatedAt DESC"
    )
    List<Object[]> getAllByUsername(@Param("username") String username);
}
