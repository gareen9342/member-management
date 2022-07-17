package com.management.member.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity @Data @ToString @DynamicUpdate
@Table(name="SYSTEM_USER")
public class User {

  @Id @GeneratedValue
  @Column(name="user_idx")
  private Long id;

  @Column(name="user_id")
  private String userId;

  @Column(name="user_pw")
  private String password = "";

  @Column(name="user_nm")
  private String userName;

  @Column(name="user_auth")
  private String role;

}
