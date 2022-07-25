package com.management.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.member.controller.api.UserApiController;
import com.management.member.entity.User;
import com.management.member.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserApiController userApiController;

  @BeforeEach
  void init(){
    mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();
  }
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
  void 유저_수정시_userId는_Null이어선_안된다() throws Exception {

    MultiValueMap<String, String> param = new LinkedMultiValueMap<>();

    param.add("userId", null);
    param.add("username", "username");

    mockMvc
      .perform(post("/api/user")
      .params(param))
      .andExpect(status().is4xxClientError())
      .andDo(print());
  }
}