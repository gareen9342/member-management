package com.management.member.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = authentication.getName();
    String password = (String)authentication.getCredentials();

    UserDetails userDetail = userDetailsService.loadUserByUsername(username);

    if(!passwordEncoder.matches(password, userDetail.getPassword())){
      throw new BadCredentialsException("bad credentials exception");
    }

    return new UsernamePasswordAuthenticationToken(userDetail, password, userDetail.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
