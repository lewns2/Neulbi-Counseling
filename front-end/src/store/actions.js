import $axios from "axios";
// import jwt_decode from "jwt-decode"

export function signUp({ state }, payload) {
  console.log("signUp", state, payload);
  const url = "/user/signup";
  let body = payload;
  $axios.post(url, body)
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
      console.log("fail");
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
        console.log('확인')
      } else {
        console.log("유저 정보 없음");
      }
    })
    .catch((err) => {
      console.log(err);
    });
}
