package com.chatapp.repository;

import com.chatapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUsernameAndStatus(String name, int status);
    List<UserEntity> findAllByStatusNot(Byte status);
    UserEntity findOneById(Long id);
    UserEntity findOneByUsernameAndPassword(String username, String password);
    UserEntity findOneByUsername(String username);
}
