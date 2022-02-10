<template>
  <div>
    <hr>
    <h1>Review History</h1>
    <br>
    <h3>Received Review</h3>
    <span v-for="(receivedReview, idx) in state.profileHistoryReceivedReview" :key="idx">
      {{ receivedReview }}
    </span>
    <hr>
    <h3>Written Review</h3>
    <span v-for="(writtenReview, idx) in state.profileHistoryWrittenReview" :key="idx">
      {{ writtenReview }}
    </span>
    <hr>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileHistoryReview",
  setup () {
    const store = useStore()
    const state = reactive ({
      profileHistoryReceivedReview: computed(() => store.getters['root/profileHistoryReceivedReview']),
      profileHistoryWrittenReview: computed(() => store.getters['root/profileHistoryWrittenReview']),
    })

    return { state }
  }
}
</script>

<style>

</style>