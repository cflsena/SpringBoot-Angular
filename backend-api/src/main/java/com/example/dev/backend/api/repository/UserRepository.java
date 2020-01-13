package com.example.dev.backend.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dev.backend.api.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Query("SELECT u FROM UserEntity u JOIN FETCH u.permissions where u.email = :email")
	public Optional<UserEntity> findByEmail(@Param("email") String email);
}
