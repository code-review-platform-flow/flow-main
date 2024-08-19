package com.flow.main.repository;

import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.entity.FollowsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowsRepository extends JpaRepository<FollowsEntity, Long> {

    @Query("SELECT f FROM FollowsEntity f WHERE f.followerId = :followerId AND f.followeeId = :followeeId AND f.useYn = true")
    Optional<FollowsEntity> findByFollowerIdAndFolloweeIdUseYnTrue(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    @Query("SELECT f FROM FollowsEntity f WHERE f.followerId = :followerId AND f.followeeId = :followeeId AND f.useYn = false")
    Optional<FollowsEntity> findByFollowerIdAndFolloweeIdUseYnFalse(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

}
