package com.flow.main.repository;

import com.flow.main.entity.CareerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CareerRepository extends JpaRepository<CareerEntity, Long> {

    @Query("SELECT c FROM CareerEntity c WHERE c.careerId = :careerId")
    Optional<CareerEntity> findByCareerId(@Param("careerId") Long careerId);

    @Query("SELECT c FROM CareerEntity c WHERE c.userInfo.userInfoId = :userInfoId")
    Optional<List<CareerEntity>> findAllByUserInfoId(@Param("userInfoId") Long userInfoId);

}
