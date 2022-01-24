package com.ssafy.api.controller;

/*
 * 고해성사 관련 API 요청 처리 컨트롤러
 */

import com.ssafy.api.request.ConfessionPostReq;
import com.ssafy.api.response.MeetingRes;
import com.ssafy.api.response.MeetingResList;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.common.model.response.BaseResponseBody;

import com.ssafy.db.entity.Meeting;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "고해성사 API", tags = {"Confession"})
@RestController
@RequestMapping("/confession")
public class ConfessionController {

    @GetMapping()
    @ApiOperation(value = "넘겨 받을 값 없음", notes = "고해성사 모든 방 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MeetingResList> findConfession() {

        // meeting에서 meeting_category_id가 고해성사인 방을 모두 검색 하여 돌려줌
        // @PathVariable int noticeSeq

        // 테스트
        System.out.println("1111111111111111111111111111");
        System.out.println("고해성사 방 전부 검색");

        List<Meeting> resList = new ArrayList<>();

//        for(int i = 1; i <= 3; i++) {
//            Meeting meeting = new Meeting();
//            meeting.setTitle("방" + i);
//            meeting.setDescription("방설명" + i);
//            meeting.setParticipants(i);
//            resList.add(meeting);
//        }

        // 일단 넘겨주는 값 없음
        return ResponseEntity.status(200).body( MeetingResList.of(200, "Success", resList) );
    }

    @GetMapping("/{topicCategoryId}")
    @ApiOperation(value = "topicCategoryId : 주제별 id", notes = "주제별 고해성사 방 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<MeetingResList> findTopicCategoryConfession(@PathVariable int topicCategoryId) {

        // topicCategoryId를 topic_category에서 찾아서 해당 주제의 방을 전부 넘겨줌

        // 테스트
        System.out.println(topicCategoryId);
        System.out.println("고해성사 주제별 방 전부 검색");

        List<Meeting> resList = new ArrayList<>();

//        for(int i = 1; i <= 3; i++) {
//            Meeting meeting = new Meeting();
//            meeting.setTitle("방" + i);
//            meeting.setDescription("방설명" + i);
//            meeting.setParticipants(i);
//            resList.add(meeting);
//        }

        // 일단 넘겨주는 값 없음
        return ResponseEntity.status(200).body( MeetingResList.of(200, "Success", resList) );
    }

    @GetMapping("/confession/search/{key}/{value}")
    @ApiOperation(value = "key, value", notes = "원하는 검색을 통해 고해성사 방 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ConfessionPostReq> findSearchConfession(@PathVariable int key, @PathVariable int value) {

        // key와 value를 통해 고해성사방을 검색한다.
        // service단에 key와 value를 넘겨줘서 해당 값에 맞는 적절한 방 검색

        // 테스트
        System.out.println(key);
        System.out.println(value);

        // 일단 넘겨주는 값 없음
        return ResponseEntity.status(200).body( null );
    }

    @PostMapping()
    @ApiOperation(value = "방 제목, 방 설명, 주제, 제한인원 수", notes = "방 제목, 방 설명, 주제, 제한인원 수를 통해 고해성사 방을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> createConfession(@RequestBody @ApiParam(value="방 생성 정보", required = true) ConfessionPostReq confessionInfo) {

        // meeting Service 쪽이랑 연결하고 meetingPostRes? 이러한 응답 객체로 넘겨주면 될 듯 함.
        // 고민 상담 부분이랑 같이 처리해야 함.
        // 고해성사에서 필요한 부분이랑 고민 상담에 필요한 부분 다름

        // 테스트
        System.out.println(confessionInfo.getTitle());
        System.out.println(confessionInfo.getDescription());
        System.out.println(confessionInfo.getParticipants());
        System.out.println(confessionInfo.getTopicCategoryId());

        // 일단 넘겨주는 값 없음
        return ResponseEntity.status(200).body( BaseResponseBody.of(200, "Success"));
    }
}
