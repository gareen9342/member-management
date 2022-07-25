package com.management.member.dto;

public class ErrorResponse extends ApiResponse{

  public ErrorResponse() {
    super(ApiResponse.FAILURE, "unknown error occured");
  }

  public ErrorResponse(String resultCode, String resultMsg) {
    super(resultCode, resultMsg);
  }
}
