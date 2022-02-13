import VueAxios from 'vue-axios'
import axios from 'axios'
// import config from '../config'

const BASE_URL = 'https://i6e202.p.ssafy.io:443/'
const DEFAULT_ACCEPT_TYPE = 'application/json'

axios.defaults.baseURL = BASE_URL
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE

export default { VueAxios, axios }
