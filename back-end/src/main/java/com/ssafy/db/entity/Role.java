package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * 권한 모델 정의.
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseEntity{
    String authority; // 권한

    @Builder
    public Role(String authority) {
        this.authority = authority;
    }
}