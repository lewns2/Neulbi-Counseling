import state from "./state";

export function MAIN_EMAIL_BOOL(state, data) {
  state.mainEmailBool = data
}

export function MAIN_NICKNAME_BOOL(state, data) {
  state.mainNicknameBool= data
}

export function MAIN_IS_SIGNIN(state, data) {
  state.mainIsSignIn = data;
}

export function GET_USER_INFO(state, userInfo) {
  state.userInfo = userInfo;
}

export function PROFILE_GET_NICKNAME(state, data) {
  state.profileNicknameBool = data;
}

export function PROFILE_GET_CONSULTANT_PROFILE(state, data) {
  state.profileConsultantProfile = data;
}

export function PROFILE_CONSULTANT_LIKE(state, data) {
  state.profileConsultantLikeActive = data[0]
  state.profileConsultantLike = []
  for (let i = 1; i < data.length; i++) {
    state.profileConsultantLike.push({
      id: data[i].id,
      nickname: data[i].nickname,
      topicCategoryName: data[i].topicCategoryName,
      pointTot: data[i].pointTot,
      consultingCnt: data[i].consultingCnt,
      description: data[i].description,
    })
  }
}

export function PROFILE_GET_HISTORY_MEETING(state, data) {
  state.profileHistoryConfession = []
  state.profileHistoryConfession = data.confession
  state.profileHistoryAdvice = []
  state.profileHistoryAdvice = data.advice
}

export function PROFILE_GET_HISTORY_REVIEW(state, data) {
  state.profileHistoryReceivedReview = data.receivedReview
  state.profileHistoryWrittenReview = data.writtenReview
}

export function PROFILE_GET_HISTORY_COMMUNITY(state, data) {
  state.profileHistoryCommunity = data
}

export function PROFILE_GET_HISTORY_COMMENT(state, data) {
  state.profileHistoryComment = data
}

export function CONFESSION_GET_LAST_PAGE_NUM(state, data) {
  state.confessionLastPageNum = 0
  state.confessionLastPageNum = data
}

export function CONFESSION_GET_CONSULTANT_LIST(state, data) {
  state.confessionMeetingList = []
  let min = Math.min(6, data.length)
  for (let i = 0; i < min; i++) {
    state.confessionMeetingList.push({
      ownerId: data[i].ownerId,
      ownerNickname: data[i].ownerNickname,
      title: data[i].nickntitleame,
      description: data[i].description,
      meetingId: data[i].meetingId,
      participants: data[i].participants,
      currJoinParticipants: data[i].currJoinParticipants,
      topicCategoryId: data[i].topicCategoryId,
      topicCategoryName: data[i].topicCategoryName,
      mask: data[i].mask,
      profileImg: data[i].profileImg
    })
  }
}

export function CONFESSION_CREATE_MEETING_INFO(state, data) {
  state.confessionMeetingInfo = []
  state.confessionMeetingInfo.push({
    meetingId: data.meeting.id,
    ownerId: data.ownerId,
    topicCategoryId: data.topicCategoryId,
    title: data.meeting.title,
    description: data.meeting.description,
    isActive: data.meeting.active,
    participants: data.meeting.participants
  })
  state.confessionMeetingInfo = state.confessionMeetingInfo[0]
}

export function CONFESSION_REVIEW_LIST(state, data) {
  state.confessionReviewList = []
  state.confessionReviewList = data
}

export function ADVICE_GET_RANK_LIST(state, data) {
  state.adviceRankList = []
  data.forEach((rank) => {
    state.adviceRankList.push({
      id: rank.id,
      nickname: rank.nickname,
      pointTot: rank.pointTot,
      profileImg: rank.profileImg,
    });
  });
}

export function ADVICE_GET_LAST_PAGE_NUM(state, data) {
  state.adviceLastPageNum = 0
  state.adviceLastPageNum = data
}

export function ADVICE_GET_CONSULTANT_LIST(state, data) {
  state.adviceConsultantList = []
  let min = Math.min(6, data.length)
  for (let i = 0; i < min; i++) {
    state.adviceConsultantList.push({
      id: data[i].id,
      nickname: data[i].nickname,
      profileImg: data[i].profileImg,
      pointTot: data[i].pointTot,
      topicCategoryName: data[i].topicCategoryName,
      description: data[i].description,
      consultingCnt: data[i].consultingCnt,
      favConsultant: data[i].favConsultant,
    })
  }
}

export function COMMUNITY_GET_COMMUNITY_LIST(state, data) {
  state.communityList = data
}

export function COMMUNITY_GET_LAST_PAGE_NUM(state, data) {
  state.communityLastPageNum = 0
  state.communityLastPageNum = data
}

export function COMMUNITY_GET_DETAIL(state, payload) {
  state.communityComment = payload.commentListRes
  state.communityDetail = {
    description: payload.description,
    community_id: payload.id,
    like: payload.like,
    likeCnt: payload.likeCnt,
    title: payload.title,
    user_id: payload.userId,
    userNickname: payload.userNickname,
    viewCnt: payload.viewCnt
  }
}

export function CHATROOM_GET_LIST(state, data) {
  state.chatRoomList = data
}

export function CHATROOM_GET_DETAIL_ID(state, data) {
  state.chatRoomId = data
}

export function CHATROOM_GET_DETAIL_NICKNAME(state, data) {
  state.chatRoomNickname = data
}

export function CHATROOM_GET_DETAIL_MESSAGE(state, data) {
  state.chatRoomMessage = data
}

export function CHATROOM_GET_USER_LIST(state, data) {
  state.chatRoomUserList = data
}

export function ADVICE_CREATE_MEETING_INFO(state, data) {
  state.adviceMeetingInfo = []
  state.adviceMeetingInfo.push({
    meetingId: data.meeting.id,
    ownerId: data.ownerId,
    topicCategoryId: data.topicCategoryId,
    title: data.meeting.title,
    description: data.meeting.description,
    isActive: data.meeting.active,
    participants: data.meeting.participants
  })
  state.adviceMeetingInfo = state.adviceMeetingInfo[0]
}

export function CHATROOM_GET_MEETING_IS_ACTIVE(state, data) {
  state.meetingIsActive = false
  state.meetingIsActive = data
}

export function MEETING_PARTICIPANT_LIMIT(state, data) {
  state.meetingParticipantLimit = true
  console.log(state.meetingParticipantLimit, 'limit')
  state.meetingParticipantLimit = data
}

export function NOTICE_GET_LAST_PAGE_NUM(state, data) {
  state.noticeLastPageNum = 0
  state.noticeLastPageNum = data
}

export function NOTICE_GET_NOTICE_LIST(state, data) {
  state.noticeList = []
  state.noticeList = data
}

export function NOTICE_GET_NOTICE_DETAIL(state, data) {
  state.noticeDetail = data
}

export function QNA_GET_LAST_PAGE_NUM(state, data) {
  state.qnaLastPageNum = 0
  state.qnaLastPageNum = data
}

export function QNA_GET_QUESTION_LIST(state, data) {
  state.qnaList = []
  state.qnaList = data
}

export function QNA_GET_QUESTION_DETAIL(state, data) {
  state.qnaDetail = data
}

export function QNA_GET_QUESTION_ANSWER(state, data) {
  state.qnaAnswerList = []
  let min = Math.min(6, data.length)
  for (let i = 0; i < min; i++) {
    state.qnaAnswerList.push({
      answerId: data[i].answerId,
      date: data[i].date,
      description: data[i].description,
      userId: data[i].userId,
    })
  }
}