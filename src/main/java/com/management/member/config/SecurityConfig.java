package com.management.member.config;

import com.management.member.constants.UserRole;
import com.management.member.security.CustomAuthenticationProvider;
import com.management.member.security.CustomUserDetailsService;
import com.management.member.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final UserService userService;

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

    http.csrf().disable();

    http
      .authorizeRequests()
      .antMatchers("/login", "/register", "/logout").permitAll()
      .antMatchers("/user/list/*").hasAuthority(UserRole.ADMIN.getRolename())
      .antMatchers("/user/detail/*").hasAuthority(UserRole.ADMIN.getRolename())
      .anyRequest().authenticated();

    http
      .formLogin()
      .loginPage("/login")
      .usernameParameter("userid")
      .passwordParameter("password")
      .loginProcessingUrl("/login")
      .defaultSuccessUrl("/")
      .and()
      .logout()
      .logoutUrl("/logout")
      .deleteCookies("JSESSIONID")
      .logoutSuccessUrl("/login")
      ;

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  protected void configure(AuthenticationManagerBuilder auth){
    auth.authenticationProvider(customAuthenticationProvider());
  }

  @Bean
  public CustomAuthenticationProvider customAuthenticationProvider(){
    return new CustomAuthenticationProvider(userDetailsService(), passwordEncoder());
  }

  @Bean
  protected UserDetailsService userDetailsService(){
    return new CustomUserDetailsService(userService);
  }
}
