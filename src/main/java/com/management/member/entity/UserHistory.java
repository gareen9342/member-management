package com.management.member.entity;

import com.management.member.constants.ActionType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="USER_HISTORY")
@Data @ToString
public class UserHistory {
  // FIXME
  @Id @GeneratedValue
  @Column(name="history_idx")
  private Long id;

  private String url;

  @Enumerated(EnumType.STRING)
  @Column(name="action_type")
  private ActionType actionType;

  @ManyToOne(targetEntity = User.class, fetch=FetchType.EAGER)
  @JoinColumn(name="reg_user_idx", nullable = false)
  private User user;

  @Column(name="reg_ip")
  private String regIp;

  @CreationTimestamp
  @Column(name="reg_dt", nullable = false)
  private LocalDateTime regDt;
}
