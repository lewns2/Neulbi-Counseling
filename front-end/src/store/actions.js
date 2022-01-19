import $axios from 'axios'

export function singUp ({state}, payload) {
  console.log('signUp', state, payload)
  const url = '/user/signup'
  let body = payload
  return $axios.post(url, body)
}

