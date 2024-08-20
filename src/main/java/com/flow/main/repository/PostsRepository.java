package com.flow.main.repository;

import com.flow.main.entity.PostsEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {

    @Query("SELECT p FROM PostsEntity p WHERE p.postId = :postId")
    Optional<PostsEntity> findByPostId(@Param("postId") Long postId);

    @Query("SELECT p FROM PostsEntity p WHERE p.postId = :postId AND p.user.userId = :userId")
    Optional<PostsEntity> findByPostId(@Param("postId") Long postId, @Param("userId") Long userId);

    @Query("SELECT p FROM PostsEntity p ORDER BY p.createDate DESC LIMIT 1")
    Optional<PostsEntity> findLatestByCreateDate();

    @Query("SELECT p FROM PostsEntity p " +
        "WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% " +
        "ORDER BY " +
        "(LENGTH(p.title) - LENGTH(REPLACE(p.title, :keyword, '')) + " +
        "LENGTH(p.content) - LENGTH(REPLACE(p.content, :keyword, ''))) DESC")
    Optional<List<PostsEntity>> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p, " +
        "(LENGTH(p.title) - LENGTH(REPLACE(p.title, :keyword, '')) + " +
        "LENGTH(p.content) - LENGTH(REPLACE(p.content, :keyword, ''))) AS keywordCount " +
        "FROM PostsEntity p " +
        "WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% " +
        "ORDER BY keywordCount DESC")
    Optional<List<Object[]>> searchByKeywordWithCount(@Param("keyword") String keyword);

    @Query("SELECT p FROM PostsEntity p WHERE p.category.categoryId = :categoryId")
    Optional<List<PostsEntity>> findAllByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM PostsEntity p WHERE p.user.userId = :userId")
    Optional<List<PostsEntity>> findAllByUserId(@Param("userId") Long userId);
}
