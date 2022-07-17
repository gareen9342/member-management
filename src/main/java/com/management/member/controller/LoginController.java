package com.management.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

  /**
   * 로그인 페이지
   * @param error
   * @param errorMessage
   * @param model
   * @return
   */
  @GetMapping("/login")
  public String login(
                      @RequestParam(value="error", required = false) String error,
                      @RequestParam(value="errorMessage", required = false) String errorMessage,
                      Model model){

    model.addAttribute("error", error);
    model.addAttribute("errorMessage", errorMessage);

    return "login";
  }

}
