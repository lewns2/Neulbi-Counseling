<template>
  <div>
    <div v-if="state.userInfo">
      <p>{{ state.userInfo.id }}</p>
      <button @click="clickProfile(state.userInfo.id)">Go To Profile</button>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'HomeMain',
  setup() {
    const store = useStore()
		const router = useRouter()

    const state = reactive({
			userInfo: computed(() => store.getters['root/userInfo']),
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