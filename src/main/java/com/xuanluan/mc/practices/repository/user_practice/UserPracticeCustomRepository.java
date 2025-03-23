package com.xuanluan.mc.practices.repository.user_practice;

import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.sdk.repository.BaseRepositoryV2;

import javax.persistence.EntityManager;

public class UserPracticeCustomRepository extends BaseRepositoryV2<UserPractice, String> {
    public UserPracticeCustomRepository(EntityManager em) {
        super(UserPractice.class, em);
    }
}
