import { createApp, h } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./common/lib/store";
import VueAxios from "./common/lib/axios";
import axios from "./common/lib/axios";


// global styles
// import "./assets/main.css";

// const router = new useRouter({
//   routes,
//   scrollBehavior (to, from, savedPosition) {
//     return { x: 0, y: 0}
//   }
// })

const app = createApp({
  render: () => h(App),
});
app.use(VueAxios, axios);
app.use(store);
app.use(router);

app.mount("#app");
