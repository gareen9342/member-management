package com.management.member.service;

import com.management.member.dto.UserListResponse;
import com.management.member.entity.User;
import com.management.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getUserByUserId(String userId) {
    return userRepository.findByUserId(userId);
  }

  @Override
  public UserListResponse getUsersByUserId(String userId, Integer pagenum) {

    PageRequest pageRequest = PageRequest.of(pagenum, 10);
    Page<User> userPage = userRepository.findAllByUserIdContaining(userId, pageRequest);

    UserListResponse userListResponse = new UserListResponse();
    // TODO: userListResponse의 값을 세팅하는 단을 빌더나 생성자 변경을 통해 코드 수 줄이기
    userListResponse.setUserlist(userPage.getContent());
    userListResponse.setCurPage(pagenum);
    userListResponse.setIsFirstPage(userPage.isFirst());
    userListResponse.setIsLastPage(userPage.isLast());
    userListResponse.setTotalCount(userPage.getTotalPages());

    return userListResponse;
  }

  @Override
  public UserListResponse getUsersByUsername(String username, Integer pagenum) {
    return null ;
  }

  @Override
  public User updateUser(User user) {
    return null;
  }

  @Override
  public Long deleteUser(Long userId) {
    return null;
  }

  @Override
  public UserListResponse getUsers(Integer pagenum) {
    Page<User> userpage = userRepository.findAll(PageRequest.of(pagenum, 10));

    UserListResponse userListResponse = new UserListResponse();

    userListResponse.setUserlist(userpage.getContent());
    userListResponse.setCurPage(pagenum);
    userListResponse.setTotalCount(userpage.getTotalPages());
    userListResponse.setIsFirstPage(userpage.isFirst());
    userListResponse.setIsLastPage(userpage.isLast());

    return userListResponse;
  }

  @Override
  public User getUserDetail(Long userIdx) {
    return userRepository.findById(userIdx).get(); // TODO: null 처리
  }

}
