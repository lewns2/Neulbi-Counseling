package com.ssafy.api.service;

import com.ssafy.api.request.ConsultantRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *	상담가 프로필 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ConsultantService {
	ConsultantProfile createConsultantProfile(ConsultantRegisterPostReq consultantInfo);
	Page<ConsultantProfile> getAllConsultant(Pageable pageable);
	Page<ConsultantProfile> getConsultantByValue(String key, String value, Pageable pageable);
	Page<ConsultantProfile> getUserByTopicCategory(Long topicCategoryId, Pageable pageable);

	List<User> getUserByRank();

}
