package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MeetingHistoryRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QMeeting qMeeting = QMeeting.meeting;
    QMeetingHistory qMeetingHistory = QMeetingHistory.meetingHistory;

    public Long findMeetingHistoryJoinCountByMeetingRoomId(Long meetingRoomId) {
        Long joinCount = jpaQueryFactory.select(qMeetingHistory)
                                        .from(qMeetingHistory)
                                        .where(qMeetingHistory.meeting.id.eq(meetingRoomId).and(qMeetingHistory.action.eq("JOIN")))
                                        .fetchCount();

        return joinCount;
    }

    public MeetingHistory findMeetingHistoryByUserIdAndMeetingId(Long userId, Long meetingId) {
        MeetingHistory meetingHistory = jpaQueryFactory.select(qMeetingHistory)
                .from(qMeetingHistory)
                .where(qMeetingHistory.meeting.id.eq(meetingId).and(qMeetingHistory.user.id.eq(userId)))
                .fetchOne();

        return meetingHistory;
    }

    public MeetingHistory findMeetingHistoryJOINByUserIdAndMeetingId(Long userId, Long meetingId) {
        MeetingHistory meetingHistory = jpaQueryFactory.select(qMeetingHistory)
                .from(qMeetingHistory)
                .where(qMeetingHistory.meeting.id.eq(meetingId)
                        .and(qMeetingHistory.user.id.eq(userId))
                        .and(qMeetingHistory.action.eq("JOIN")))
                .fetchOne();

        return meetingHistory;
    }

    public MeetingHistory findMeetingHistoryCreateByUserIdAndMeetingId(Long userId, Long meetingId) {
        MeetingHistory meetingHistory = jpaQueryFactory.select(qMeetingHistory)
                .from(qMeetingHistory)
                .where(qMeetingHistory.meeting.id.eq(meetingId)
                        .and(qMeetingHistory.user.id.eq(userId))
                        .and(qMeetingHistory.action.eq("CREATE")))
                .fetchOne();

        return meetingHistory;
    }

    public List<MeetingHistory> findMeetingHistoriesByMeetingId(Long meetingId) {
        List<MeetingHistory> meetingHistory = jpaQueryFactory.select(qMeetingHistory)
                                            .from(qMeetingHistory)
                                            .where(qMeetingHistory.meeting.id.eq(meetingId))
                                            .fetch();

        return meetingHistory;
    }

    public long countMeetingHistoriesByMeetingIdAndActionCreateOrActionJoin(Long meetingId) {
        return jpaQueryFactory
                .select(qMeetingHistory.id)
                .from(qMeetingHistory, qMeeting)
                .where(
                        qMeetingHistory.meeting.id.eq(meetingId)
                                .and(qMeetingHistory.action.eq("CREATE")
                                        .or(qMeetingHistory.action.eq("JOIN")))
                )
                .distinct()
                .fetchCount();
    }
}
