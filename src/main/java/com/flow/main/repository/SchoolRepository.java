package com.flow.main.repository;


import com.flow.main.entity.SchoolEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
    Optional<SchoolEntity> findBySchoolName(String schoolName);

}
