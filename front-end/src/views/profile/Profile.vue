<template>
  <div class="row">
    <!-- Sidebar -->
    <div id="sidebar" class="col-3">
      <!-- <header class="px-5 py-5">
        <a href="#">My Profile</a>
      </header> -->
      <ul class="nav">
        <li class="px-5 py-5" style="margin-left: 0;" @click="goToConfession">
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
    <div id="content" class="col-9" v-if="state.select=='User'">
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
</template>

<script>
import { reactive, onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
import ProfileUser from './components/ProfileUser.vue'
import ProfileConsultant from './components/ProfileConsultant.vue'
import ProfileHistory from './components/ProfileHistory.vue'

export default {
  name: 'Profile',
  components: {
    ProfileUser,
    ProfileConsultant,
    ProfileHistory,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const route = useRoute()
    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
      select: 'User',
    })

    const selectProfile = async function (event) {
      state.select = event.target.innerText
      const body = { user_id: state.userInfo.id }
      if (state.userInfo.consultant) {
        await store.dispatch("root/profileGetConsultantProfile", state.userInfo.id)
      } else {
        store.commit("root/PROFILE_GET_CONSULTANT_PROFILE", "No")
      }
      await store.dispatch('root/profileGetConsultantLike', state.userInfo.id)
      await store.dispatch('root/profileGetHistoryConfessionMeeting', body)
      await store.dispatch('root/profileGetHistoryAdviceMeeting', body)
      await store.dispatch('root/profileGetHistoryReceivedReview', body)
      await store.dispatch('root/profileGetHistoryWrittenReview', body)
      await store.dispatch('root/profileGetHistoryCommunity', body)
      await store.dispatch('root/profileGetHistoryComment', body)   
    }

    onMounted(async() => {
      if(route.query.value==='AdviceUser') {
        state.select = 'Consultant'
      } 

      const body = { user_id: state.userInfo.id }
      if (state.userInfo.consultant) {
        await store.dispatch("root/profileGetConsultantProfile", state.userInfo.id)
      } else {
        store.commit("root/PROFILE_GET_CONSULTANT_PROFILE", "No")
      }
      await store.dispatch('root/profileGetConsultantLike', state.userInfo.id)
      await store.dispatch('root/profileGetHistoryConfessionMeeting', body)
      await store.dispatch('root/profileGetHistoryAdviceMeeting', body)
      await store.dispatch('root/profileGetHistoryReceivedReview', body)
      await store.dispatch('root/profileGetHistoryWrittenReview', body)
      await store.dispatch('root/profileGetHistoryCommunity', body)
      await store.dispatch('root/profileGetHistoryComment', body)
    })

    const goToConfession = function () {
      router.push({
        name: 'Confession'
      })
    }

    return { state, selectProfile, onMounted, goToConfession }
  }
}
</script>

<style scoped lang="scss">
@import url('https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500');

/* Toggle Styles */
#content {
  width: 75%;
  // position: absolute;
  height: 80vh;
  margin-top: 30px;
  margin-left: 20%;
}

/* Sidebar Styles */

#sidebar {
  z-index: 1000;
  position: fixed;
  // left: 20%
  margin-left: 12px;
  // margin-right: 12px;
  padding-left: 0;
  padding-right: 0;
  width: 15%;
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
  padding: 5px 5px 5px 5px;
  text-align: left;
  line-height: 30px;
}

#sidebar .nav div:hover{
  background: none;
  color: #ECEFF1;
}

</style>