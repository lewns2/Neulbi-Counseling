<template>
  <p>댓글</p>
  <input type="text" v-model="state.description">
  <button @click="clickCreateAnswer">댓글 달기</button>

  <div v-for="(answer, idx) in state.qnaAnswerList" :key="idx">
    {{ answer }}
    <input type="text" v-model="answer.description">
    <button @click="clickModifyAnswer(answer)">수정</button>
    <button @click="clickDeleteAnswer(answer.answerId)">삭제</button>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'QnaAnswer',
  props: {
    userInfo: Object,
    qnaDetail: Object,
  },
  setup(props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      qnaDetail: props.qnaDetail,
      qnaAnswerList: computed(() => store.getters['root/qnaAnswerList']),
      description: '',
    })

    const clickCreateAnswer = async function () {
      await store.dispatch('root/qnaCreateAnswer', {
        userId: state.userInfo.id,
        qnaId: state.qnaDetail.qnaId,
        description: state.description,
      })
      await store.dispatch('root/qnaGetQuestionDetail', {
        qna_id: state.qnaDetail.qnaId,
        user_id: state.userInfo.id
      })
    }

    const clickModifyAnswer = async function (answer) {
      await store.dispatch('root/qnaModifyAnswer', {
        userId: state.userInfo.id,
        answerId: answer.answerId,
        description: answer.description,
      })
      await store.dispatch('root/qnaGetQuestionDetail', {
        qna_id: state.qnaDetail.qnaId,
        user_id: state.userInfo.id
      })
    }

    const clickDeleteAnswer = async function (answerId) {
      await store.dispatch('root/qnaDeleteAnswer', {
        userId: state.userInfo.id,
        answerId: answerId,
      })
      await store.dispatch('root/qnaGetQuestionDetail', {
        qna_id: state.qnaDetail.qnaId,
        user_id: state.userInfo.id
      })
    }

    return { state, clickCreateAnswer, clickModifyAnswer, clickDeleteAnswer }
  }
}
</script>

<style>

</style>