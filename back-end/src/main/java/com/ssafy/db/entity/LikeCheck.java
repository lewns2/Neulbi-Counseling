package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LikeCheck extends BaseEntity{


    // 외래키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    // 외래키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="community_id")
    Community community;


}
