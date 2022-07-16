package com.management.member.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

    http
      .authorizeRequests()
      .antMatchers("/login", "/register").permitAll()
      .anyRequest().authenticated();

    http
      .formLogin()
      .loginPage("/login")
      .usernameParameter("username")
      .passwordParameter("password")
      .loginProcessingUrl("/login_process")
      ;

    return http.build();
  }
}
