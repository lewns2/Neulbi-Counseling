package com.ssafy.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "커뮤니티 API", tags = {"community"})
@RestController
@RequestMapping("/community")
public class CommunityController {
}
