package com.management.member.controller.api;

import com.management.member.dto.UserListRequest;
import com.management.member.dto.UserListResponse;
import com.management.member.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

  private final UserService userService;

  /**
   * /api/user/list
   * pagenum은 0번부터 시작
   * @return {
   *   userlist: [user..]
   *   totalCount: 0,
   *   curPage: 1,
   *   isFirstPage: true,
   *   isLastPage: false;
   * }
   */
  @GetMapping("/list")
  public UserListResponse getUserList(UserListRequest userListRequest){

    UserListResponse userListResponse = new UserListResponse();
    log.info("userListResponse = {}",userListRequest);

    if(userListRequest.getUserid() != null){ // 유저 아이디 검색 (like)
      /*검색시 유저아이디 우선적*/
      userListResponse = userService.getUsersByUserId(userListRequest.getUserid(), userListRequest.getPagenum());
    }else if(userListRequest.getUsername() != null){// 유저 이름 검색 (like)
      userListResponse = userService.getUsersByUsername(userListRequest.getUsername(), userListRequest.getPagenum());
    }else if (userListRequest.getUserid() == null && userListRequest.getUsername() == null){ // 전부 검색 (like)
      userListResponse = userService.getUsers(userListRequest.getPagenum());
    }

    return userListResponse;
  }
}
