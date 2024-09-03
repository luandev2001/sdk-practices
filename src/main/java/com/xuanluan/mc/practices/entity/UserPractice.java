package com.xuanluan.mc.practices.entity;

import com.xuanluan.mc.sdk.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class UserPractice extends BaseEntity {
    @Column(nullable = false, unique = true, length = 40)
    private String username;
}
