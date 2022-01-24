export function isLogin(state) {
  return state.isLogin;
}

export function userInfo (state) {
  return state.userInfo
}

export function userId (state) {
  return state.user_id
}

export function nicknameBool (state) {
  return state.nickname_bool
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
