<template>
  <div>
    <h1>My Consultant</h1>
    <br>
    <span v-for="(consultant, idx) in state.profileConsultantLike" :key="idx">
      {{ consultant }}
    </span>
    <hr>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileConsultantList",
  props:{
    userInfo: Object,
  },
  setup (props) {
    const store = useStore()
    const state = reactive ({
      userInfo: props.userInfo,
      profileConsultantLike: computed(() => store.getters['root/profileConsultantLike']),
    })

    return { state }
  }
}
</script>

<style>

</style>