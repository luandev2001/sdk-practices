package com.xuanluan.mc.practices.request.page;

import com.xuanluan.mc.sdk.model.request.page.BasePageParameter;
import com.xuanluan.mc.sdk.model.request.page.KeywordParameter;

import java.util.Set;

public class UserPracticePrefixSearchParameter extends BasePageParameter {
    @Override
    public KeywordParameter getKeywordParams() {
        return KeywordParameter.builder()
                .names(Set.of("username"))
                .prefix(true)
                .build();
    }
}
