<template>
	<div>
		<div>
			<form :model="credentials">
				<input type="text" placeholder="사용자 닉네임" id="nickname"
					v-model="credentials.nickname" />
				<input type="email" placeholder="이메일" id="email"
					v-model="credentials.email" />
				<input type="password" placeholder="비밀번호" id="password"
					v-model="credentials.password" />
				<!-- <input type="password" placeholder="비밀번호 확인" id="passwordConfirmation"
					v-model="credentials.passwordConfirmation" /> -->
				<button type="button" @click="clickSignUp">Sign Up</button>
			</form>
		</div>
	</div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'SignUp',
  setup() {
		const store = useStore()
		const router = useRouter()

		const credentials = reactive({
			nickname: null,
			email: null,
			password: null,
			// passwordConfirmation: null
		})

		const clickSignUp = function () {
			console.log(credentials)
			store.dispatch('root/signUp', credentials)
			.then((res) => {
				console.log(res)
				// 보내는데 성공하면 로그인창으로 이동
				router.push({
					name: 'SignIn'
				})
			})
			.catch((err) => {
				console.log(err);
			});
		}

    return { credentials, clickSignUp }
  }
}
</script>

<style>

</style>