<template>
  <div id="app">
    <MainHeader />
    <div class="content">
      <router-view />
    </div>
  </div>
</template>

<script>
import MainHeader from "./views/main/components/MainHeader.vue";

export default {
  name: "App",
  components: { MainHeader },
  setup() {},
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  width: 100%;
}
</style>
