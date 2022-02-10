<template>
  <div>
    <div v-for="(person, idx) in state.confessionReviewList" :key="idx">
      <div v-if="person.userId!=state.user_id">{{ person }}
        <button @click="clickIsReport(idx)">신고</button>
        <div v-if="idx==state.reviewIndex">
          <textarea
            cols="30"
            rows="5"
            v-model="state.reportMsg"
            placeholder="신고 내용을 작성해주세요."
          >
          </textarea>
          <br>
          <button type="button" @click="clickReviewReportDetail(person.userId)">보내기</button>
        </div>
      </div>
    </div>
    <router-link :to="{ name: 'Home'}">Home</router-link>
  </div>
</template>

<script>
import { reactive, computed } from '@vue/reactivity'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'

export default {
  name: "ReviewConfession",
  setup() {
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const state = reactive({
      confessionReviewList: computed(() => store.getters['root/confessionReviewList']),
      reviewIndex: -1,
      user_id: route.params.user_id,
      meeting_id: route.params.meeting_id,
      reportBool: false,
      reportMsg: "",
    })

    const clickIsReport = async function (idx) {
      if (state.reviewIndex === -1) {
        state.reviewIndex = idx
      } else {
        state.reviewIndex = -1
      }
    }

    const clickReviewReportDetail = function (reportUserId) {
      store.dispatch("root/reportDetail", {
        category: 'confession',
        reportUserId: reportUserId,
        userId: state.user_id,
        reportMsg: state.reportMsg,
      })
      state.reportMsg = ""
    }

    return { state, clickIsReport, clickReviewReportDetail }
  }
}
</script>

<style>

</style>