<template>
  {{ state.noticeDetail }}
  <div v-if="state.userInfo.role==='ADMIN'">
    <input type="text" v-model="data.title">
    <input type="text" v-model="data.description">
    <button @click="clickModifyNotice">수정</button>
    <button @click="clickDeleteNotice">삭제</button>
  </div>
</template>

<script>
import { reactive, computed } from '@vue/reactivity'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'NoticeDetail',
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      noticeDetail: computed(() => store.getters['root/noticeDetail']),
      userInfo: computed(() => store.getters['root/userInfo']),
    })
    const data = reactive({
      title: state.noticeDetail.title,
      description: state.noticeDetail.description,
    })

    const clickModifyNotice = async function () {
      await store.dispatch('root/noticeModifyNotice', {
        noticeId: state.noticeDetail.noticeId,
        userId: state.userInfo.id,
        title: data.title,
        description: data.description,
      })
      await store.dispatch('root/noticeGetNoticeDetail', state.noticeDetail.noticeId)
    }

    const clickDeleteNotice = async function () {
      await store.dispatch('root/noticeDeleteNotice', {
        noticeId: state.noticeDetail.noticeId,
        userId: state.userInfo.id,
      })
      await store.dispatch('root/noticeGetNoticeList')
      await router.push({ name: 'Notice' })
    }

    return { state, data, clickModifyNotice, clickDeleteNotice }
  }
}
</script>

<style>

</style>