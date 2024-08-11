package com.flow.main.repository;

import com.flow.main.entity.TagsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagsRepository extends JpaRepository<TagsEntity, Long> {

    boolean existsByTagName(String tagName);

    @Query("SELECT t FROM TagsEntity t WHERE t.tagName = :tagName")
    Optional<TagsEntity> findByTagName(@Param("tagName") String tagName);

    @Query("SELECT t FROM TagsEntity t WHERE t.tagId = :tagId")
    Optional<TagsEntity> findByTagId(@Param("tagId") Long tagId);
}
