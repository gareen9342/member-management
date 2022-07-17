package com.management.member.dto;

import com.management.member.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data @ToString
public class UserListResponse {

  private List<User> userlist = new ArrayList<>();

  private Integer totalCount = 0;

  private Integer curPage;

  private Boolean isFirstPage;

  private Boolean isLastPage;

}
