<template>
  <div>
    <h1>Meeting History</h1>
    <br>
    <h3>Confession History</h3>
    <span v-for="(confession, idx) in state.profileHistoryConfession" :key="idx">
      {{ confession }}
    </span>
    <hr>
    <h3>Advice History</h3>
    <span v-for="(advice, idx) in state.profileHistoryAdvice" :key="idx">
      {{ advice }}
    </span>
    <hr>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileHistoryMeeting",
  setup () {
    const store = useStore()
    const state = reactive ({
      profileHistoryConfession: computed(() => store.getters['root/profileHistoryConfession']),
      profileHistoryAdvice: computed(() => store.getters['root/profileHistoryAdvice']),
    })

    return { state }
  }
}
</script>

<style>

</style>