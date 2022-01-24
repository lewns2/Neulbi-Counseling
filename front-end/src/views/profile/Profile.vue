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
        <profile-consultant :userInfo="state.userInfo"></profile-consultant>
      </div>
      <div v-else>
        <profile-history :userInfo="state.userInfo"></profile-history>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
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
      userInfo: store.getters['root/userInfo'],
      select: 'Main'
    })

    const selectProfile = function (event) {
      state.select = event.target.textContent
    }

    return { state, selectProfile }
  }
}
</script>

<style>

</style>