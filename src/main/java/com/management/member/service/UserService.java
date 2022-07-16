package com.management.member.service;

import com.management.member.entity.User;

public interface UserService {

  User saveUser(User user);

  User findUserByUserId(String userId);

  User findUserByUsername(String username);

  User updateUser(User user);

  Long deleteUser(Long userId);
}
