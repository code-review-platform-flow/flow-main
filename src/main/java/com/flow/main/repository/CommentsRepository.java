package com.flow.main.repository;

import com.flow.main.entity.CommentsEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    @Query("SELECT c FROM CommentsEntity c WHERE c.commentId = :commentId")
    Optional<CommentsEntity> findByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT c FROM CommentsEntity c WHERE c.commentId = :commentId AND c.user.userId = :userId")
    Optional<CommentsEntity> findByCommentId(@Param("commentId") Long commentId, @Param("userId") Long userId);

    @Query("SELECT c FROM CommentsEntity c WHERE c.post.postId = :postId")
    Optional<List<CommentsEntity>> findAllByPostId(@Param("postId") Long postId);

    @Query("SELECT COUNT(c) FROM CommentsEntity c WHERE c.post.postId = :postId")
    Long countByPostId(@Param("postId") Long postId);

    @Query("SELECT c.user, COUNT(c) as commentCount " +
        "FROM CommentsEntity c " +
        "GROUP BY c.user " +
        "ORDER BY commentCount DESC")
    Optional<List<Object[]>> findUsersWithCommentCountOrdered();
}
