<template>
  <div class="home">
    <p>homepage</p>
    <router-link class="btn" :to="{ name: 'Login' }">Login</router-link>
  </div>
</template>

<script>

export default {
  name: 'Home',
  setup() {
  }
}
</script>
