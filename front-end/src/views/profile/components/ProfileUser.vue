<template>
  <div>
    <p>ProfileUser</p>
    <p>{{ userInfo }}</p>
    <input type="text" placeholder="사용자 닉네임" id="nickname" v-model="state.nickname" />
    <button @click="getNickname">getNickname</button>
    <button v-if="state.nickname_bool == true" @click="modifyNickname">
      modifyNickname
    </button>
    <br>
    <br>
    <div v-for="(idx) in 10" :key="idx">
      <button @click="modifyMask(idx)">{{idx}}</button>
    </div>
    <br>
    <br>
    <p>Profile_Img_Route</p>
    <input type="text" placeholder="이미지 경로" id="profileImg" v-model="state.profileImg" />
    <button @click="modifyProfileImg">
      modifyProfileImg
    </button>
    <br>
    <br>
    <!-- modal button -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Change Password</button>
    <!-- modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Password를 변경하세요.</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="message-password" class="col-form-label">현재 비밀번호:</label>
                <input type="password" class="form-control" id="message-password" v-model="state.password">
                <label for="message-newPassword" class="col-form-label">새로운 비밀번호:</label>
                <input type="password" class="form-control" id="message-newPassword" v-model="state.newPassword">
                <label for="message-newPasswordConfirmation" class="col-form-label">새로운 비밀번호 확인:</label>
                <input type="password" class="form-control" id="message-newPasswordConfirmation" v-model="state.newPasswordConfirmation">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="modifyPassword">Send message</button>
          </div>
        </div>
      </div>
    </div>
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
			nickname_bool: computed(() => store.getters['root/profileNicknameBool']),
      password: null,
      newPassword: null,
      newPasswordConfirmation: null,
    })

    const getNickname = function () {
      store.dispatch('root/profileGetNickname', { nickname: state.nickname })
    }

    const modifyNickname = function () {
      store.dispatch('root/profileModifyNickname', { user_id: props.userInfo.id, nickname: state.nickname})
    }

    const modifyMask = function (event) {
      store.dispatch('root/profileModifyMask', { user_id: props.userInfo.id, mask_id: event})
    }

    const modifyProfileImg = function () {
      console.log(state.profileImg)
      store.dispatch('root/profileModifyProfileImg', { user_id: props.userInfo.id, profile_img: state.profileImg })
    }

    const modifyPassword = function () {
      const token = localStorage.getItem('jwt')
      store.dispatch('root/profileModifyPassword', { token: token, password: state.newPassword})
    }

    const resignUser = function () {
      store.dispatch('root/profileResignUser', { user_id: props.userInfo.id })
      .then((res) => {
        console.log('성공', res)
        store.commit("root/SET_IS_LOGIN", false)
        store.commit("root/GET_USER_INFO", null)
        localStorage.removeItem("jwt")
        router.push({
          name: 'Home'
        })
      })
      .catch((err) => {
        console.log('실패', err)
      })
    }

    return { onMounted, state, getNickname, modifyNickname, modifyMask, modifyProfileImg, modifyPassword, resignUser }
  }
}
</script>

<style>

</style>