package com.flow.main.repository;


import com.flow.main.entity.UserSessionsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSessionsRepository extends JpaRepository<UserSessionsEntity, Long> {

    @Query("SELECT us FROM UserSessionsEntity us WHERE us.user.userId = :userId")
    Optional<UserSessionsEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT us FROM UserSessionsEntity us WHERE us.refreshToken = :refreshToken AND us.useYn = true")
    Optional<UserSessionsEntity> findByRefreshToken(@Param("refreshToken") String refreshToken);

    @Query("SELECT us FROM UserSessionsEntity us WHERE us.accessToken =:accessToken AND us.useYn = true")
    Optional<UserSessionsEntity> findByAccessToken(@Param("accessToken") String accessToken);
}
