<template>
  <p>title</p>
  <input type="text" v-model="state.title">
  <p>description</p>
  <textarea cols="30" rows="10" v-model="state.description"></textarea>
  <p>잠금여부</p>
  <input type="checkbox" id="checkbox" v-model="state.rocked">
  <br>
  <button @click="clickCreateQna">생성</button>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'QnaCreate',
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
      title: '',
      description: '',
      rocked: false
    })

    const clickCreateQna = async function () {
      await store.dispatch('root/qnaCreateQuestion', {
        userId: state.userInfo.id,
        title: state.title,
        description: state.description,
        isRocked: state.rocked
      })
      await store.dispatch('root/qnaGetQuestionList', state.userInfo.id)
      await router.push({ name: 'Qna' })
    }

    return { state, clickCreateQna }
  }
}
</script>

<style>

</style>