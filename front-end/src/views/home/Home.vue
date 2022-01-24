<template>
  <div class="home">
    <home-main></home-main>
    <home-service></home-service>
    <home-age></home-age>
  </div>
</template>

<script>
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
  }
}
</script>
