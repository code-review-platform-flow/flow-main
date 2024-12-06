package com.flow.main.repository;

import com.flow.main.entity.FollowsEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowsRepository extends JpaRepository<FollowsEntity, Long> {
    @Query("SELECT f FROM FollowsEntity f WHERE f.followeeId = :followeeId")
    Optional<List<FollowsEntity>> findAllByFolloweeId(@Param("followeeId") Long followeeId);

    @Query("SELECT f FROM FollowsEntity f WHERE f.followerId = :followerId")
    Optional<List<FollowsEntity>> findAllByFollowerId(@Param("followerId") Long followerId);

    @Query("SELECT f FROM FollowsEntity f WHERE f.followerId = :followerId AND f.followeeId = :followeeId AND f.useYn = true")
    Optional<FollowsEntity> findByFollowerIdAndFolloweeIdUseYnTrue(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    @Query("SELECT f FROM FollowsEntity f WHERE f.followerId = :followerId AND f.followeeId = :followeeId AND f.useYn = false")
    Optional<FollowsEntity> findByFollowerIdAndFolloweeIdUseYnFalse(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    @Query("SELECT COUNT(f) FROM FollowsEntity f WHERE f.followeeId = :followeeId AND f.useYn = true")
    Long countByFolloweeId(@Param("followeeId") Long followeeId);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FollowsEntity f WHERE f.followeeId =:followeeId AND f.followerId =:followerId AND f.useYn = true")
    boolean existsByFolloweeIdAndFollowerId(@Param("followeeId") Long followeeId, @Param("followerId") Long followerId);
}
