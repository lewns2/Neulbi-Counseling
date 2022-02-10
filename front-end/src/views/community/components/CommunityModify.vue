<template>
  <div>
    {{ state.communityDetail }}
    <p>제목: 
      <input type="text" id="title" v-model="data.title"
      >
    </p>
    <p>내용: 
      <textarea cols="30" rows="10" v-model="data.description">
      </textarea>
    </p>
    <button @click="communityModifyDetail">수정</button>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'CommunityModify',
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
      communityDetail: computed(() => store.getters["root/communityDetail"]),
    })
    const data = reactive({
      title: state.communityDetail.title,
      description: state.communityDetail.description,
    })
    const communityModifyDetail = async function () {
      await store.dispatch('root/communityModifyDetail',
      {
       user_id: state.userInfo.id,
       community_id: state.communityDetail.community_id,
       title: data.title,
       description: data.description
      })
      await router.push({
        name: 'CommunityDetail',
        params: {
          community_id: state.communityDetail.community_id
        }
      })
    }

    return { state, data, communityModifyDetail }
  }
}
</script>

<style>

</style>