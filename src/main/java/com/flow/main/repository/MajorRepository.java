package com.flow.main.repository;


import com.flow.main.entity.MajorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<MajorEntity, Long> {

    Optional<MajorEntity> findByMajorName(String majorName);

}
