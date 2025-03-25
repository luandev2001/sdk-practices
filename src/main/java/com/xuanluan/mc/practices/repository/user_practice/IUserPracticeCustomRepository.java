package com.xuanluan.mc.practices.repository.user_practice;

import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.practices.request.page.UserPracticePrefixSearchParameter;

import java.util.List;

public interface IUserPracticeCustomRepository {
     List<UserPractice> getPageResponse(UserPracticePrefixSearchParameter parameter);
}
