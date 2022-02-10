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

    public AdviceAndConfessionListRes(Meeting meetings, MeetingHistory history) {
        this.meetingHistory = history;
        this.meeting = meetings;
    }
}
