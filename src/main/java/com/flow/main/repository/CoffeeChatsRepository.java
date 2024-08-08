package com.flow.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flow.main.entity.CategoriesEntity;
import com.flow.main.entity.CoffeeChatsEntity;

public interface CoffeeChatsRepository extends JpaRepository<CoffeeChatsEntity, Long> {

	@Query("SELECT c FROM CoffeeChatsEntity  c WHERE c.initiatorUser.userId = :initiatorUserId")
	List<CoffeeChatsEntity> findAllCoffeeChatsByInitiatorUserId(@Param("initiatorUserId") Long initiatorUserId);

}
