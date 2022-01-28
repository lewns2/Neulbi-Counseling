package com.ssafy.api.controller;

import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.api.response.CommunityListRes;
import com.ssafy.api.response.CommunityViewRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.CommunityService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Community;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 *
 * 1.
 * 2.
 * 3.
 * 4.
 * 5.
 */
@Api(value = "커뮤니티 API", tags = {"community"})
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;


    @GetMapping()
    @ApiOperation(value = "게시글 목록 정보", notes = "<strong>전체 게시글 목록</strong>을 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<Page<CommunityListRes>> communityList(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        // user 테이블에서 is_consultant 가 true인 사람의 point_tot, user_id,

        Page<Community> list = communityService.getAllCommunity(pageable);
        return ResponseEntity.status(200).body(CommunityListRes.of(list));
    }


    @PostMapping()
    @ApiOperation(value = "게시글 등록", notes = "<strong>게시글</strong>을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> communityRegister(@RequestBody @ApiParam(value = "게시글 정보", required = true) CommunityRegisterPostReq registerInfo) {
        // user 테이블에서 is_consultant 가 true인 사람의 point_tot, user_id,

        Community community = communityService.createCommunity(registerInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/{community_id}")
    @ApiOperation(value = "게시글 상세보기", notes = "<strong>선택한 게시글</strong>을 본다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<CommunityViewRes> communityView(@RequestBody @ApiParam(value = "게시글 번호", required = true) @PathVariable(name = "community_id") Long communityId) {
        // user 테이블에서 is_consultant 가 true인 사람의 point_tot, user_id,

        Community community = communityService.getCommunity(communityId);

        return ResponseEntity.status(200).body(CommunityViewRes.of(community));
    }

    @DeleteMapping("/{community_id}")
    @ApiOperation(value = "게시글 삭제", notes = "<strong>선택한 게시글</strong>을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> communityDelete(@RequestBody @ApiParam(value = "게시글 번호", required = true) @PathVariable(name = "community_id") Long communityId) {
        // user 테이블에서 is_consultant 가 true인 사람의 point_tot, user_id,

       Community community = communityService.deleteCommunity(communityId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }




}
