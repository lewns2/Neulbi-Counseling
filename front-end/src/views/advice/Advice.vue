<template>
  
  
  
  <div class="row">
    <div class="col-sm-9" align="left">
      <advice-view :userInfo="state.userInfo"></advice-view>
      <!-- <advice-pagination></advice-pagination> -->
    </div>
    <div class="col-sm-3">
      <advice-user :userInfo="state.userInfo"></advice-user>
      <advice-create :userInfo="state.userInfo"></advice-create>
      <advice-rank></advice-rank>
    </div>
  </div>
</template>

<script>
import AdviceCreate from "./components/AdviceCreate.vue";
// import AdvicePagination from "./components/AdvicePagination.vue";
import AdviceRank from "./components/AdviceRank.vue";
import AdviceUser from "./components/AdviceUser.vue";
import AdviceView from "./components/AdviceView.vue";

import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "Advice",
  components: {
    AdviceCreate,
    // AdvicePagination,
    AdviceRank,
    AdviceUser,
    AdviceView,
  },
  setup () {
    const store = useStore()
    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
    })

    onMounted(async() => {
      await store.dispatch("root/adviceGetConsultantList", state.userInfo.id)
      await store.dispatch("root/adviceGetRankList")
    })
    return { state, onMounted }
  }
};
</script>

<style scoped></style>
