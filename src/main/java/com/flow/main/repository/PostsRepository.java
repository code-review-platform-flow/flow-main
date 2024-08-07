package com.flow.main.repository;

import com.flow.main.entity.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {

}
