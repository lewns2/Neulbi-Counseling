<template>
  <div>
    <h2>게시글 생성</h2>
    <form :model="community">
      <input type="text" placeholder="title" id="title"
        v-model="community.title"
      >
      <input type="text" placeholder="description" id="description"
        v-model="community.description"
      >
      <button type="button" @click="clickCreateArticle">생성</button>          
    </form>
  </div>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
export default {
  name: "CommunityCreate",
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: computed(()=> store.getters['root/userInfo']),
    })
    const community = reactive({
      title: null, 
      description: null,
    })

    const clickCreateArticle = async function() {
      const body = {
        title: community.title,
        description: community.description,
        userId: state.userInfo.id
      }
      await store.dispatch("root/communityCreateCommunity", body)
      await router.push({ name: 'Community' })
    } 

    return { state, community, clickCreateArticle }
  }
}
</script>

<style>

</style>