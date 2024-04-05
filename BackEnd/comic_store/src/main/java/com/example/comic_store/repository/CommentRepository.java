package com.example.comic_store.repository;

import com.example.comic_store.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
            value = "SELECT" +
                    " c.id," +
                    " c.userId," +
                    " c.comicId," +
                    " ue.fullName," +
                    " cm.comicName," +
                    " c.content," +
                    " c.datePost," +
                    " c.imgComment " +
                    "FROM Comment c " +
                    "INNER JOIN UserEntity ue ON c.userId = ue.id " +
                    "INNER JOIN Comic cm ON c.comicId = cm.id " +
                    "ORDER BY c.datePost",
            countQuery = "SELECT COUNT(c.id) FROM Comment c"
    )
    Page<Object[]> getCommentPage(Pageable pageable);

    @Query(
            value = "SELECT" +
                    " c.id," +
                    " c.userId," +
                    " c.comicId," +
                    " ue.fullName," +
                    " cm.comicName," +
                    " c.content," +
                    " c.datePost," +
                    " c.imgComment " +
                    "FROM Comment c " +
                    "INNER JOIN UserEntity ue ON c.userId = ue.id " +
                    "INNER JOIN Comic cm ON c.comicId = cm.id " +
                    "WHERE c.comicId = :comicId " +
                    "GROUP BY c.datePost",
            countQuery = "SELECT COUNT(c.id) FROM Comment c"
    )
    Page<Object[]> getCommentPageByComicId(Pageable pageable, @Param("comicId") Long comicId);
}
