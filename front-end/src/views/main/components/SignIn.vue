<template>
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
							<button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="findPassword">findPassword</button>
						</div>
					</div>
				</div>
			</div>
			<button type="button" @click="clickSignIn">Sign In</button>
		</form>
		<div class="container social_div">
			<a class="social_btn" href="http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:8080/oauth/redirect" >
				<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAMAAACdt4HsAAAArlBMVEVHcEw/hfbvgxs6ic4yqlQ/gvJAhvhhepCmcULsQjPsQjPqQjPsQjQzqVPrQjPxeSAyq1QyqlQxp1PnQTL8vwAzqVTrQjMyqlQ5qVE/hPU/hPYyqlQ+g/X9wAA/h/TtQjP7vADqQTP9wAA/hPU/hff7uQXzhxk3l5zAth4+g/P8wAA5k7LeuhPnQTM+gvEyp1P5uwAxplM/hPUyp1H3rAjtaCSIsDI6i8w0n3RXq0M974clAAAALXRSTlMAjQ8P7TDhAwjwcUbYSKE0gNAdHr1rup0x8Lm1SomBgmBf6c2gpff892Rt4qhtntK7AAACRUlEQVRYw+1XaXeiMBRFgg1r2GQTq7XaTjszGiitM/P//9gQljSWABFPv3nVox+89928Je8gSTfc8M1QfdsgsH11Als2dGSaGoFpIt2QL6P7eqgdWGjh1r8gum4eugi3oi6ML9EpkC2UOm74GqYxnk51ox36YdrX8TVdHfV/HV/anvE1M0QIhaYmyoc+mz8TGb4MJSj7BtLE4quIiY5sVYKNsGqEInzJ0JjjnneNvBHgy08flL+FX04H4Sgf2o/vz218OGH+4H2WZbUCmjK/UF6UAtn7h0i/cWE/Zo3CZsoBJPiQNXhmDEAw5wD0pqDCgqkgXN9xsOZZVJ9agXtWdnbs4m3PFVi0Aj9HBZa8KslU4GFM4LgE3yFw0RHAlUnk5qCnjLO3TwxXQaKN9BIzAqvZJ3atArcPYNPK2Qk7kB1jCrBsBV7510GVxZd/OXZj7h9WScNP5v3jTPgYR9wk7VsDO9A7jqeC8LESwAEDx1nPtMq/TznOq3dXAc5pCpNV30RbCq4MYKJwbhOudsN9WPdSVAnUp4hixoQX/DqOG5Ck2K3jk1cpYXmgXCyqZzkuzn+0AnswcCtZSpUDAuLCTaMoSl2l/F0Uf/9U/Lv54G5ylAJ3QfTyWiF5Hb4wS4UunSgQD+UxkjUYuVtBR6Gg36XCKL/0ECiV57YlmqrUpXGAyP1upZj2Q+2++mDsWoIbywtcGhRTI4rjiW8ZL0gVNollRZ34sn0FLKdsgBpu6ljehHUHvdgiiD110rJsbqTbU8sNAvgPj0mre8ZWN6oAAAAASUVORK5CYII=" 
					class="social_login">
			</a>
			
			<a class="social_btn" href="http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:8080/oauth/redirect">
				<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABABAMAAABYR2ztAAAAKlBMVEVHcEwQqgAOpQANpAAOpAAeyAD///8bxAARqwBp21Xk+eA80CJW1kCw7KZApJ5fAAAABXRSTlMA7LNCXU5PuLYAAADxSURBVEjH7dYxDoJAEAXQKbyBBfYW1noCL7HZEPtNMGwBCUFLK70AFB5BsbTB3oIDUHgaBxESlN3facOvJvHnTbWMRIvJwZjdlGjkxJYES5pHkTBGxmOaCVtkSI61IALa2wubofD7wurGuXanTuF44lyqqaim7LMgU8VZJzzdq8n7El4Fdeapv1ALSiNBJUDgHUDQSFAJEHgHELQAgkqAoDIk6BQIqrQIum0ZBK80FmrBL4Dgu0gQJRBEjgQXCc0Oo9DsMAsuEt47zILMkbA1FNoHJx+9Tw8/3uEr9/8CPIv2wypCPs223/k0w+OO/h48AfrSASA123dQAAAAAElFTkSuQmCC" 
					class="social_login">
			</a>
			
			<a class="social_btn" href="http://localhost:8080/oauth2/authorization/kakao?redirect_uri=http://localhost:8080/oauth/redirect">
				<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAMAAACdt4HsAAAAV1BMVEVHcEz95AD74wD74gD85QD64QD74QD74QD74QD74wD74gD74wD74gD74QA8Hh44Gh7y2AFGKRx8YBRfQhjgxgSegw6tkwyOchFTNhprThbGrAjTuQa5nwoG7ISxAAAADXRSTlMABzOdD97t9x1zuzo+dvp0aAAAAvJJREFUWMOtl4myqjAMQBEBL3pp0339/+98bQFFKNyiLzMyzmhOs5EmVZWkqft7exsK5dbe+7qpXnKpr12xdiIMt+5aX2b9pm+HD6Ttm1m/+0Q/GDESLh/qB+n66EXdfqofglkHB67DF3Jtqrr7BtDVVf/MH5x5zE701X34Su7VhyGczWirgwqEl+wnotrVdVJRw7RmhirpdhlZAHCrGCHoKYQwZTkcAOBNXZqF8lOMzCG2FnDJEEY5wUzyPwFgKToQauEYAFKjQ9ESjgAgCPpDiIB9QIH+hvAGkAX60Ys9gGOoSJjLA4CiQqGQBXiNCwHaZwHJAE2TmJBNQ1NI2OiYSdXJxizTHMCxaICfXj9JKAcVdDS3JBah5QYjyh1ZRaFapYB6ywduPSUyvJDhPAZcR4ADE/Rh/BeRGYDC04snBhk0jPM2hnUCGAf0qY+Q2gKA4rlQBokRVqAo92QBkC99/MrDDgBrD4Y4zl6AGKY5TwWAcLrGMoTxBQihnSslBxjeXRCDVcqDXQCEBG4mQCYGg0ALALOTXSwCME5Z0HawejeIg5wcTAAKTiilLAgDEL6ZEESDDR+TjReluC6k9LqK4IFA4VwKlkF03gZWLCSAWJeRlCllNVe6YsiohCOKEiqCUE1jWZL0RIGfA9g5SWT6zF9J7PBjl09PvHyfl/1AHfUTvOxJOw1lTtKZdvDe0iw73ZBWXdnrAn17dC94dlJ/czNZgw4aGybU/XU3crXbGkNBC15wve/cjjhUmN27nd9vK4m3Z2NkhIWyAQMUfqqNEnq18A6yU1IGwNkUMCWlUEJIbx3fnZIyADcNJB6G4wFrc7XNI0Kqd8ULhrysBanxMQ+FA+N2Tgwt8Pj41ZzYboYkUn58nFQ3s7IThcfDOCv3p7atVcnFaf3RZX8ppHSP7zeW6rfds2+1YGRsbB97W1uZQ+PW9vHeGCLY/J/NNXjxez1tRHf9vSzW7+YRtvfuVihd2N4fy+09IZqfE9I8T/8HxHrZ6Vi0v8gAAAAASUVORK5CYII=" 
					class="social_login">
			</a>
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
			isSignIn: computed(() => store.getters["root/mainIsSignIn"]),
			email: null,
		})

		const clickSignIn = async function () {
			await store.dispatch('root/mainSignIn', credentialsIn)
			if (state.isSignIn == true) {
				await router.push({
					name: 'Home'
				})
			}
		}

		const findPassword = function () {
			store.dispatch('root/mainFindPassword', { email: state.email })
		}

    return { credentialsIn, state, clickSignIn, findPassword }
  }
}
</script>

<style>

</style>