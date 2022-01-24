package com.ssafy.api.service;

import com.ssafy.api.request.ConsultantRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.User;

import java.util.List;

/**
 *	상담가 프로필 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ConsultantService {
	ConsultantProfile createConsultantProfile(ConsultantRegisterPostReq consultantInfo);
	List<ConsultantListRes> getUsersByConsultant();
	List<ConsultantListRes> getConsultantByValue(String key, String value);
	List<ConsultantListRes> getUserByTopicCategory(Long topicCategoryId);

	List<User> getUserByRank();

}
