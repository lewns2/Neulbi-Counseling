<template>
	<div>
		<p>{{ state.userInfo }}</p>
		<p>{{ state.email_bool }}</p>
		<p>{{ state.nickname_bool }}</p>
		<div>
			<form :model="credentialsIn">
				<input type="text" placeholder="이메일" id="email"
					v-model="credentialsIn.email" />
				<input type="password" placeholder="비밀번호" id="password"
					v-model="credentialsIn.password" />
				<!-- modal button -->
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Find Password</button>
				<!-- modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Password 찾기위한 Email을 기입하시오.</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form>
									<div class="mb-3">
										<label for="message-text" class="col-form-label">Email:</label>
										<input type="text" class="form-control" id="message-text" v-model="state.email">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="sendEmail">Send message</button>
							</div>
						</div>
					</div>
				</div>
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
			userInfo: computed(() => store.getters['root/userInfo']),
			isLogin: computed(() => store.getters["root/isLogin"]),
			email_bool: computed(() => store.getters['root/userEmailBool']),
			nickname_bool: computed(() => store.getters['root/userNicknameBool']),
			email: null,
		})

		const clickSignIn = function () {
			store.dispatch('root/signIn', credentialsIn)
			setTimeout(function() {
				if (state.isLogin == true) {
				router.push({
						name: 'Home'
					})
				}
			},500)
		}

		const sendEmail = function () {
			store.dispatch('root/userFindPassword', { email: state.email })
		}

    return { credentialsIn, state, clickSignIn, sendEmail }
  }
}
</script>

<style>

</style>