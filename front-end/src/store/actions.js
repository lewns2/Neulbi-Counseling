import $axios from "axios";
// import { useRouter } from 'vue-router'
// import jwt_decode from "jwt-decode"

export function signUp({ state }, payload) {
  console.log("signUp", state, payload);
  const url = "/user/signup";
  let body = payload;
  $axios.post(url, body);
}

export function userOverlapping({ commit }, payload) {
  console.log('Overlapping')
  const url = "/user/check"
  let body  = payload
  console.log(url, body)
  $axios.post(url, body)
    .then((res) => {
      console.log('성공', res)
      if (res.data.message === "Duplicated Email") {
        console.log('email')
        commit("SET_USER_EMAIL", false)
      }
      else if (res.data.message === "Success Email") {
        console.log('nickname')
        commit("SET_USER_EMAIL", true)
      }
      else if (res.data.message === "Duplicated Nickname") {
        console.log('nickname')
        commit("SET_USER_NICKNAME", false)
      }
      else if (res.data.message === "Success Nickname") {
        console.log('nickname')
        commit("SET_USER_NICKNAME", true)
      }
    })
    .catch((err) => {
      console.log('나나')
      // if (err.data.message === "Fail") {
      //   console.log('email')
      //   commit("SET_USER_EMAIL", false)
      // }
      // else if (err.data.message === "Fail") {
      //   console.log('nickname')
      //   commit("SET_USER_NICKNAME", false)
      // }
    })
}

