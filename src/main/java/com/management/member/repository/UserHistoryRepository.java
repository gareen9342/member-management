package com.management.member.repository;

import com.management.member.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
