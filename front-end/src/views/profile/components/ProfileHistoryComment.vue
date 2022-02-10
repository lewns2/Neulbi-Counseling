<template>
  <div>
    <h1>My Comments</h1>
    <span v-for="(comment, idx) in state.profileHistoryComment" :key="idx">
      {{ comment }}
    </span>
    <hr>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileHistoryComment",
  setup () {
    const store = useStore()
    const state = reactive ({
      profileHistoryComment: computed(() => store.getters['root/profileHistoryComment'])
    })

    return { state }
  }
}
</script>

<style>

</style>