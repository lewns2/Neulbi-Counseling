export function SET_IS_LOGIN(state, isLogin) {
  state.isLogin = isLogin;
}

export function SET_IS_LOGIN_ERROR(state, isLoginError) {
  state.isLoginError = isLoginError;
}

export function GET_USER_INFO(state, userInfo) {
  state.userInfo = userInfo;
}

export function SET_USER_EMAIL(state, bool) {
  state.email_bool = bool;
}

export function SET_USER_NICKNAME(state, bool) {
  state.nickname_bool = bool;
}

export function SET_PROFILE_NICKNAME (state, bool) {
  state.pro_nickname_bool = bool
}

export function SET_PROFILE_CONSULTANTPROFILE(state, profileConsultant) {
  state.profileConsultant = profileConsultant;
}

export function SET_ADVICE_RANK(state, adviceRank) {
  adviceRank.forEach((rank) => {
    state.adviceRank.push({
      id: rank.id,
      nickname: rank.nickname,
      pointTot: rank.pointTot,
    });
  });
}

export function CLEAR_ADVICE_RANK(state) {
  state.adviceRank = [];
}

export function SET_ADVICE_VIEW_TOTAL(state, adviceViewTotal) {
  adviceViewTotal.forEach((view) => {
    state.adviceViewTotal.push({
      id: view.id,
      nickname: view.nickname,
      profileImg: view.profileImg,
      pointTot: view.pointTot,
      topicCategoryId: view.topicCategoryId,
      description: view.description,
      consultingCnt: view.consultingCnt,
    });
  });
}

export function SET_ADVICE_VIEW(state, adviceView) {
  let min = Math.min(6, adviceView.length);
  for (let i = 0; i < min; i++) {
    state.adviceView.push({
      id: adviceView[i].id,
      nickname: adviceView[i].nickname,
      profileImg: adviceView[i].profileImg,
      pointTot: adviceView[i].pointTot,
      topicCategoryId: adviceView[i].topicCategoryId,
      description: adviceView[i].description,
      consultingCnt: adviceView[i].consultingCnt,
    });
  }
}

export function CLEAR_ADVICE_VIEW_TOTAL(state) {
  state.adviceViewTotal = [];
}

export function CLEAR_ADVICE_VIEW(state) {
  state.adviceView = [];
}

export function SET_ADVICE_PAGINATION(state, payload) {
  let min = Math.min(payload.pageNum * 6 - 1, payload.totalNum - 1);
  for (let i = (payload.pageNum - 1) * 6; i <= min; i++) {
    state.adviceView.push({
      id: state.adviceViewTotal[i].id,
      nickname: state.adviceViewTotal[i].nickname,
      profileImg: state.adviceViewTotal[i].profileImg,
      pointTot: state.adviceViewTotal[i].pointTot,
      topicCategoryId: state.adviceViewTotal[i].topicCategoryId,
      description: state.adviceViewTotal[i].description,
      consultingCnt: state.adviceViewTotal[i].consultingCnt,
    });
  }
}

export function SET_ADVICE_PAGENUM(state, payload) {
  state.advicePageNum = payload;
}

export function SET_COMMUNITY_VIEW(state, communityView) {
    let min = Math.min(6, communityView.length);
    for (let i = 0; i < min; i++) {
    state.adviceView.push({
      id: communityView[i].id,
      title: communityView[i].title,
      author: communityView[i].author,
    });
  }
}
