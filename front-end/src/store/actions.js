import $axios from "axios";
// import { useRouter } from 'vue-router'
// import jwt_decode from "jwt-decode"

export function signUp({ state }, payload) {
  console.log("signUp", state, payload);
  const url = "/user/signup";
  let body = payload;
  $axios.post(url, body);
}

export function signIn({ state, commit, dispatch }, payload) {
  console.log("signIn", state, payload);
  const url = "/user/signin";
  let body = payload;
  $axios.post(url, body)
    .then((res) => {
      if (res.data.accessToken) {
        let token = res.data.accessToken;
        commit("SET_IS_LOGIN", true);
        commit("SET_IS_LOGIN_ERROR", false);
        localStorage.setItem("jwt", token);
        if (state.isLogin) {
          dispatch("getUserInfo", token);
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

export function getUserInfo({ commit }, token) {
  const url = `/user/info`;
  $axios
    .get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((res) => {
      if (res.status === 200) {
        commit("SET_USER_INFO", res.data);
        // const router = useRouter()
        // const user_id = state.user_id
        // console.log('router', router, user_id)
			  // router.push({
        //   name: 'Profile',
        //   params: {
        //     user_id: user_id,
        //   }
        // })
      } else {
        console.log("유저 정보 없음");
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

export function profileCheckNickname({ state, commit }, payload) {
  console.log("Nick", state, payload)
  const url = "/profile/check/nickname"
  let body = payload
  $axios.post(url, body)
    .then((res) => {
      if (res.data.message === "SUCCESS") {
        console.log('교체 가능')
        commit("PROFILE_CHECK_NICKNAME", true)
      } 
      else if(res.data.message === "FAIL") {
        console.log('교체 불가능')
        commit("PROFILE_CHECK_NICKNAME", false)
      }
    })
    .catch((err) => {
      console.log('에러', err)
    })
}

export function profileChangeNickname({ state }, payload) {
  console.log("ChangeNick", state, payload)
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

export function profileChangeMask({ state }, payload) {
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

export function profileChangeProfileImg({ state }, payload) {
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

export function profileResignUser({ state }, payload) {
  const user_id = payload.user_id
  const url = `/profile/${user_id}/resign`
  return $axios.delete(url)
}

export function createConfessionChatRoom({ state }, payload) {
  console.log("confession", state, payload);
  const url = "/confession";
  let body = payload;
  $axios.post(url, body)
}

export function getConfessionView() {
  const url = "/confession";
  $axios.get(url)
  .then((res) => {
    console.log(res);
    return res;
  })
  .catch((err)=> {
    console.log(err);
  })
}

export function getTopicConfessionView({state}, topicId) {
  // console.log(topicId)
  const url = `/confession/${topicId}`;
  $axios.get(url)
  .then((res) => {
    console.log(res);
    return res;
  })
  .catch((err)=> {
    console.log(err);
  })
}

export function getAdviceRank({ commit }) {
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

export function getAdviceView({ commit }) {
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

export function getAdviceCategory({ commit }, payload) {
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

export function beConsultant({ state }, payload) {
  console.log(state);
  const url = `/advice`;
  return $axios.post(url, payload);
}

export function modifyMask({ commit }, payload) {
  const url = `/user/mask/${payload}`;
  $axios
    .put(url)
    .then((res) => {
      console.log(res);
      if (res.status == 200) {
        commit("SET_USER_INFO_MASK", res.data);
      } else {
        console.log("오류 발생");
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export function createChatRoom({ state }, payload) {
  console.log(state.userInfo.id);
  const url = `/chatroom/${state.userInfo.id}/${payload}`;
  return $axios.post(url);
}

export function adviceSearch({ state }, payload) {
  console.log(state);
  const url = `/advice/search/${payload.key}/${payload.value}`;
  return $axios.get(url);
}

