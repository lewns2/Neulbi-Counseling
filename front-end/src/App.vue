<template>
  <div id="app">
    <!-- <main-header></main-header> -->
    <router-view />
  </div>
</template>

<script>
// import MainHeader from "./views/main/components/MainHeader.vue";

export default {
  name: "App",
  // components: { MainHeader },
  setup() {
  },
};
</script>

<style lang="scss">
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: auto;
  width: 100%;
}

body {
  height: 100vh;
  margin-top: 0px!important;
  justify-content: center;
}
</style>
