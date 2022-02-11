<template>
  <div class="row">
    <!-- Sidebar -->
    <div id="sidebar" class="col-3">
      <!-- <header class="px-5 py-5">
        <a href="#">My Profile</a>
      </header> -->
      <ul class="nav">
        <li class="px-5 py-5" style="margin-left: 0;" @click="selectProfile" value='Main'>
          <div>
            Main
          </div>
        </li>
        <li class="px-5 py-5" style="margin-left: 0;" @click="selectProfile" value='User'>
          <div>
            User
          </div>
        </li>
        <li class="px-5 py-5" style="margin-left: 0;" @click="selectProfile" value='Consultant'>
          <div>
            Consultant
          </div>
        </li>
        <li class="px-5 py-5" style="margin-left: 0;" @click="selectProfile" value='History'>
          <div>
            History
          </div>
        </li>
      </ul>
    </div>
    <!-- Content -->
    <div id="content" class="col-9" v-if="state.select=='Main'">
      <profile-main :userInfo="state.userInfo" class="container-fluid"></profile-main>
    </div>
    <div id="content" class="col-9" v-else-if="state.select=='User'">
      <profile-user :userInfo="state.userInfo" class="container-fluid"></profile-user>
    </div>
    <div id="content" class="col-9" v-else-if="state.select=='Consultant'">
      <profile-consultant
          :userInfo="state.userInfo"
          class="container-fluid"
        >
        </profile-consultant>
    </div>
    <div id="content" class="col-9" v-else-if="state.select=='History'">
      <profile-history
          :userInfo="state.userInfo"
          class="container-fluid"
        >
        </profile-history>
    </div>

  </div>
    <!-- <div>
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
    </div> -->
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
      state.select = event.target.innerText
      console.log(event)
      console.log(state.select)
      const body = { user_id: state.userInfo.id }
      if (state.userInfo.consultant) {
        await store.dispatch("root/profileGetConsultantProfile", state.userInfo.id)
      } else {
        store.commit("root/PROFILE_GET_CONSULTANT_PROFILE", "No")
      }
      await store.dispatch('root/profileGetConsultantLike', state.userInfo.id)
      await store.dispatch('root/profileGetHistoryMeeting', body)
      await store.dispatch('root/profileGetHistoryReview', body)
      await store.dispatch('root/profileGetHistoryCommunity', body)
      await store.dispatch('root/profileGetHistoryComment', body)   
    }

    onMounted(async() => {
      const body = { user_id: state.userInfo.id }
      if (state.userInfo.consultant) {
        await store.dispatch("root/profileGetConsultantProfile", state.userInfo.id)
      } else {
        store.commit("root/PROFILE_GET_CONSULTANT_PROFILE", "No")
      }
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

<style scoped lang="scss">
@import url('https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500');
/* 
body {
  overflow-x: hidden;
  font-family: 'Roboto', sans-serif;
  font-size: 16px;
} */

/* Toggle Styles */
#content {
  width: 75%;
  // position: absolute;
  height: 100%;
  margin-top: 30px;
}

/* Sidebar Styles */

#sidebar {
  z-index: 1000;
  // position: absolute;
  // left: 20%
  margin-left: 12px;
  // margin-right: 12px;
  padding-left: 0;
  padding-right: 0;
  width: 20%;
  height: 100vh;
  overflow-y: auto;
  background: #c2d6f8;
  -webkit-transition: all 0.5s ease;
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

#sidebar header {
  background-color: #263238;
  font-size: 25px;
  line-height: 52px;
  text-align: center;
}

#sidebar header p {
  color: #fff;
  display: block;
  text-decoration: none;
}

#sidebar header p:hover {
  color: #fff;
}

#sidebar .nav {
  display: flex;
  flex-direction: column;
}

#sidebar .nav div{
  background: none;
  color: white;
  font-size: 25px;
  font-weight: bold;
  padding: 5px 24px;
  text-align: center;
  line-height: 30px;
}

#sidebar .nav div:hover{
  background: none;
  color: #ECEFF1;
}

</style>