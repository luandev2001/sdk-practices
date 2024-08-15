package com.xuanluan.mc.practices.repository;

import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.sdk.repository.JpaMultipleRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPracticeRepository extends JpaMultipleRepository<UserPractice, String> {
}
