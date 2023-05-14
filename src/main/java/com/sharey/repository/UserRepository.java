package com.sharey.repository;

import com.sharey.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 아이디로 회원 정보 조회
    Optional<UserEntity> findByUserId(String id);
    // 전체 회원목록 조회
    List<UserEntity> findAll();
}
