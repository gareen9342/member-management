package com.management.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data @ToString
@AllArgsConstructor
public class UserDeleteResponse {
  private Boolean isSuccess;
}
