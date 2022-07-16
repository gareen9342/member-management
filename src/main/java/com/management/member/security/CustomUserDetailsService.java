package com.management.member.security;

import com.management.member.entity.User;
import com.management.member.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

    log.info("[LOAD USER BY USERNAME] :: userid = {}", userid);

    User existUserByUserId = userService.getUserByUserId(userid);

    if(existUserByUserId == null){
      throw new UsernameNotFoundException(userid);
    }

    String rolename = existUserByUserId.getRole();

    log.info("[LOAD USER BY USERNAME] :: exist user found = {} rolename = {}", existUserByUserId, rolename);

    return new UserDetail(existUserByUserId.getUserId(), existUserByUserId.getPassword(), rolename);

  }
}
