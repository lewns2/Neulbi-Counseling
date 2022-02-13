<template>
  <div class="homemain1">
  </div>
  <div class="homemain2 py-4 mb-5" v-if="state.userInfo">
    <button type="button" class="btn btn-light btn-lg" @click="clickProfile">시작하기</button>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'HomeMain',
  props: {
    userInfo : Object,
  },
  setup(props) {
    const store = useStore()
		const router = useRouter()

    const state = reactive({
			userInfo: props.userInfo
    })
    
    const clickProfile = function()  {
      router.push({
        name: 'Profile',
        params: {
          user_id: state.userInfo.id
        }
      })
		}
    return { store, state, clickProfile }
  }
}
</script>

<style scoped lang="scss">

h1 {
  text-align: left;
  margin-left: 10%;
  font-size: 150px;
}

.homemain1 {
  /* width: 100%; */
  height: 100%;

  background-image: url("캡처-removebg-preview.png");
  background-color: #c2d6f8;
  /* width: 50vw; */
  background-position: center;
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.homemain2 {
  background-color: #c2d6f8;
}
</style>