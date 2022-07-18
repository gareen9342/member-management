package com.management.member.service;

import com.management.member.constants.ActionType;
import com.management.member.entity.User;
import com.management.member.entity.UserHistory;

import javax.servlet.http.HttpServletRequest;

public interface UserHistoryService {

  UserHistory saveUserHistory(User user, ActionType actionType, HttpServletRequest request);
}
