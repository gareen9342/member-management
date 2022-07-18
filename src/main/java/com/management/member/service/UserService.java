package com.management.member.service;

import com.management.member.dto.UserListResponse;
import com.management.member.dto.UserUpdateRequest;
import com.management.member.entity.User;

public interface UserService {

  User saveUser(User user);

  User getUserByUserId(String userId);

  UserListResponse getUsersByUserId(String userId, Integer pagenum);

  UserListResponse getUsersByUsername(String username, Integer pagenum);

  User updateUser(UserUpdateRequest userUpdateRequest);

  Boolean deleteUser(String userId);

  UserListResponse getUsers(Integer pagenum);

  User getUserDetail(Long userIdx);
}
