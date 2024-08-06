package com.flow.main.repository;



import com.flow.main.entity.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.email = :email")
    Optional<UsersEntity> findByEmail(@Param("email") String email);

}
