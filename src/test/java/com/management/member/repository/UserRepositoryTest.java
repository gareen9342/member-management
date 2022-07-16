package com.management.member.repository;

import com.management.member.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void createUser(){
    String username = "testname";

    User user = new User();
    user.setName(username);

    User savedUser = userRepository.save(user);

    assertThat(savedUser.getName()).isEqualTo(username);
  }
}