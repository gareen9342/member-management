package com.management.member.service;

import com.management.member.entity.User;
import com.management.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findUserByUserId(String userId) {
    return null;
  }

  @Override
  public User findUserByUsername(String username) {
    return null;
  }

  @Override
  public User updateUser(User user) {
    return null;
  }

  @Override
  public Long deleteUser(Long userId) {
    return null;
  }
}
