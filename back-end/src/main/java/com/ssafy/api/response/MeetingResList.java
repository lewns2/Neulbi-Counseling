package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Meeting;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 방 목록 ([POST] /) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("MeetingResList")
public class MeetingResList extends BaseResponseBody {
    @ApiModelProperty(name="방 목록", example = "방 목록")
    List<Meeting> meetingResList;

    public static MeetingResList of(Integer statusCode, String message, List<Meeting> meetingList) {
        MeetingResList res = new MeetingResList();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMeetingResList(meetingList);

        return res;
    }

}
