export function isLogin(state) {
  return state.isLogin;
}

export function userInfo(state) {
  return state.userInfo;
}

export function userId(state) {
  return state.user_id;
}

export function userEmailBool(state) {
  return state.email_bool;
}

export function userNicknameBool(state) {
  return state.nickname_bool;
}

export function profileNicknameBool(state) {
  return state.pro_nickname_bool;
}

export function profileConsultant(state) {
  return state.profileConsultant;
}

export function adviceRank(state) {
  return state.adviceRank;
}

export function adviceView(state) {
  return state.adviceView;
}

export function adviceTotalLength(state) {
  return state.adviceViewTotal.length;
}

export function advicePageNum(state) {
  console.log(state.advicePageNum);
  return state.advicePageNum;
}

export function communityView(state) {
  return state.communityView;
}

export function communityDetail(state) {
  return state.communityDetail;
}

export function communityLike(state) {
  return state.communityLike;
}

export function communityComment(state) {
  return state.communityComment;
}
