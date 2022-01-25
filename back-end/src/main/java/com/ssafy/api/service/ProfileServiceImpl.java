package com.ssafy.api.service;

import com.ssafy.api.request.ProfileModifyPasswordPutReq;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	UserRepository	userRepository;

	@Autowired
	ConsultantRepository consultantRepository;

	@Autowired
	ConsultantRepositorySupport consultantRepositorySupport;
	
	@Autowired
	ProfileRepositorySupport profileRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Integer getUserByNickname(String nickname) {

		//User profile = profileRepositorySupport.findByNickname(nickname);
		int cnt = profileRepositorySupport.findByNickname(nickname);
		return cnt;
	}

	@Override
	public void modifyUserByNickname(String UserNickName) {

	}

	@Override
	public Optional<User> findByUserId(Long UserId) {
		Optional<User> user = profileRepositorySupport.findById(UserId);
		return user;
	}

	@Override
	public void modifyPasswordByUserId(ProfileModifyPasswordPutReq profileModifyPassword, Long id) {
		Optional<User> userInfo = this.findByUserId(id);

		String modifyPassword = passwordEncoder.encode(profileModifyPassword.getPassword());

		userInfo.ifPresent(user1 -> {
			user1.resetPassword(modifyPassword);
			userRepository.save(user1);
		});
	}

	@Override
	public Optional<ConsultantProfile> getConsultantProfile(Long userId) {

		Optional<ConsultantProfile> con = consultantRepositorySupport.findByUserId(userId);

		return con;
	}
}
