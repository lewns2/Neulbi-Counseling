<template>
  <div class="row">
    <div class="col-sm-9" align="left">
      <confession-view :userInfo="state.userInfo"></confession-view>
      <!-- 페이지네이션 -->
      <!-- <advice-pagination></advice-pagination> -->
    </div>
    <div class="col-sm-3">
      <confession-user :userInfo="state.userInfo"></confession-user>
      <br>
      <br>
      <confession-create :userInfo="state.userInfo"></confession-create>
    </div>
  </div>
</template>

<script>
import ConfessionCreate from './components/ConfessionCreate.vue'
import ConfessionView from './components/ConfessionView.vue'
import ConfessionUser from './components/ConfessionUser.vue'
import { onMounted, reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "Confession",
  components: {
    ConfessionCreate, 
    ConfessionView,
    ConfessionUser
  },
  setup() {
    const store = useStore()
    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo'])
    })

    onMounted(async() => {
      await store.dispatch('root/confessionGetMeetingList')
    })
    return { state, onMounted }
  }
}
</script>

<style></style>
