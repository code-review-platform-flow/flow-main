package com.flow.main.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flow.main.entity.CoffeeChatsEntity;

public interface CoffeeChatsRepository extends JpaRepository<CoffeeChatsEntity, Long> {

	@Query("SELECT c FROM CoffeeChatsEntity  c WHERE c.initiatorUser.userId = :initiatorUserId")
	List<CoffeeChatsEntity> findAllCoffeeChatsByInitiatorUserIdWithPageable(@Param("initiatorUserId") Long initiatorUserId, Pageable pageable);

}
