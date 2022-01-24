<template>
  <div>
    <p>ProfileUser</p>
    <p>{{ userInfo }}</p>
    <input type="text" placeholder="사용자 닉네임" id="nickname" v-model="state.nickname" />
    <button @click="checkNickname">checkNickname</button>
    <button v-if="state.nickname_bool == true" @click="changeNickname">
      changeNickname
    </button>
    <br>
    <br>
    <div v-for="(idx) in 10" :key="idx">
      <button @click="changeMask(idx)">{{idx}}</button>
    </div>
    <br>
    <br>
    <p>Profile_Img_Route</p>
    <input type="text" placeholder="이미지 경로" id="profileImg" v-model="state.profileImg" />
    <button @click="changeProfile">
      changeProfile
    </button>
    <br>
    <br>
    <button @click="resignUser">회원탈퇴</button>
  </div>
</template>

<script>
import { onMounted , reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'ProfileUser',
  props: {
    userInfo : Array,
  },
  setup(props) {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      userInfo: null,
      nickname: null,
      maskId : null,
      profileImg : null,
			nickname_bool: computed(() => store.getters['root/nicknameBool']),
    })

    const checkNickname = function () {
      store.dispatch('root/profileCheckNickname', { nickname: state.nickname })
    }

    const changeNickname = function () {
      store.dispatch('root/profileChangeNickname', { user_id: props.userInfo.id, nickname: state.nickname})
    }

    const changeMask = function (event) {
      store.dispatch('root/profileChangeMask', { user_id: props.userInfo.id, mask_id: event})
    }

    const changeProfile = function () {
      console.log(state.profileImg)
      store.dispatch('root/profileChangeProfileImg', { user_id: props.userInfo.id, profile_img: state.profileImg })
    }

    const resignUser = function () {
      store.dispatch('root/profileResignUser', { user_id: props.userInfo.id })
      .then((res) => {
        console.log('성공', res)
        store.commit("root/SET_IS_LOGIN", false)
        store.commit("root/SET_USER_INFO", null)
        localStorage.removeItem("jwt")
        router.push({
          name: 'Home'
        })
      })
      .catch((err) => {
        console.log('실패', err)
      })
    }

    return { onMounted, state, checkNickname, changeNickname, changeMask, changeProfile, resignUser }
  }
}
</script>

<style>

</style>