package com.ssafy.api.controller;

import com.ssafy.api.request.ProfileCheckPostReq;
import com.ssafy.api.request.ProfileModifyMaskPostReq;
import com.ssafy.api.request.ProfileModifyNicknamePostReq;
import com.ssafy.api.service.ProfileService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"Profile"})
@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/check/nickname")
	@ApiOperation(value = "닉네임 중복 확인", notes = "<strong>닉네임</strong>이 이미 존재하는지 확인한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> check(
			@RequestBody @ApiParam(value="닉네임 중복확인 체크", required = true) ProfileCheckPostReq checkInfo) {

//		밸류값
		String nickname = checkInfo.getNickname();
		int profile_cnt = 0;
		profile_cnt = profileService.getUserByNickname(nickname);
		if(profile_cnt == 0) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
		}
		else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "FAIL"));
		}
	}

	@PostMapping("/{user_id}/modify/nickname")
	@ApiOperation(value = "닉네임 변경", notes = "<strong>닉네임</strong>을 변경한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> changeNickname(
			@PathVariable("user_id") Long id, @RequestBody @ApiParam(value="닉네임 변경", required = true) ProfileModifyNicknamePostReq nickname) {

		Optional<User> user = profileService.findByUserId(id);
		String changeNick = nickname.getNickname();
		user.ifPresent(user1 -> {
			//user1.setNickname(changeNick);
			userRepository.save(user1);
		});

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}

	@PostMapping("/{user_id}/mask")
	@ApiOperation(value = "마스크 변경", notes = "<strong>닉네임</strong>을 변경한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> changeNickname(
			@PathVariable("user_id") Long id, @RequestBody @ApiParam(value="마스크 변경", required = true) ProfileModifyMaskPostReq mask_id) {

		Optional<User> user = profileService.findByUserId(id);
		Long changeMask = mask_id.getMask_id();
		user.ifPresent(user1 -> {
			//user1.setMask(changeMask);
			userRepository.save(user1);
		});

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}

	@DeleteMapping("/{user_id}/resign")
	@ApiOperation(value = "회원 탈퇴", notes = "<strong>회원탈퇴</strong>")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> changeNickname(@PathVariable("user_id") Long id) {

		Optional<User> user = profileService.findByUserId(id);

		user.ifPresent(user1 -> {
			userRepository.delete(user1);
		});

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}
}
