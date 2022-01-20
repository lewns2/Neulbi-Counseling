export function SET_IS_LOGIN (state, isLogin) {
  state.isLogin = isLogin
}

export function SET_IS_LOGIN_ERROR (state, isLoginError) {
  state.isLoginError = isLoginError
}

export function SET_USER_INFO (state, userInfo) {
  state.isLogin = true
  state.userInfo = userInfo
}