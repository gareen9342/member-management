package com.management.member.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserListRequest {
  private String userid;

  private String username;

  private Integer pagenum = 0;
}
