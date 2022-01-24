<template>
	<div>
		<p>{{ state.userInfo }}</p>
		<div>
			<form :model="credentialsIn">
				<input type="text" placeholder="이메일" id="email"
					v-model="credentialsIn.email" />
				<input type="password" placeholder="비밀번호" id="password"
					v-model="credentialsIn.password" />
				<!-- <a href="#">Forgot your password?</a> -->
				<button type="button" @click="clickSignIn">Sign In</button>
			</form>
		</div>
	</div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'SignIn',
  setup() {
		const store = useStore()
		const router = useRouter()

		const credentialsIn = reactive({
			email: null,
			password: null,
		})

		const state = reactive({
			userInfo: computed(() => store.getters['root/userInfo'])
		})

		const clickSignIn = function () {
			console.log(credentialsIn)
			console.log('start')
			store.dispatch('root/signIn', credentialsIn)
			console.log('end')
			
			// const user_id = userInfo
			router.push({
					name: 'Home'
				})
		}

    return { credentialsIn, state, clickSignIn }
  }
}
</script>

<style>

</style>