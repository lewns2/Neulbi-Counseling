<template>
  <div id="app">
    <MainHeader />
    <div class="content">
      <router-view/>
    </div>
  </div>
</template>

<script>
import MainHeader from './views/main/components/MainHeader.vue'

export default ({
  components: { MainHeader },
  setup() {

  }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
