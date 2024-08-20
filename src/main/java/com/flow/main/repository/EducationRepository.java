package com.flow.main.repository;

import com.flow.main.entity.EducationEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EducationRepository extends JpaRepository<EducationEntity, Long> {

    @Query("SELECT e FROM EducationEntity e WHERE e.educationId = :educationId")
    Optional<EducationEntity> findByEducationId(@Param("educationId") Long educationId);

    @Query("SELECT e FROM EducationEntity e WHERE e.userInfo.userInfoId = :userInfoId")
    Optional<List<EducationEntity>> findAllByUserInfoId(@Param("userInfoId") Long userInfoId);
}
