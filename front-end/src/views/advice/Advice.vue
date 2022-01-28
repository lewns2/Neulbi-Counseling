<template>
  <div class="row">
    <advice-create></advice-create>
  </div>
  <div class="row">
    <div class="col-sm-8" align="left">
      <advice-view :userInfo="state.userInfo"></advice-view>
      <advice-pagination></advice-pagination>
    </div>
    <div class="col-sm-4">
      <!-- <advice-user></advice-user> -->
      <advice-rank></advice-rank>
    </div>
  </div>
</template>

<script>
import AdviceCreate from "./components/AdviceCreate.vue";
import AdvicePagination from "./components/AdvicePagination.vue";
import AdviceRank from "./components/AdviceRank.vue";
import AdviceUser from "./components/AdviceUser.vue";
import AdviceView from "./components/AdviceView.vue";

import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "Advice",
  components: {
    AdviceCreate,
    AdvicePagination,
    AdviceRank,
    AdviceUser,
    AdviceView,
  },
  setup () {
    const store = useStore()
    const state = reactive({
      userInfo: store.getters['root/userInfo'],
    })
    return { state }
  }
};
</script>

<style></style>