export function signIn({ state, commit, dispatch }, payload) {
  console.log("signIn", state, payload);
  const url = "/user/signin";
  let body = payload;
  $axios
    .post(url, body)
    .then((res) => {
      if (res.data.accessToken) {
        let token = res.data.accessToken;
        commit("SET_IS_LOGIN", true);
        commit("SET_IS_LOGIN_ERROR", false);
        localStorage.setItem("jwt", token);
        if (state.isLogin) {
          dispatch("userGetInfo", token);
        } else {
          console.log("유저 정보 없음");
        }
      } else {
        commit("SET_IS_LOGIN", false);
        commit("SET_IS_LOGIN_ERROR", true);
        console.log(state);
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

export function userFindPassword({ state }, payload) {
  const url = '/user/resetPassword'
  let body = payload
  console.log(body)
  $axios.post(url, body)
  .then((res) => {
    console.log('성공')
  })
  .catch((err) => {
    console.log('실패')
  })
}

export function userGetInfo({ commit }, token) {
  const url = `/user/info`;
  $axios
    .get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((res) => {
      if (res.status === 200) {
        console.log('유저정보', res.data)
        commit("GET_USER_INFO", res.data);
      } else {
        console.log("유저 정보 없음");
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

export function profileGetNickname({ state, commit }, payload) {
  console.log("getNick", state, payload)
  const url = "/profile/check/nickname"
  let body = payload
  $axios.post(url, body)
    .then((res) => {
      if (res.data.message === "SUCCESS") {
        console.log('교체 가능', res)
        commit("SET_PROFILE_NICKNAME", true)
      } 
      else if(res.data.message === "FAIL") {
        console.log('교체 불가능', res)
        commit("SET_PROFILE_NICKNAME", false)
      }
    })
    .catch((err) => {
      console.log('에러', err)
    })
}

export function profileModifyNickname({ state }, payload) {
  console.log("modifyNick", state, payload)
  const user_id = payload.user_id
  const body = { nickname : payload.nickname }
  const url = `/profile/${user_id}/modify/nickname`
  $axios.post(url, body)
    .then((res) => {
      console.log('성공', res)
    })
    .catch((err) => {
      console.log('실패', err)
    })
}

export function profileModifyMask({ state }, payload) {
  const user_id = payload.user_id
  const body = { mask_id : payload.mask_id }
  console.log(body)
  const url = `/profile/${user_id}/mask`
  $axios.post(url, body)
    .then((res) => {
      console.log('성공', res)
    })
    .catch((err) => {
      console.log('실패', err)
    })
}

export function profileModifyProfileImg({ state }, payload) {
  const user_id = payload.user_id
  const body = { profile_img: payload.profileImg }
  console.log(body)
  const url = `/profile/${user_id}/profileImg`
  $axios.post(url, body)
    .then((res) => {
      console.log('성공', res)
    })
    .catch((err) => {
      console.log('실패', err)
    })
}

export function profileModifyPassword({ state }, payload) {
  const url = `/profile/modify/password`
  const token = payload.token
  const body = { password: payload.password}
  $axios
    .body(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      }, body
    })
    .then((res) => {
      console.log('비번 변경 성공')
    })
    .catch((err) => {
      console.log('비번 변경 실패')
    })
}

export function profileResignUser({ state }, payload) {
  const user_id = payload.user_id
  const url = `/profile/${user_id}/resign`
  return $axios.delete(url)
}

export function profileGetConsultantProfile({ state }, payload) {
  const url = `/profile/${payload}/consultantProfile`;
  $axios
    .get(url)
    .then((res) => {
      console.log(res);
      if (res.status == 200) {
        // commit("SET_PROFILE_CONSULTANTPROFILE", res.data);
      } else {
        console.log("오류 발생");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export function profileModifyConsultantProfile({ state }, payload) {
  const url = `/profile/${payload.id}/consultantProfile`;
  return $axios.post(url, payload);
}

export function confessionCreateChatRoom({ state }, payload) {
  console.log("confession", state, payload);
  const url = "/confession";
  let body = payload;
  $axios.post(url, body);
}

export function confessionGetView() {
  const url = "/confession";
  $axios
    .get(url)
    .then((res) => {
      console.log(res);
      return res;
    })
    .catch((err) => {
      console.log(err);
    });
}

export function confessionGetTopicView({state}, topicId) {
  // console.log(topicId)
  const url = `/confession/${topicId}`;
  $axios
    .get(url)
    .then((res) => {
      console.log(res);
      return res;
    })
    .catch((err) => {
      console.log(err);
    });
}

export function confessionSearch({state}, payload) {
  console.log(state);
  const url = `/confession/search/${payload.key}/${payload.value}/${payload.page}`;
  return $axios.get(url);
}

export function adviceGetRank({ commit }) {
  const url = `/advice/rank`;
  $axios
    .get(url)
    .then((res) => {
      if (res.status == 200) {
        commit("CLEAR_ADVICE_RANK");
        commit("SET_ADVICE_RANK", res.data);
      } else {
        console.log("오류 발생");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export function adviceGetView({ commit }) {
  const url = `/advice`;
  $axios
    .get(url)
    .then((res) => {
      if (res.status == 200) {
        commit("CLEAR_ADVICE_VIEW_TOTAL");
        commit("CLEAR_ADVICE_VIEW");
        commit("SET_ADVICE_VIEW_TOTAL", res.data);
        commit("SET_ADVICE_VIEW", res.data);
      } else {
        console.log("오류 발생");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export function adviceGetCategory({ commit }, payload) {
  const url = `/advice/${payload}`;
  $axios
    .get(url)
    .then((res) => {
      if (res.status == 200) {
        commit("CLEAR_ADVICE_VIEW_TOTAL");
        commit("SET_ADVICE_VIEW_TOTAL", res.data);
        commit("CLEAR_ADVICE_VIEW");
        commit("SET_ADVICE_VIEW", res.data);
      } else {
        console.log("오류 발생");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export function adviceCreateConsultant({ state }, payload) {
  console.log(state);
  const url = `/advice`;
  return $axios.post(url, payload);
}

export function adviceCreateChatRoom({ state }, payload) {
  console.log(state.userInfo.id);
  const url = `/chatroom/${state.userInfo.id}/${payload}`;
  return $axios.post(url);
}

export function adviceSearch({ state }, payload) {
  console.log(state);
  const url = `/advice/search/${payload.key}/${payload.value}`;
  return $axios.get(url);
}

export function CommunityGetView({ commit }) {
  const url = `/community`;
  $axios
    .get(url)
    .then((res) => {
      if (res.status == 200) {
        commit("SET_COMMUNITY_VIEW", res.data);
      } else {
        console.log("오류 발생");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

