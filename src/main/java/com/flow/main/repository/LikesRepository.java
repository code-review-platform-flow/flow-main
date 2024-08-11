package com.flow.main.repository;


import com.flow.main.entity.LikesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface LikesRepository extends JpaRepository<LikesEntity, Long> {

    @Query("SELECT l FROM LikesEntity l WHERE l.user.userId = :userId AND l.post.postId = :postId AND l.useYn = :useYn")
    Optional<LikesEntity> findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId, @Param("useYn") Boolean useYn);

    @Query("SELECT COUNT(l) FROM LikesEntity l WHERE l.post.postId = :postId")
    Long countByPostId(@Param("postId") Long postId);

}
