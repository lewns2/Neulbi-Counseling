package com.ssafy.api.response;


import com.ssafy.db.entity.Meeting;
import com.ssafy.db.entity.MeetingHistory;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AdviceResponse")
@NoArgsConstructor
@AllArgsConstructor
public class AdviceAndConfessionListRes {
    MeetingHistory meetingHistory;
    Meeting meeting;
    // 방장의 프로필 이미지
    String userProfileImg;
    // 방장의 마스크 이미지 아이디
    int userMaskId;
    Long topicCategoryId;

    public AdviceAndConfessionListRes(Meeting meetings, MeetingHistory history) {
        this.meetingHistory = history;
        this.meeting = meetings;

        this.userProfileImg = meetings.getUser().getProfileImg();
        this.userMaskId = meetings.getUser().getMaskId();
        this.topicCategoryId = meetings.getTopicCategory().getId();

    }
}
