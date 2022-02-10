<template>
  <div id="app">
    <main-header></main-header>
    <router-view />
  </div>
</template>

<script>
import MainHeader from "./views/main/components/MainHeader.vue";

export default {
  name: "App",
  components: { MainHeader },
  setup() {
  },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: auto;
  width: 100%;
  margin-left: 10px;
  margin-right: 3px;
}

body {
  height: 100vh;
  margin: 0;
  justify-content: center;
}
</style>
