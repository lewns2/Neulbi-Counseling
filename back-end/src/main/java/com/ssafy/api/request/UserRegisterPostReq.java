package com.ssafy.api.request;

import com.ssafy.db.entity.Mask;
import com.ssafy.db.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	String nickname; // 별명
	@ApiModelProperty(name="유저 이메일", example="ssafy_web")
	String email; // 이메일 == 아이디
//	String profile_img; // 프로필 이미지 주소
//	boolean isConsultant; // 상담가 신청 여부
//	boolean isPenalty; // 현재 패널티 여부
//	double pointTot; // 등급 포인트
//	int reportCnt; // 신고 받은 횟수
//
//	Mask mask; // 가면
//	Role role; // 권한

	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
}
