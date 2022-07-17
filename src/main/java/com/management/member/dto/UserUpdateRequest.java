package com.management.member.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @ToString
public class UserUpdateRequest {

  @NotNull @NotEmpty
  private String userId;

  @NotNull
  private String username;
}
