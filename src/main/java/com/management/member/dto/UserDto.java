package com.management.member.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @ToString
public class UserDto {

  @NotNull @NotEmpty
  private String userid;

  @NotNull @NotEmpty
  private String password;

  @NotNull @NotEmpty
  private String username;

}
