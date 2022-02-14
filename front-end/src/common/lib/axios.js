import VueAxios from "vue-axios";
import axios from "axios";
// import config from '../config'

<<<<<<< HEAD
const BASE_URL = 'https://i6e202.p.ssafy.io:8443/'
const DEFAULT_ACCEPT_TYPE = 'application/json'
=======
const BASE_URL = "https://i6e202.p.ssafy.io:8443/";
const DEFAULT_ACCEPT_TYPE = "application/json";
>>>>>>> cde89a116673cd68170fb7a479b767f76e849e32

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers["Content-Type"] = DEFAULT_ACCEPT_TYPE;

export default { VueAxios, axios };
