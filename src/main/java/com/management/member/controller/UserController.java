package com.management.member.controller;

import com.management.member.constants.UserRole;
import com.management.member.dto.*;
import com.management.member.entity.User;
import com.management.member.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // TODO: 비즈니스 로직은 서비스로
    String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

    User user = new User();

    user.setUserId(userDto.getUserid());
    user.setPassword(encodedPassword);
    user.setUserName(userDto.getUsername());
    user.setRole(UserRole.DEFAULT.getRolename());

    userService.saveUser(user);

    return "redirect:/login";
  }

  @GetMapping("/user/list")
  public String userList(UserListRequest userListRequest, Model model){

    log.info("userListRequest = {}", userListRequest);
    UserListResponse userListResponse = new UserListResponse();

    // TODO: 아무 파라미터도 없는 경우
    // FIXME: DAO가 해야할 일을 서비스가 하고 있는 듯
    if(userListRequest.getUserid() != null && !userListRequest.getUserid().isEmpty()){
      userListResponse = userService.getUsersByUserId(userListRequest.getUserid(), userListRequest.getPagenum());
    }
    if(userListRequest.getUsername() != null){// TODO
    }else if (userListRequest.getUserid() == null && userListRequest.getUsername() == null){
      userListResponse = userService.getUsers(userListRequest.getPagenum());
    }

    model.addAttribute("users", userListResponse.getUserlist());
    model.addAttribute("totalCount", userListResponse.getTotalCount());
    model.addAttribute("isFirst", userListResponse.getIsFirstPage());
    model.addAttribute("isLast", userListResponse.getIsLastPage());

    return "pages/user/userlist";

  }

  @GetMapping("/user/detail")
  public String userDetail(@RequestParam(value="useridx") Long useridx, Model model){
    User userById = userService.getUserDetail(useridx);
    model.addAttribute("user", userById);
    return "pages/user/userdetail";
  }

  @PostMapping("/user/update") // TODO: url end point 수정할 것인지
  public String userUpdate(UserUpdateRequest userUpdateRequest){
    User user = userService.updateUser(userUpdateRequest);
    log.info("[USER UPDATE] :: updated user = {}", user);
    return "redirect:/user/list";
  }

  @ResponseBody
  @DeleteMapping("/user/delete")
  public UserDeleteResponse userDelete(@RequestParam(value = "userId") String userId){
    Boolean deleteResult = userService.deleteUser(userId);
    log.info("[USER DELETE] :: delete result = {}", deleteResult);
    return new UserDeleteResponse(deleteResult);
  }
}
