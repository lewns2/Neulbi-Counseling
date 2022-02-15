<template>
  <div>
    <main-header></main-header>
    <div class="container">
      <profile-history-confession></profile-history-confession>
      <profile-history-advice></profile-history-advice>
      <profile-history-community></profile-history-community>
      <profile-history-comment></profile-history-comment>
      <profile-history-review :userInfo="state.userInfo"></profile-history-review>
    </div>
  </div>
</template>

<script>
import MainHeader from "../main/components/MainHeader.vue";
import ProfileHistoryConfession from './ProfileHistoryConfession.vue'
import ProfileHistoryAdvice from './ProfileHistoryAdvice.vue'
import ProfileHistoryReview from './ProfileHistoryReview.vue'
import ProfileHistoryCommunity from './ProfileHistoryCommunity.vue'
import ProfileHistoryComment from './ProfileHistoryComment.vue'

import { reactive } from 'vue'

export default {
  name: 'ProfileHistory',
  components: {
    MainHeader,
    ProfileHistoryConfession,
    ProfileHistoryAdvice,
    ProfileHistoryReview,
    ProfileHistoryCommunity,
    ProfileHistoryComment
  },
  props: {
    userInfo: Object,
  },
  setup (props) {
    const state = reactive({
      userInfo: props.userInfo,
    })

    return { state }
  }
}
</script>

<style>

</style>