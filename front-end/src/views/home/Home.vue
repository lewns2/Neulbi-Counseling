<template>
  <div class="home">
    <home-main :userInfo="state.userInfo"></home-main>
    <home-service></home-service>
    <home-age></home-age>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

import HomeMain from './components/HomeMain.vue'
import HomeService from './components/HomeService.vue'
import HomeAge from './components/HomeAge.vue'

export default {
  name: 'Home',
  components: {
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
