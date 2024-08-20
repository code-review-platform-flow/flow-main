package com.flow.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flow.main.entity.AlarmEntity;

public interface AlarmRepository extends JpaRepository<AlarmEntity, Long> {

	@Query("SELECT a FROM AlarmEntity a WHERE a.alarmId = :alarmId")
	Optional<AlarmEntity> findAlarmByAlarmId(Long alarmId);

	@Query("SELECT a FROM AlarmEntity a WHERE a.user.userId = :userId")
	List<AlarmEntity> findAllByUserId(Long userId);

}
