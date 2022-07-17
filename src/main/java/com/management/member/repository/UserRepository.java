package com.management.member.repository;

import com.management.member.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserId(String userId);

  Page<User> findAllByUserIdContaining(String userId, Pageable pageable);

  Page<User> findAllByUserNameContaining(String username, Pageable pageable);

}
