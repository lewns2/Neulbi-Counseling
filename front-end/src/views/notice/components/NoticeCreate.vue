<template>
  <div class="container-contact100">
    <div class="wrap-contact100">
      <div class="contact100-form validate-form">
        <div class="contact100-form-title">공지사항 등록</div>

        <div
          class="wrap-input100 validate-input"
          data-validate="Please enter title"
        >
          <input
            class="input100"
            type="text"
            name="title"
            placeholder="Please enter title"
            v-model="state.title"
          />
          <span class="focus-input100"></span>
        </div>

        <div
          class="wrap-input100 validate-input"
          data-validate="Please enter description"
        >
          <textarea
            class="input100"
            name="description"
            placeholder="Please enter description"
            v-model="state.description"
          ></textarea>
          <span class="focus-input100"></span>
        </div>

        <div class="container-downBtn">
          <button class="downBtn" @click="goToNotice">
            <span> <i class="fas fa-undo"></i>&nbsp;취소 </span>
          </button>
          <button
            style="margin-left: 30px"
            class="downBtn"
            @click="clickCreateNotice"
          >
            <span>
              <i class="far fa-paper-plane"></i>
              생성
            </span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "NoticeCreate",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"]),
      title: "",
      description: "",
    });

    const clickCreateNotice = async function () {
      if (state.userInfo.role === "ADMIN") {
        await store.dispatch("root/noticeCreateNotice", {
          userId: state.userInfo.id,
          title: state.title,
          description: state.description,
        });
        await store.dispatch("root/noticeGetNoticeList");
        await router.push({ name: "Notice" });
      } else {
        alert("관리자가 아닙니다!");
      }
    };

    const goToNotice = function () {
      router.push({ name: "Notice" });
    };

    return { state, clickCreateNotice, goToNotice };
  },
};
</script>

<style scoped>
input {
  outline: none;
  border: none;
}

textarea {
  outline: none;
  border: none;
}

textarea:focus,
input:focus {
  border-color: transparent !important;
}

input::-webkit-input-placeholder {
  color: #bdbdd3;
}
input:-moz-placeholder {
  color: #bdbdd3;
}
input::-moz-placeholder {
  color: #bdbdd3;
}
input:-ms-input-placeholder {
  color: #bdbdd3;
}

textarea::-webkit-input-placeholder {
  color: #bdbdd3;
}
textarea:-moz-placeholder {
  color: #bdbdd3;
}
textarea::-moz-placeholder {
  color: #bdbdd3;
}
textarea:-ms-input-placeholder {
  color: #bdbdd3;
}

button {
  outline: none !important;
  border: none;
  background: transparent;
}

button:hover {
  cursor: pointer;
}

.container {
  max-width: 100%;
}

.container-contact100 {
  width: 100%;
  min-height: 10vh;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  padding: 15px;
  position: relative;
  background-color: #f2f2f2;
}

.wrap-contact100 {
  width: 700px;
  background: transparent;
  padding: 50px 0px 160px 0px;
}

.contact100-form {
  width: 100%;
}

.contact100-form-title {
  display: block;
  font-family: Ubuntu-Bold;
  font-size: 30px;
  color: #403866;
  line-height: 1.2;
  text-transform: uppercase;
  text-align: center;
  padding-bottom: 49px;
}

.wrap-input100 {
  width: 100%;
  background-color: #fff;
  border-radius: 31px;
  margin-bottom: 16px;
  position: relative;
  z-index: 1;
}

.input100 {
  position: relative;
  display: block;
  width: 100%;
  background: #fff;
  border-radius: 31px;
  font-family: Ubuntu-Bold;
  font-size: 18px;
  color: #8f8fa1;
  line-height: 1.2;
}

input.input100 {
  height: 62px;
  padding: 0 35px 0 35px;
}

textarea.input100 {
  min-height: 300px;
  padding: 19px 35px 0 35px;
}

.focus-input100 {
  display: block;
  position: absolute;
  z-index: -1;
  width: 100%;
  height: 100%;
  top: 0;
  left: 50%;
  -webkit-transform: translateX(-50%);
  -moz-transform: translateX(-50%);
  -ms-transform: translateX(-50%);
  -o-transform: translateX(-50%);
  transform: translateX(-50%);
  border-radius: 31px;
  background-color: #fff;
  pointer-events: none;

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.input100:focus + .focus-input100 {
  width: calc(100% + 20px);
}

.container-downBtn {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding-top: 10px;
}

.downBtn {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 20px;
  min-width: 150px;
  height: 62px;
  /* background-color: transparent; */
  border-radius: 31px;

  font-family: Ubuntu-Bold;
  font-size: 16px;
  color: #fff;
  line-height: 1.2;
  text-transform: uppercase;

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
  position: relative;
  z-index: 1;
}

.downBtn::before {
  content: "";
  display: block;
  position: absolute;
  z-index: -1;
  width: 100%;
  height: 100%;
  top: 0;
  left: 50%;
  -webkit-transform: translateX(-50%);
  -moz-transform: translateX(-50%);
  -ms-transform: translateX(-50%);
  -o-transform: translateX(-50%);
  transform: translateX(-50%);
  border-radius: 31px;
  background-color: #827ffe;
  pointer-events: none;

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.downBtn:hover:before {
  background-color: #403866;
  width: calc(100% + 20px);
}

.validate-input {
  position: relative;
}
</style>
