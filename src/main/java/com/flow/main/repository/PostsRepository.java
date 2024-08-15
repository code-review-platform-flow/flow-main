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


@Query(value = "SELECT DISTINCT ON (p.post_id) p.*, " +
    "(COALESCE(LENGTH(p.content) - LENGTH(REPLACE(LOWER(p.content), LOWER(:keyword), '')), 0) / LENGTH(:keyword) + " +
    "COALESCE(LENGTH(p.title) - LENGTH(REPLACE(LOWER(p.title), LOWER(:keyword), '')), 0) / LENGTH(:keyword) + " +
    "COALESCE(LENGTH(c.category_name) - LENGTH(REPLACE(LOWER(c.category_name), LOWER(:keyword), '')), 0) / LENGTH(:keyword) + " +
    "COALESCE(LENGTH(t.tag_name) - LENGTH(REPLACE(LOWER(t.tag_name), LOWER(:keyword), '')), 0) / LENGTH(:keyword)) AS keyword_count " +
    "FROM posts p " +
    "LEFT JOIN categories c ON p.category_id = c.category_id " +
    "LEFT JOIN post_tags pt ON p.post_id = pt.post_id " +
    "LEFT JOIN tags t ON pt.tag_id = t.tag_id " +
    "WHERE p.content ILIKE %:keyword% " +
    "OR p.title ILIKE %:keyword% " +
    "OR c.category_name ILIKE %:keyword% " +
    "OR t.tag_name ILIKE %:keyword% " +
    "ORDER BY p.post_id, keyword_count DESC",
    countQuery = "SELECT COUNT(DISTINCT p.post_id) FROM posts p " +
        "LEFT JOIN categories c ON p.category_id = c.category_id " +
        "LEFT JOIN post_tags pt ON p.post_id = pt.post_id " +
        "LEFT JOIN tags t ON pt.tag_id = t.tag_id " +
        "WHERE p.content ILIKE %:keyword% " +
        "OR p.title ILIKE %:keyword% " +
        "OR c.category_name ILIKE %:keyword% " +
        "OR t.tag_name ILIKE %:keyword%",
    nativeQuery = true)
//@Query("SELECT p FROM PostsEntity p " +
//    "LEFT JOIN p.category c " +
//    "LEFT JOIN PostTagsEntity pt ON pt.post = p " +
//    "LEFT JOIN pt.tag t " +
//    "WHERE p.content LIKE %:keyword% " +
//    "OR p.title LIKE %:keyword% " +
//    "OR c.categoryName LIKE %:keyword% " +
//    "OR t.tagName LIKE %:keyword% " +
//    "GROUP BY p.postId, p.content, p.title, c.categoryId, c.categoryName, t.tagId, t.tagName " +
//    "ORDER BY ( " +
//    "    COALESCE(LENGTH(p.content) - LENGTH(REPLACE(p.content, :keyword, '')), 0) + " +
//    "    COALESCE(LENGTH(p.title) - LENGTH(REPLACE(p.title, :keyword, '')), 0) + " +
//    "    COALESCE(LENGTH(c.categoryName) - LENGTH(REPLACE(c.categoryName, :keyword, '')), 0) + " +
//    "    COALESCE(SUM(CASE WHEN t.tagName LIKE %:keyword% THEN 1 ELSE 0 END), 0) " +
//    ") DESC")
    Page<PostsEntity> findPostsByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
