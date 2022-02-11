<template>
  <div>
    <h1>My Communities</h1>
    <span v-for="(community, idx) in state.profileHistoryCommunity" :key="idx">
      {{ community }}
    </span>
    <hr>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileHistoryCommunity",
  setup () {
    const store = useStore()
    const state = reactive ({
      profileHistoryCommunity: computed(() => store.getters['root/profileHistoryCommunity']),
    })

    return { state }
  }
}
</script>

<style>

</style>