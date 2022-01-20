package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * 권한 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Role extends BaseEntity{
    String authority; // 권한
}