package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Meeting;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 방 목록 ([POST] /) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("MeetingResResponse")
public class MeetingRes {
    @ApiModelProperty(name="방 제목", example = "취직 문제로 고민인 사람")
    String title;
    @ApiModelProperty(name="방 설명", example = "진짜 힘든사람만 들어오세요")
    String description;
    @ApiModelProperty(name="제한 인원수", example = "3")
    int participants;
    @ApiModelProperty(name="해당 방 연결주소", example = "sdfadff22424")
    String meetingAddress;

    public static MeetingRes of(Meeting meeting) {
        MeetingRes res = new MeetingRes();
        res.setTitle(meeting.getTitle());
        res.setDescription(meeting.getDescription());
        res.setParticipants(meeting.getParticipants());
        res.setMeetingAddress("연결 주소 : 임시로 넣어놓은 문자열");

        return res;
    }
}
