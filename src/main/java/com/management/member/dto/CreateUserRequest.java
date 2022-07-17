package com.management.member.dto;

import com.management.member.constants.UserRole;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @ToString
public class CreateUserRequest {

  @NotNull @NotEmpty
  private String userId;

  @NotNull @NotEmpty
  private String username;

  private String userRole = UserRole.DEFAULT.getRolename();
}
