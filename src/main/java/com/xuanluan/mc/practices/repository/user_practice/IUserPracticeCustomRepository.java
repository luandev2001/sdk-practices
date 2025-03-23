package com.xuanluan.mc.practices.repository.user_practice;

import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.practices.request.page.UserPracticePageParameter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserPracticeCustomRepository {
     List<UserPractice> getPageResponse(UserPracticePageParameter parameter);
}
