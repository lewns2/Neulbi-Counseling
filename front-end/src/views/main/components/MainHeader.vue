<template>
  <div id="nav" class="py-3">
    <span v-if="state.isSignIn" class="row">
      <span class="col-1">
      </span>
      <span class="col-1" style="text-align: left;">
        <router-link :to="{ name: 'Home' }" style="font-size: 25px;">홈</router-link>
      </span>
      <span class="col-4 d-flex justify-content-around">
        <router-link :to="{ name: 'Confession' }" style="font-size: 25px;">고해성사</router-link>
        <router-link :to="{ name: 'Advice' }" style="font-size: 25px;">고민상담</router-link>
        <router-link :to="{ name: 'Community' }" style="font-size: 25px;">게시판</router-link>
        <p @click="goToChatRoom" style="font-size: 25px;">채팅방</p>
      </span>
      <span class="col-1">
      </span>
      <span class="col-2 d-flex justify-content-evenly">
        <router-link :to="{ name: 'Notice' }" style="font-size: 20px;">공지사항</router-link>
        <router-link :to="{ name: 'Qna' }" style="font-size: 20px;">QnA</router-link>
      </span>
      <span class="col-2 d-flex justify-content-evenly">
        <p @click="clickLogout" style="font-size: 20px;">로그아웃</p>
        <p @click="goToProfile" style="font-size: 25px;">프로필</p>
      </span>
    </span>
    
    <span v-else class="row">
      <span class="col-9"></span>
      <span class="col-2 d-flex justify-content-evenly">
        <router-link :to="{ name: 'SignUp' }" style="font-size: 25px;">SignUp</router-link>
        <router-link :to="{ name: 'SignIn' }" style="font-size: 25px;">SignIn</router-link>
      </span>
    </span>


    

  </div>
</template>

<script>
import { reactive, computed } from "vue"
import { useStore } from "vuex"
import { useRouter } from 'vue-router'

export default {
  name: "MainHeader",
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      isSignIn: computed(() => store.getters["root/mainIsSignIn"]),
      userInfo: computed(() => store.getters["root/userInfo"])
    })

    const clickLogout = function () {
      store.commit("root/MAIN_IS_SIGNIN", false)
      store.commit("root/GET_USER_INFO", null)
      store.commit("root/MAIN_EMAIL_BOOL", false)
      store.commit("root/MAIN_NICKNAME_BOOL", false)
      localStorage.removeItem("jwt")
      router.push({ name: 'Home' })
    }

    const goToChatRoom = async function () {
      await store.dispatch("root/chatRoomGetList", { userId: state.userInfo.id })   
      await router.push({
        name: 'ChatRoom',
        params: {
          user_id: state.userInfo.id
        }
      })
    }

    const goToProfile = function () {
      router.push({
        name: 'Profile',
        params: {
          user_id: state.userInfo.id
        }
      })
    }

    return { state, clickLogout, goToChatRoom, goToProfile }
  }
}
</script>

<style scoped>
#nav {
  position: fixed-top;
  background-color: #c2d6f8;
}

a, p {
  text-decoration: none;
  color: white;
  font-weight: bold;
  line-height: 40px;
  margin-bottom: 0px;
}

a:hover, p:hover {
  text-decoration: underline;
  cursor: pointer;
}
</style>
