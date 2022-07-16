package com.management.member.service;

import com.management.member.entity.User;

import java.util.List;

public interface UserService {

  User saveUser(User user);

  User getUserByUserId(String userId);

  User findUserByUsername(String username);

  User updateUser(User user);

  Long deleteUser(Long userId);

  List<User> getUsers();
}
