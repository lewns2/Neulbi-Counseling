package com.ssafy.api.service;

import com.ssafy.api.request.ProfileModifyPasswordPutReq;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.User;
import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ProfileService {
	Integer getUserByNickname(String ProfileCheckInfo);
	void modifyUserByNickname(String UserNickName);
	Optional<User> findByUserId(Long UserId);
	void modifyPasswordByUserId(ProfileModifyPasswordPutReq profileModifyPassword, Long id);
	Optional<ConsultantProfile> getConsultantProfile(Long userId);
}
