package com.management.member.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {

  ADMIN("SYSTEM_ADMIN"),
  DEFAULT("ROLE_USER");

  @Getter
  private final String rolename;
}
