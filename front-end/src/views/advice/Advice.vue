<template>
  <div class="row">
    <advice-create></advice-create>
  </div>
  <div class="row">
    <div class="col-sm-8" align="left">
      <advice-view></advice-view>
      <advice-pagination></advice-pagination>
    </div>
    <div class="col-sm-4">
      <advice-user></advice-user>
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

export default {
  name: "Advice",
  components: {
    AdviceCreate,
    AdvicePagination,
    AdviceRank,
    AdviceUser,
    AdviceView,
  },
};
</script>

<style></style>
