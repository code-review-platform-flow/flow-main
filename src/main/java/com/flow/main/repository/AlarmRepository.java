package com.flow.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flow.main.entity.AlarmEntity;

public interface AlarmRepository extends JpaRepository<AlarmEntity, Long> {
}
