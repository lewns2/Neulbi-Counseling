<template>
  <div>
    <h1>ProfileUser</h1>
    <br>
    <h3>닉네임 변경</h3>
    <input type="text" placeholder="사용자 닉네임" id="nickname" v-model="state.nickname">
    <button @click="clickgetNickname">getNickname</button>
    <p>{{ state.profileNicknameBool }}</p>
    <button v-if="state.profileNicknameBool == true" @click="clickmodifyNickname">
      modifyNickname
    </button>
    <br>
    <hr>
    <br>
    <h3>마스크 변경</h3>
    <div v-for="(idx) in 10" :key="idx">
      <button @click="clickmodifyMask(idx)">{{idx}}</button>
    </div>
    <br>
    <hr>
    <br>
    <h3>프로필 이미지 변경</h3>
    <!-- 내가 추가한 부분 -->
    <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
      <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
      <img style="width: 35%; max-width: 600px;" v-bind:src="state.profileImgThumbnail">
    </div>
    <input class="align-middle" @change="changeImgFile" type="file"><!-- C -->
    <button class="btn btn-primary" @click="clickmodifyProfileImg"> 변경하기 </button>
    <!----------------->

    <br>
    <hr>
    <br>
    <h3>비밀번호 변경</h3>
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
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="clickmodifyPassword">Send message</button>
          </div>
        </div>
      </div>
    </div>
    <br>
    <hr>
    <br>
    <h3>회원 탈퇴</h3>
    <button @click="clickresignUser">회원탈퇴</button>
  </div>
</template>

<script>
import { onMounted , reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'ProfileUser',
  props: {
    userInfo : Object,
  },
  setup(props) {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      userInfo: props.userInfo,
      nickname: null,
      maskId : null,
      profileImg : null,
      profileImgThumbnail : props.userInfo.profileImg !== null ? props.userInfo.profileImg : 'https://t1.daumcdn.net/cfile/tistory/2513B53E55DB206927',
			profileNicknameBool: computed(() => store.getters['root/profileNicknameBool']),
      password: null,
      newPassword: null,
      newPasswordConfirmation: null,
    })

    const clickgetNickname = function () {
      store.dispatch('root/profileGetNickname', { nickname: state.nickname })
    }

    const clickmodifyNickname = async function () {
      await store.dispatch('root/profileModifyNickname', { user_id: state.userInfo.id, nickname: state.nickname})
      await store.dispatch('root/userGetInfo', localStorage.getItem('jwt'))
    }

    const clickmodifyMask = async function (event) {
      await store.dispatch('root/profileModifyMask', { user_id: state.userInfo.id, mask_id: event})
      await store.dispatch('root/userGetInfo', localStorage.getItem('jwt'))
    }

    ////////////////////
    const changeImgFile = async function (event) {
      if( event.target.files && event.target.files.length > 0 ) {
        const file = event.target.files[0];
        store.profileImg = file;
        state.profileImgThumbnail = URL.createObjectURL(file); // 파일 경로로 바꿔서 추가
      }
    }

    const clickmodifyProfileImg = async function () {
      await store.dispatch('root/profileModifyProfileImg', { user_id: state.userInfo.id, profile_img: state.profileImg })
      await store.dispatch('root/userGetInfo', localStorage.getItem('jwt'))
    }
    ////////////////////

    const clickmodifyPassword = async function () {
      await store.dispatch('root/profileModifyPassword', { user_id: state.userInfo.id, password: state.newPassword})
      await store.dispatch('root/userGetInfo', localStorage.getItem('jwt'))
    }

    const clickresignUser = async function () {
      await store.dispatch('root/profileResignUser', { user_id: state.userInfo.id })
      await router.push({
        name: 'Home'
      })
      
    }

    return {
      onMounted, state, 
      clickgetNickname, 
      clickmodifyNickname, 
      clickmodifyMask, 
      changeImgFile, // 수정
      clickmodifyProfileImg, 
      clickmodifyPassword, 
      clickresignUser
    }
  }
}
</script>

<style>

</style>