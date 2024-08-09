package com.flow.main.repository;

import com.flow.main.entity.CommentsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    @Query("SELECT c FROM CommentsEntity c WHERE c.commentId = :commentId")
    Optional<CommentsEntity> findByCommentId(@Param("commentId") Long commentId);

}
