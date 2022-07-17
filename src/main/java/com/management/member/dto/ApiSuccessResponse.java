package com.management.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ApiSuccessResponse {

  // TODO: 별도의 상수만들기
  public static final String SUCCESS = "000000";
  public static final String FAILURE = "999999";

  private String resultCode = SUCCESS;

  public ApiSuccessResponse(String resultCode) {
    this.resultCode = resultCode;
  }
}
