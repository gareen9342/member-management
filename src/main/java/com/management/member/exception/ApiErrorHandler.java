package com.management.member.exception;

import com.management.member.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(value = "com.management.member.controller.api")
@Slf4j
public class ApiErrorHandler {

  public ResponseEntity<ErrorResponse> handleFieldError(MethodArgumentNotValidException exception){

    List<String> errorFields = new ArrayList<String>();
    BindingResult bindingResult = exception.getBindingResult();


    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      errorFields.add(fieldError.getField());
      log.info("[FIELD VALIDATION ERROR] :: field = {}, defaultMessage = {}, value = {}", fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue());
    }

    // 클라이언트 단의 bad request 에러
    ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST), "[BAD REQUEST] BAD FIELDS = " + String.join(", ",errorFields));

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
