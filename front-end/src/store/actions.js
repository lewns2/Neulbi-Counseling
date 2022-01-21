import $axios from 'axios'
import jwt_decode from "jwt-decode";

export function signUp ({state}, payload) {
  console.log('signUp', state, payload)
  const url = '/user/signup'
  let body = payload
  return $axios.post(url, body)
}

export function signIn ({state}, payload) {
  console.log('signIn', state, payload)
  const url = '/user/signin'
  let body = payload
  return $axios.post(url, body)
}

export function getUserInfo({ commit }, token) {
  const user_id = jwt_decode(token).userid
  const url = `/user/${user_id}`
  $axios.get(url)
  .then(res=> {
    if (res.data.message === "Success") {
      commit("root/SET_USER_INFO", res.data.userInfo)
    } else {
      console.log("유저 정보 없음")
    }
  })
  .catch(err=>{
    console.log(err)
  })
}