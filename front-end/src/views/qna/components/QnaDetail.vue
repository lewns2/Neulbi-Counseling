<template>
  {{ state.qnaDetail }}
  <br>
  <div v-if="state.qnaDetail.userNickname==state.userInfo.nickname">
    <p>title</p>
    <input type="text" v-model="data.title">
    <p>description</p>
    <textarea cols="30" rows="10" v-model="data.description"></textarea>
    <p>잠금여부</p>
    <input type="checkbox" id="checkbox" v-model="data.rocked">
    <br>
    <button @click="clickModifyQna">수정</button>
    <button @click="clickDeleteQna">삭제</button>
  </div>
  <qna-answer
    :userInfo="state.userInfo"
    :qnaDetail="state.qnaDetail"
  >
  </qna-answer>
</template>

<script>
import QnaAnswer from './QnaAnswer.vue'

import { reactive, computed } from '@vue/reactivity'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'QnaDetail',
  components:{
    QnaAnswer,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      qnaDetail: computed(() => store.getters['root/qnaDetail']),
      userInfo: computed(() => store.getters['root/userInfo']),
    })

    const data = reactive({
      title: state.qnaDetail.title,
      description: state.qnaDetail.description,
      rocked: state.qnaDetail.isRocked,
    })

    const clickModifyQna = async function () {
      await store.dispatch('root/qnaModifyQuestion', {
        qnaId: state.qnaDetail.qnaId,
        userId: state.userInfo.id,
        title: data.title,
        description: data.description,
        isRocked: data.rocked,
      })
      await store.dispatch('root/qnaGetQuestionDetail', {
        qna_id: state.qnaDetail.qnaId,
        user_id: state.userInfo.id
      })
    }

    const clickDeleteQna = async function () {
      await store.dispatch('root/qnaDeleteQuestion', {
        qnaId: state.qnaDetail.qnaId,
        userId: state.userInfo.id,
      })
      await store.dispatch('root/qnaGetQuestionList', state.userInfo.id)
      await router.push({ name: 'Qna' })
    }

    return { state, data, clickModifyQna, clickDeleteQna }
  }
}
</script>

<style>

</style>