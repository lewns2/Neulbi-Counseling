package com.ssafy.api.service;

import com.ssafy.api.request.ProfileModifyPasswordPutReq;
import com.ssafy.db.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ProfileService {
	Integer getUserByNickname(String ProfileCheckInfo);
	Optional<User> findByUserId(Long UserId);
	int modifyProfileImg(Long userId, MultipartFile profileImgInfo) throws IOException;
	void modifyPasswordByUserId(ProfileModifyPasswordPutReq profileModifyPassword, Long id);
	Optional<ConsultantProfile> getConsultantProfile(Long userId);
	List<ConsultantProfile> getMyConsultantList(Long userId);
	Page<Community> getCommunityList(Pageable pageable, Long userId);
	Page<Comment> getCommentList(Pageable pageable, Long userId);
	void createMyConsultant(Long userId, Long consultantId);
	void deleteMyConsultant(Long userId, Long consultantId);
	void modifyUserNickname(Long userId, String nickname);
	void modifyMask(Long userId, int maskId);
	void modifyMaskBack(Long userId, int maskId);
}
