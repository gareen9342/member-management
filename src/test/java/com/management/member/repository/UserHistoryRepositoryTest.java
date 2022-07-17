package com.management.member.repository;

import com.management.member.constants.ActionType;
import com.management.member.entity.User;
import com.management.member.entity.UserHistory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class UserHistoryRepositoryTest {

  @Autowired
  UserHistoryRepository userHistoryRepository;

  @Autowired
  UserRepository userRepository;


  @Test
  void getUsers(){

    // TODO: 이거 저장하고 삭제되는 로직을 공통으로 분리한다.
    UserHistory userHistory = new UserHistory();

    User savedUser = userRepository.findByUserId("bb");

    userHistory.setUser(savedUser);
    userHistory.setActionType(ActionType.C);
    userHistory.setUrl("url");
    userHistory.setRegIp("Ip");

    userHistoryRepository.save(userHistory);

    List<UserHistory> all = userHistoryRepository.findAll();

    log.info("all = {}",all);
  }

}