package com.flow.main.repository;


import com.flow.main.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Long> {

}
