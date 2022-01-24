<template>
  <div>
    <div v-if="state.userInfo">
      <p>{{ state.userInfo.id }}</p>
      <button @click="clickProfile(state.userInfo.id)">Go To Profile</button>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'HomeMain',
  props: {
    userInfo : Array,
  },
  setup(props) {
    const store = useStore()
		const router = useRouter()

    const state = reactive({
			userInfo: props.userInfo
    })
    
    const clickProfile = function(user_id)  {
      router.push({
        name: 'Profile',
        params: {
          user_id: user_id
        }
      })
		}
    return { store, state, clickProfile }
  }
}
</script>

<style>

</style>