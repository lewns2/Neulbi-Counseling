package com.ssafy.api.service;

import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ProfileRepository;
import com.ssafy.db.repository.ProfileRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
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
	ProfileRepositorySupport profileRepositorySupport;

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
}
