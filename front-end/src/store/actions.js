import $axios from "axios";
// import jwt_decode from "jwt-decode"

export function signUp({ state }, payload) {
  console.log("signUp", state, payload);
  const url = "/user/signup";
  let body = payload;
  $axios
    .post(url, body)
    .then((res) => {
      console.log(res);
      // 보내는데 성공하면 로그인창으로 이동
    })
    .catch((err) => {
      console.log(err);
    });
}

export function signIn({ state, commit, dispatch }, payload) {
  console.log("signIn", state, payload);
  const url = "/user/signin";
  let body = payload;
  console.log("hi");
  $axios
    .post(url, body)
    .then((res) => {
      if (res.data.accessToken) {
        console.log("data?", res.data);
        console.log(state);
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
      return res.data;
    })
    .catch((err) => {
      console.log("fail");
      console.log(err);
    });
}

export function getUserInfo({ commit }, token) {
  const url = `/user/token`;
  console.log(token);
  $axios
    .get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((res) => {
      console.log(res);
      if (res.data.message === "Success") {
        commit("SET_USER_INFO", res.data.userInfo);
      } else {
        console.log("유저 정보 없음");
      }
    })
    .catch((err) => {
      console.log(err);
    });
}
