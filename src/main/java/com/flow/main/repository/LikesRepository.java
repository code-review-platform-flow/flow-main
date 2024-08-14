package com.flow.main.repository;


import com.flow.main.entity.LikesEntity;
import com.flow.main.entity.PostsEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface LikesRepository extends JpaRepository<LikesEntity, Long> {

    @Query("SELECT l FROM LikesEntity l WHERE l.user.userId = :userId AND l.post.postId = :postId AND l.useYn = :useYn")
    Optional<LikesEntity> findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId, @Param("useYn") Boolean useYn);

    @Query("SELECT COUNT(l) FROM LikesEntity l WHERE l.post.postId = :postId AND l.useYn = true")
    Long countByPostId(@Param("postId") Long postId);

    //@Query("SELECT l.post FROM LikesEntity l GROUP BY l.post.postId ORDER BY COUNT(l.likeId) DESC")
    @Query("SELECT p FROM PostsEntity p WHERE p.postId IN (SELECT l.post.postId FROM LikesEntity l WHERE l.useYn = true GROUP BY l.post.postId ORDER BY COUNT(l.likeId) DESC)")
    List<PostsEntity> findPostIdsOrderByLikeCount(Pageable pageable);
}
