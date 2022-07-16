package com.management.member.controller;

import com.management.member.constants.UserRole;
import com.management.member.dto.UserDto;
import com.management.member.entity.User;
import com.management.member.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

  private final PasswordEncoder bCryptPasswordEncoder;
  private final UserService userService;

  @GetMapping("/register")
  public String register(){
    return "register";
  }

  @PostMapping("/register")
  public String register(UserDto userDto){

    // TODO: parameter 로깅은 필터로 구현하기
    log.info("user dto = {}", userDto);

    String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

    User user = new User();

    user.setUserId(userDto.getUserid());
    user.setPassword(encodedPassword);
    user.setUserName(userDto.getUsername());
    user.setRole(UserRole.DEFAULT.getRolename());

    userService.saveUser(user);

    return "redirect:/login";
  }
}
