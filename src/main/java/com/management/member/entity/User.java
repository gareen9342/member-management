package com.management.member.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @DynamicUpdate
@Getter @Setter
@Table(name="SYSTEM_USER")
public class User {

  @Id @GeneratedValue
  @Column(name="user_idx")
  private Long id; //FIXME

  @Column(name="user_id")
  private String userId;

  @Column(name="user_pw")
  private String password = "";

  @Column(name="user_nm")
  private String userName;

  @Column(name="user_auth")
  private String role;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<UserHistory> userHistoryList = new ArrayList<>();

}
