package com.management.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

  public static final String SUCCESS = "000000";
  public static final String FAILURE = "999999";

  private String resultCode = SUCCESS;
  private String resultMsg = "server responsed";

  public ApiResponse(String resultCode) {
    this.resultCode = resultCode;
  }
}
