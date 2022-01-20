package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class User extends BaseEntity{
    String nickname; // 별명
    String email; // 이메일 == 아이디
    String profile_img; // 프로필 이미지 주소
    boolean isConsultant; // 상담가 신청 여부
    boolean isPenalty; // 현재 패널티 여부
    double pointTot; // 등급 포인트
    int reportCnt; // 신고 받은 횟수

    // 외래키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mask_id")
    Mask mask;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    Role role;

    // Jackson 라이브러리 Annotation
    @JsonIgnore // 직렬화 시 제외 필드
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 쓰기 전용
            String password;
}
