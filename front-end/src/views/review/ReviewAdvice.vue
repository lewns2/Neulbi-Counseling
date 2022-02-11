<template>
  <div>
    <textarea cols="30" rows="10" placeholder="리뷰작성해주세요"
      v-model="state.description"
    >
    </textarea>
    <input type="number" placeholder="point" id="point"
      v-model="state.point"
    >
    <button @click="clickSubmitReview">제출</button>
    <br>
    <!-- 게시글 신고 버튼 -->
    <button type="button" @click="state.reportBool = !state.reportBool">신고</button>
    <div v-if="state.reportBool">
      <textarea
        cols="30"
        rows="5"
        v-model="state.reportMsg"
        placeholder="신고 내용을 작성해주세요."
      ></textarea
      ><br />
      <button type="button" @click="clickReviewReportDetail">보내기</button>
    </div>
    <br>
    <router-link :to="{ name: 'Home'}">Home</router-link>
  </div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
export default {
  name: "ReviewAdvice",
  setup() {
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const state = reactive({
      user_id: route.params.user_id,
      consultant_id: route.params.consultant_id,
      description: '',
      point: 0,
      reportBool: false,
      reportMsg: "",
    })

    const clickSubmitReview = async function () {
      const body = {
        userId: state.user_id,
        consultantId: state.consultant_id,
        description: state.description,
        point: state.point
      }
      await store.dispatch('root/reviewSubmitReview', body)
      await store.dispatch('root/userGetInfo', localStorage.getItem('jwt'))
    }

    const clickReviewReportDetail = function () {
      store.dispatch("root/reportDetail", {
        category: 'advice',
        reportUserId: state.consultant_id,
        userId: state.user_id,
        reportMsg: state.reportMsg,
      })
    }

    return { state, clickSubmitReview, clickReviewReportDetail }
  }
}
</script>

<style>

</style>