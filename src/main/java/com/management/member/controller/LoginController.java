package com.management.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
  // TODO: query parameter 로깅 디펜던시 추가하기

  @GetMapping("/login")
  public String login(){
    return "login";
  }

}
