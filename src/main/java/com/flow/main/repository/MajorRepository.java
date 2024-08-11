package com.flow.main.repository;


import com.flow.main.entity.MajorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MajorRepository extends JpaRepository<MajorEntity, Long> {
    @Query("SELECT m FROM MajorEntity m WHERE m.majorName = :majorName")
    Optional<MajorEntity> findByMajorName(@Param("majorName") String majorName);

    @Query("SELECT m FROM MajorEntity m WHERE m.majorId = :majorId")
    Optional<MajorEntity> findByMajorId(@Param("majorId") Long majorId);

}
