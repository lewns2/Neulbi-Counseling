import $axios from 'axios'

export function signUp ({state}, payload) {
  console.log('signUp', state, payload)
  const url = '/user/signup'
  let body = payload
  console.log($axios.post(url, body))
  return $axios.post(url, body)
}

export function signIn ({state}, payload) {
  console.log('signIn', state, payload)
  const url = '/user/signin'
  let body = payload
  console.log($axios.post(url, body))
  return $axios.post(url, body)
}

