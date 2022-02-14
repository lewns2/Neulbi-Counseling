<template>
  <div>
    <profile-history-meeting></profile-history-meeting>
    <profile-history-community></profile-history-community>
    <profile-history-comment></profile-history-comment>
    <profile-history-review :userInfo="state.userInfo"></profile-history-review>
  </div>
</template>

<script>
import ProfileHistoryMeeting from './ProfileHistoryMeeting.vue'
import ProfileHistoryReview from './ProfileHistoryReview.vue'
import ProfileHistoryCommunity from './ProfileHistoryCommunity.vue'
import ProfileHistoryComment from './ProfileHistoryComment.vue'

import { reactive } from 'vue'

export default {
  name: 'ProfileHistory',
  components: {
    ProfileHistoryMeeting,
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