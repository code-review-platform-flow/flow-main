package com.flow.main.repository;


import com.flow.main.entity.CategoriesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {

    @Query("SELECT c FROM CategoriesEntity c WHERE c.categoryName = :categoryName")
    Optional<CategoriesEntity> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT c FROM CategoriesEntity c WHERE c.categoryId = :categoryId")
    Optional<CategoriesEntity> findByCategoryId(@Param("categoryId") Long categoryId);

}
