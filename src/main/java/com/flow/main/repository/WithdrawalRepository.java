package com.flow.main.repository;

import com.flow.main.entity.WithdrawalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<WithdrawalEntity, Long> {

}
