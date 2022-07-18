package com.management.member.service;

import com.management.member.constants.ActionType;
import com.management.member.dto.UserListResponse;
import com.management.member.dto.UserUpdateRequest;
import com.management.member.entity.User;
import com.management.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final UserHistoryService userHistoryService;
  private final EntityManager em;

  @Override
  public User saveUser(User user) {

    User savedUser = userRepository.save(user);

    HttpServletRequest request = getRequest();
    userHistoryService.saveUserHistory(savedUser, ActionType.C, request);

    return savedUser;
  }

  private HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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

    PageRequest pageRequest = PageRequest.of(pagenum, 10);

    Page<User> userPage = userRepository.findAllByUserNameContaining(username, pageRequest);

    UserListResponse userListResponse = new UserListResponse();

    userListResponse.setUserlist(userPage.getContent());
    userListResponse.setCurPage(pagenum);
    userListResponse.setIsFirstPage(userPage.isFirst());
    userListResponse.setIsLastPage(userPage.isLast());
    userListResponse.setTotalCount(userPage.getTotalPages());

    return userListResponse;
  }

  @Override
  public User updateUser(UserUpdateRequest userUpdateRequest) {

    User user = userRepository.findByUserId(userUpdateRequest.getUserId());
    user.setUserName(userUpdateRequest.getUsername());

    User updated = userRepository.save(user);

    HttpServletRequest request = getRequest();
    userHistoryService.saveUserHistory(updated, ActionType.U, request);

    return updated;
  }

  @Override
  public Boolean deleteUser(String userId) {
    try{
      User byUserId = userRepository.findByUserId(userId);
      
      HttpServletRequest request = getRequest();
      userHistoryService.saveUserHistory(byUserId, ActionType.D, request);

      userRepository.delete(byUserId);

    }catch(Exception e){ //TODO: 올바르지 않은 유저의 Exception 종류 걸러 응답하기
      log.error(e.getMessage());
      return false;
    }
    return true;
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
