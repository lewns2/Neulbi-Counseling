package com.ssafy.api.controller;

import com.ssafy.api.request.ConsultantRegisterPostReq;
import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.ConsultantService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 *
 * 1. 상담가 등록(완) - 중복 등록 막기 (unique 처리, create table sql 수정)
 * 2. 상담가 목록(완)
 * 3. 상담가 주제별 목록(완)
 * 4. 상담가 검색 (key, value)(완) -> 닉네임만, 추가 검색 기능 확인 필요
 * 5. 상담가 상세 정보(get)(완) -> 상담가 정보를 넘길때 함께 넘기게 만듬(user_id, 닉네임, 프로필 이미지, 포인트 총합, 주제 카테고리, 한줄설명, 컨설팅횟수)
 * 7. 랭킹별 정렬(get)(완) -> point_tot로 정렬하고 상위 10명(user_id, 닉네임, 포인트 총합, 프로필 이미지)
 */
@Api(value = "유저 API", tags = {"advice"})
@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    ConsultantService consultantService;



    @GetMapping()

    @ApiOperation(value = "상담가 정보", notes = "<strong>각 상담가의 유저 정보</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<Page<ConsultantListRes>> consultantList(@PageableDefault(page = 0, size = 10)Pageable pageable) {
        // user 테이블에서 is_consultant 가 true인 사람의 point_tot, user_id,

        Page<ConsultantListRes> cons = consultantService.getUsersByConsultant(pageable);

        return ResponseEntity.status(200).body(cons);

    }

    @GetMapping("/{topic_category_id}")
    @ApiOperation(value = "주제별 상담가 정보", notes = "<strong>각 주제별 상담가의 유저 정보</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<List<ConsultantListRes>> consultantListByCategory(@PathVariable(value = "topic_category_id") Long topicCategoryId) {
        // consultantProfile 테이블에서 topic_category_id가 매개변수와 같은 데이터

        List<ConsultantListRes> cons = consultantService.getUserByTopicCategory(topicCategoryId);

        return ResponseEntity.status(200).body(cons);

    }

    @PostMapping()
    @ApiOperation(value = "상담가 프로필 생성", notes = "<strong>상담가 프로필</strong>을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> consultantRegister(@RequestBody @ApiParam(value = "상담가 프로필 정보", required = true) ConsultantRegisterPostReq registerInfo) {


        ConsultantProfile consultantProfile = consultantService.createConsultantProfile(registerInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

    }

    @GetMapping("/rank")
    @ApiOperation(value = "상담가 순위 정보", notes = "<strong>전체 상담가 순위</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<List<ConsultantListRes>> consultantRanking() {
        // user 테이블에서 is_consultant 가 true인 사람의 point_tot이 상위 10위인 사람


        List<User> users = consultantService.getUserByRank();

        return ResponseEntity.status(200).body(ConsultantListRes.of(users));

    }

    @GetMapping("/search/{key}/{value}")
    @ApiOperation(value = "상담가 검색", notes = "<strong>검색한 상담가 정보</strong>를 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<List<ConsultantListRes>> consultantSearch(@PathVariable String key, @PathVariable String value) {


        List<ConsultantListRes> cons = consultantService.getConsultantByValue(key,value);

        return ResponseEntity.status(200).body(cons);

    }


}


