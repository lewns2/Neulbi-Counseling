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
	@ApiModelProperty(name="유저 닉네임", example="ssafy_khm")
	String nickname; // 별명
	@ApiModelProperty(name="유저 이메일", example="ssafy_web")
	String email; // 이메일 == 아이디
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
}
