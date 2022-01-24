<template>
  <h3>유저 정보 주르륵</h3>
  <p>이메일: {{ state.userInfo.email }}</p>
  <p>닉네임: {{ state.userInfo.nickname }}</p>
  <p>프로필이미지:{{ state.userInfo.profileImg }}</p>
  <p>포인트: {{ state.userInfo.pointTot }}</p>
  <p>reportCnt: {{ state.userInfo.reportCnt }}</p>
  <p>가면: {{ state.userInfo.mask }}</p>
  <button
    type="button"
    class="btn btn-outline-secondary"
    @click="clickModifyMask"
  >
    가면 변경
  </button>
  <p>페널티: {{ state.userInfo.penalty }}</p>
  <p>상담가인가?: {{ state.userInfo.consultant }}</p>

  <button type="button" class="btn btn-outline-secondary">
    <router-link :to="{ name: 'Confession' }">고해성사로 이동</router-link>
  </button>
  <br />
  <button type="button" class="btn btn-outline-secondary">
    <router-link :to="{ name: 'Profile' }">프로필로 이동</router-link>
  </button>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
export default {
  name: "AdviceUser",
  setup() {
    const store = useStore();
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"]),
    });

    const clickModifyMask = function () {
      store.dispatch("root/modifyMask", state.userInfo.id);
    };
    return { state, clickModifyMask };
  },
};
</script>

<style></style>
