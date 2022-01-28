<template>
  <div>
    <div align="left">
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
          @keyup="clickValidateNickname"
        />

        <p id="nicknameState" class="red">{{ state.nicknameMessage }}</p>
        <button type="button" @click="getNickname">getNickname</button>
        <br />
        <p>{{ state.nickname_bool }}</p>
        <input
          type="password"
          placeholder="비밀번호"
          id="password"
          v-model="credentials.password"
          @keyup="clickValidatePassword"
        />
        <p id="passwordState" class="red">{{ state.passwordMessage }}</p>
        <input
          type="password"
          placeholder="비밀번호 확인"
          id="passwordConfirmation"
          v-model="credentials.passwordConfirmation"
        />
        <p v-if="credentials.passwordConfirmation === null"></p>
        <p
          class="blue"
          v-else-if="credentials.password == credentials.passwordConfirmation"
        >
          비밀번호가 일치합니다.
        </p>
        <p class="red" v-else>비밀번호가 일치하지 않습니다.</p>
        <button
          :disabled="
            !state.nicknameValid ||
            !state.passwordValid ||
            credentials.password != credentials.passwordConfirmation
          "
          type="button"
          @click="clickSignUp"
        >
          Sign Up
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

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
      email_bool: computed(() => store.getters["root/userEmailBool"]),
      nickname_bool: computed(() => store.getters["root/userNicknameBool"]),
      nicknameMessage: "",
      passwordMessage: "",
      nicknameValid: false,
      passwordValid: false,
    });

		const clickSignUp = function () {
			console.log(credentials)
			store.dispatch('root/signUp', credentials)
			.then((res) => {
				console.log("SignUp 성공")
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
      let nicknameElement = document.getElementById("nicknameState");
      if (lengthNickname > 15) {
        state.nicknameMessage = "닉네임 길이 15자를 초과할 수 없습니다.";
        state.nicknameValid = false;
      } else {
        if (specialNickname != -1 || spaceNickname != -1) {
          state.nicknameMessage =
            "닉네임에 특수문자나 공백을 포함할 수 없습니다.";
          state.nicknameValid = false;
        } else {
          state.nicknameMessage = "사용할 수 있는 닉네임입니다.";
          state.nicknameValid = true;
        }
      }
      if (lengthNickname == 0) {
        state.nicknameMessage = "";
        state.nicknameValid = false;
      }

      if (state.nicknameValid) {
        nicknameElement.classList.remove("red");
        nicknameElement.classList.add("blue");
      } else {
        nicknameElement.classList.remove("blue");
        nicknameElement.classList.add("red");
      }
    };

    const clickValidatePassword = function () {
      let passwordCheck = credentials.password;
      let lengthPassword = passwordCheck.toString().length;
      let engPassword = passwordCheck.search(/[a-z]/gi);
      let numPassword = passwordCheck.search(/[0-9]/g);
      let specialPassword = passwordCheck.search(
        /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
      );
      let passwordElement = document.getElementById("passwordState");
      if (lengthPassword < 8 || lengthPassword > 15) {
        state.passwordMessage = "8자 이상 15자 이하로 작성해주세요.";
        state.passwordValid = false;
      } else {
        if (engPassword == -1 || numPassword == -1 || specialPassword == -1) {
          state.passwordMessage =
            "영문, 숫자, 특수문자를 하나 이상 포함해야 합니다.";
          state.passwordValid = false;
        } else {
          state.passwordMessage = "사용할 수 있는 비밀번호입니다.";
          state.passwordValid = true;
        }
      }
      if (lengthPassword == 0) {
        state.passwordMessage = "";
        state.passwordValid = false;
      }

      if (state.passwordValid) {
        passwordElement.classList.remove("red");
        passwordElement.classList.add("blue");
      } else {
        passwordElement.classList.remove("blue");
        passwordElement.classList.add("red");
      }
    };

    const getEmail = function () {
      store.dispatch("root/userOverlapping", {
        key: "email",
        value: credentials.email,
      });
    };

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
      getNickname,
    };
  },
};
</script>

<style scoped>
.red {
  color: red;
}
.blue {
  color: blue;
}
</style>
