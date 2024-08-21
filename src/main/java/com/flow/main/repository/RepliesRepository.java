package com.flow.main.repository;

import com.flow.main.entity.RepliesEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepliesRepository extends JpaRepository<RepliesEntity, Long> {

    @Query("SELECT r FROM RepliesEntity r WHERE r.replyId = :replyId")
    Optional<RepliesEntity> findByReplyId(@Param("replyId") Long replyId);

    @Query("SELECT r FROM RepliesEntity r WHERE r.replyId = :replyId AND r.user.userId = :userId")
    Optional<RepliesEntity> findByReplyId(@Param("replyId") Long replyId, @Param("userId") Long userId);

    @Query("SELECT r FROM RepliesEntity r WHERE r.comment.commentId = :commentId")
    Optional<List<RepliesEntity>> findAllByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT COUNT(r) FROM RepliesEntity r WHERE r.comment.commentId = :commentId")
    Long countByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT r.user, COUNT(r) as replyCount " +
        "FROM RepliesEntity r " +
        "GROUP BY r.user " +
        "ORDER BY replyCount DESC")
    Optional<List<Object[]>> findUsersWithReplyCountOrdered();
}
