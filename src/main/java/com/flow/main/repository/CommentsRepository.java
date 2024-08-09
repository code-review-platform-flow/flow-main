package com.flow.main.repository;

import com.flow.main.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

}
