package com.management.member.service;

import com.management.member.constants.ActionType;
import com.management.member.entity.User;
import com.management.member.entity.UserHistory;
import com.management.member.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHistoryServiceImpl implements UserHistoryService{

  private final UserHistoryRepository userHistoryRepository;

  @Override
  public UserHistory saveUserHistory(User user, ActionType actionType, HttpServletRequest request) {
    UserHistory userHistory = new UserHistory();

    userHistory.setUser(user);
    userHistory.setActionType(actionType);
    userHistory.setUrl(request.getRequestURL().toString());
    userHistory.setRegIp(request.getRemoteAddr());

    UserHistory savedUserHistory = userHistoryRepository.save(userHistory);
    return savedUserHistory;
  }
}
