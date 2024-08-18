package com.flow.main.repository;

import com.flow.main.entity.PostTagsEntity;
import com.flow.main.entity.PostsEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;


public interface PostTagsRepository extends JpaRepository<PostTagsEntity, Long> {

    @Query("SELECT p FROM PostTagsEntity p WHERE p.post.postId = :postId AND p.useYn = true")
    Optional<List<PostTagsEntity>> findAllByPostId(@Param("postId") Long postId);

    @Query("SELECT p FROM PostTagsEntity p WHERE p.post.postId = :postId AND p.tag.tagId = :tagId AND p.useYn = true")
    Optional<PostTagsEntity> findByPostIdAndTagId(@Param("postId") Long postId, @Param("tagId") Long tagId);

    @Query("SELECT p FROM PostTagsEntity p WHERE p.post.postId = :postId AND p.tag.tagId = :tagId AND p.useYn = false")
    Optional<PostTagsEntity> findByPostIdAndTagIdUseYnFalse(@Param("postId") Long postId, @Param("tagId") Long tagId);

    @Query("SELECT p FROM PostTagsEntity p WHERE p.tag.tagId = :tagId AND p.useYn = true")
    Optional<List<PostTagsEntity>> findAllByTagId(@Param("tagId") Long tagId);
}
