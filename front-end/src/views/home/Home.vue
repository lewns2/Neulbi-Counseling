<template>
  <main-header></main-header>
  <div class="home">
    <home-main :userInfo="state.userInfo"></home-main>
    <home-service></home-service>
    <home-age></home-age>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import MainHeader from "../main/components/MainHeader.vue";
import HomeMain from './components/HomeMain.vue'
import HomeService from './components/HomeService.vue'
import HomeAge from './components/HomeAge.vue'

export default {
  name: 'Home',
  components: {
    MainHeader,
    HomeMain,
    HomeService,
    HomeAge
  },
  setup() {
    const store = useStore()

    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
    })

    return { state }
  }
}
</script>

<style scoped>
.home {
  height: 100vh;
  width: 101%;
}
</style>