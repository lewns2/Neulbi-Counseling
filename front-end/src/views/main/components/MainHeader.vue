<template>
  <div id="nav">
    <span v-if="isLogin">
      <router-link :to="{ name: 'Home' }">Home</router-link>
    </span>
    <span v-else>
      <router-link :to="{ name: 'SignIn' }">SignIn</router-link>
    </span>
  </div>
</template>

<script>

export default {

}
</script>

<style>

</style>