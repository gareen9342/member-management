package com.management.member.service;

import com.management.member.dto.UserListResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceImplTest {

  @Autowired
  private UserService userService;
  @Test
  void saveUser() {
  }

  // TODO: before 넣어서 확장해서 사용할 것
  @Test
  void getUsersByUserId() {
    String userId = "a"; // a, admin관리자 현재 가지고 있음
    UserListResponse userlistresponse = userService.getUsersByUserId(userId, 1);
    log.info("userlistresponse = ", userlistresponse.getUserlist());
    log.info("userlistresponse = ", userlistresponse.getIsFirstPage());
    log.info("userlistresponse = ", userlistresponse.getTotalCount());
  }

  @Test
  void getUsersByUsername() {
  }
}