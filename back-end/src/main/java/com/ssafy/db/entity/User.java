package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ssafy.db.converter.MaskAttributeConverter;
import com.ssafy.db.converter.RoleAttributeConverter;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Convert;
import javax.persistence.Column;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@ToString
// @DynamicInsert // insert 시 null 인필드 제외
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseEntity{
    String nickname; // 별명
    String email; // 이메일 == 아이디
    String profileImg; // 프로필 이미지 주소
    boolean isConsultant; // 상담가 신청 여부
    boolean isPenalty; // 현재 패널티 여부
    double pointTot; // 등급 포인트
    int reportCnt; // 신고 받은 횟수


    @Column(name="mask_id")
    @Convert(converter = MaskAttributeConverter.class)
    String mask;

    @Column(name="role_id")
    @Convert(converter = RoleAttributeConverter.class)
    String role;

    // Jackson 라이브러리 Annotation
    @JsonIgnoreProperties// 직렬화 시 제외 필드
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 쓰기 전용
            String password;

    // 패스워드 랜덤 비밀번호로 초기화
    public void resetPassword(String password) {
        this.password = password;
    }

    // 컨설턴트로 등록
    public void registerConsultant(boolean isConsultant){
        this.isConsultant = isConsultant;
    }

}
