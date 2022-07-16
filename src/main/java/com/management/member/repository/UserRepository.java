package com.management.member.repository;

import com.management.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserName(String username);

  User findByUserId(String userId);
}
