<template>
  <div>
    <div>
      <form :model="credentials">
        <input
          type="email"
          placeholder="이메일"
          id="email"
          v-model="credentials.email"
        />
        <button type="button" @click="getEmail">getEmail</button>
        <p>{{ state.email_bool }}</p>
        <input
          type="text"
          placeholder="사용자 닉네임"
          id="nickname"
          v-model="credentials.nickname"
        />
        <p></p>
        <button type="button" @click="getNickname">getNickname</button>
        <p>{{ state.nickname_bool }}</p>
        <!-- <button type="button" @click="clickValidateNickname">
          닉네임 유효성 검사
        </button> -->
        <input
          type="password"
          placeholder="비밀번호"
          id="password"
          v-model="credentials.password"
        />
        <button type="button" @click="clickValidatePassword">
          비밀번호 유효성 검사
        </button>
        <input type="password" placeholder="비밀번호 확인" id="passwordConfirmation"
					v-model="credentials.passwordConfirmation" />
        <p v-if="credentials.password == credentials.passwordConfirmation"> 비밀번호가 일치합니다. </p>
        <p v-else>비밀번호가 일치하지 않습니다.</p>
        <button type="button" @click="clickSignUp">Sign Up</button>
      </form>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: "SignUp",
  setup() {
    const store = useStore();
    const router = useRouter();

    const credentials = reactive({
      nickname: null,
      email: null,
      password: null,
      passwordConfirmation: null,
    });

    const state = reactive({
			email_bool: computed(() => store.getters['root/userEmailBool']),
			nickname_bool: computed(() => store.getters['root/userNicknameBool'])
		})

		const clickSignUp = function () {
			console.log(credentials)
			store.dispatch('root/signUp', credentials)
			.then((res) => {
				console.log(res)
				store.commit("root/SET_USER_EMAIL", false)
				store.commit("root/SET_USER_NICKNAME", false)
				router.push({
					name: 'SignIn'
				})
			})
			.catch((err) => {
				console.log(err);
			});
		}

    const clickValidateNickname = function () {
      let nicknameCheck = credentials.nickname;
      let lengthNickname = nicknameCheck.length;
      let specialNickname = nicknameCheck.search(
        /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
      );
      let spaceNickname = nicknameCheck.search(/\s/);

      if (lengthNickname > 15) {
        console.log("길이 제한 15자 초과, 유효하지 않은 상태");
      } else {
        if (specialNickname != -1 || spaceNickname != -1) {
          console.log("특수문자와 공백을 제거 조건 충족X, 유효하지 않은 상태");
        } else {
          console.log("특수문자와 공백이 없음, 유효한 상태");
        }
      }
    };

    const clickValidatePassword = function () {
      let passwordCheck = credentials.password;
      let lengthPassword = passwordCheck.length;
      let engPassword = passwordCheck.search(/[a-z]/gi);
      let numPassword = passwordCheck.search(/[0-9]/g);
      let specialPassword = passwordCheck.search(
        /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
      );

      if (lengthPassword < 8 || lengthPassword > 15) {
        console.log("길이 8자 이상 15자 이하를 충족X, 유효하지 않은 상태");
      } else {
        if (engPassword == -1 || numPassword == -1 || specialPassword == -1) {
          console.log(
            "영문, 숫자, 특수문자를 하나 이상 포함 충족X, 유효하지 않은 상태"
          );
        } else {
          console.log("유효한 상태");
        }
      }
    }

    const getEmail = function () {
			store.dispatch('root/userOverlapping', { key: 'email', value: credentials.email })
		}

		const getNickname = function () {
			store.dispatch('root/userOverlapping', { key: 'nickname', value: credentials.nickname })
		}

    return {
      state,
      credentials,
      clickSignUp,
      clickValidateNickname,
      clickValidatePassword,
      getEmail,
      getNickname
    };
  },
};
</script>

<style></style>
