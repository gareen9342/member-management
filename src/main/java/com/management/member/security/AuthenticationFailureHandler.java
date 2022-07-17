package com.management.member.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    String errorMessage = "인증 에러가 발생했습니다.";

    if(exception instanceof BadCredentialsException){
      errorMessage = "아이디 혹은 패스워드가 올바르지 않습니다. ";
    }
    // TODO: else

    errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
    setDefaultFailureUrl("/login?error=true&errorMessage="+errorMessage);
    super.onAuthenticationFailure(request, response, exception);
  }
}
