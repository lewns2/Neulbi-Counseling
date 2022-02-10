<template>
  <div>
    <p>{{ state.userInfo }}</p>
    <div>
      <button @click="selectProfile" value="Main">Main</button>
      <button @click="selectProfile" value="User">User</button>
      <button @click="selectProfile" value="Consultant">Consultant</button>
      <button @click="selectProfile" value="History">History</button>
    </div>
    <div>
      <div v-if="state.select==='Main'">
        <profile-main :userInfo="state.userInfo"></profile-main>
      </div>
      <div v-else-if="state.select==='User'">
        <profile-user :userInfo="state.userInfo"></profile-user>
      </div>
      <div v-else-if="state.select==='Consultant'">
        <profile-consultant
          :userInfo="state.userInfo"
        >
        </profile-consultant>
      </div>
      <div v-else>
        <profile-history
          :userInfo="state.userInfo"
        >
        </profile-history>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import ProfileMain from './components/ProfileMain.vue'
import ProfileUser from './components/ProfileUser.vue'
import ProfileConsultant from './components/ProfileConsultant.vue'
import ProfileHistory from './components/ProfileHistory.vue'

export default {
  name: 'Profile',
  components: {
    ProfileMain,
    ProfileUser,
    ProfileConsultant,
    ProfileHistory,
  },
  setup() {
    const store = useStore()

    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
      select: 'Main',
    })

    const selectProfile = async function (event) {
      const body = { user_id: state.userInfo.id }
      await store.dispatch("root/profileGetConsultantProfile", state.userInfo.id)
      await store.dispatch('root/profileGetConsultantLike', state.userInfo.id)
      await store.dispatch('root/profileGetHistoryMeeting', body)
      await store.dispatch('root/profileGetHistoryReview', body)
      await store.dispatch('root/profileGetHistoryCommunity', body)
      await store.dispatch('root/profileGetHistoryComment', body)
      state.select = event.target.textContent
    }

    onMounted(async() => {
      const body = { user_id: state.userInfo.id }
      await store.dispatch("root/profileGetConsultantProfile", state.userInfo.id)
      await store.dispatch('root/profileGetConsultantLike', state.userInfo.id)
      await store.dispatch('root/profileGetHistoryMeeting', body)
      await store.dispatch('root/profileGetHistoryReview', body)
      await store.dispatch('root/profileGetHistoryCommunity', body)
      await store.dispatch('root/profileGetHistoryComment', body)
    })

    return { state, selectProfile, onMounted }
  }
}
</script>

<style>

</style>