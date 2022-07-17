package com.management.member.controller;

import com.management.member.entity.User;
import com.management.member.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserControllerTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void userList() {
  }

  @Test
  void pagingTest(){
    PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.ASC,"userId");

    Page<User> page = userRepository
      .findAllByUserIdContaining("a", pageRequest);

    List<User> users = page.getContent();
    log.info("users = {}", users);

    int totalPages = page.getTotalPages();
    log.info("totalpages = {}", totalPages);

    assertThat(users.get(0).getUserName()).isEqualTo("testname");

  }

  @Test
  void getUsersByUserId(){}

  @Test
  void getUsersByUsername(){}
}