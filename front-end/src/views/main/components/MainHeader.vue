<template>
  <div id="nav">
    <p>{{ state.isLogin }}</p>
    <span v-if="state.isLogin">
      <router-link :to="{ name: 'Home' }">Home</router-link>
      <button @click="clickLogout">Logout</button>
      <router-link :to="{ name: 'Advice' }">Advice</router-link>
    </span>
    <span v-else>
      <router-link :to="{ name: 'SignUp' }">SignUp</router-link>
      <router-link :to="{ name: 'SignIn' }">SignIn</router-link>
    </span>

    <!-- 고해성사로 이동 -->
    <router-link :to="{ name: 'Confession' }">Confession</router-link>
    

  </div>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
export default {
  setup() {
    const store = useStore();
    const state = reactive({
      isLogin: computed(() => store.getters["root/isLogin"]),
    });

    const clickLogout = function () {
      store.commit("root/SET_IS_LOGIN", false);
      store.commit("root/SET_USER_INFO", null);
      localStorage.removeItem("jwt");
    };
    return { state, clickLogout };
  },
};
</script>

<style scoped>
#nav {
  position: fixed-top;
}
</style>
