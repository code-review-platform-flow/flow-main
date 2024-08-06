package com.flow.main.repository;


import com.flow.main.entity.UserSessionsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSessionsRepository extends JpaRepository<UserSessionsEntity, Long> {

    @Query("SELECT us FROM UserSessionsEntity us WHERE us.user.userId = :userId")
    Optional<UserSessionsEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT CASE WHEN COUNT(us) > 0 THEN TRUE ELSE FALSE END FROM UserSessionsEntity us WHERE us.user.userId = :userId")
    boolean existsByUserId(@Param("userId") Long userId);

}
