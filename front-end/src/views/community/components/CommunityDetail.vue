<template>
  <p>{{ state.communityDetail }}</p>
  <button
    v-if="state.userId == state.communityId"
    type="button"
    @click="clickModifyDetail"
  >
    수정하기
  </button>
  <button
    v-if="state.userId == state.communityId"
    type="button"
    @click="clickDeleteDetail"
  >
    삭제하기</button
  ><br />
  <button type="button" @click="state.report = !state.report">신고</button>
  <div v-if="state.report">
    <textarea
      cols="30"
      rows="10"
      v-model="state.reportMsg"
      placeholder="신고 내용을 작성해주세요."
    ></textarea
    ><br />
    <button type="button" @click="clickReportDetail">보내기</button>
  </div>
  <button type="button" @click="clickLike">하트 이모티콘</button>
  <div>
    <p>{{ state.communityComment }}</p>
    <textarea
      cols="30"
      rows="10"
      v-model="state.reportMsg"
      placeholder="댓글을 작성해주세요."
    ></textarea
    ><br />
    <button type="button" @click="clickComment">등록</button>
  </div>
</template>

<script>
import { computed, onMounted, reactive } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";

export default {
  name: "CommunityDetail",
  setup() {
    const store = useStore();
    const route = useRoute();
    const state = reactive({
      communityDetail: computed(() => store.getters["root/communityDetail"]),
      communityLike: computed(() => store.getters["root/communityLike"]),
      communityComment: computed(() => store.getters["root/communityComment"]),
      title: "",
      description: "",
      userId: computed(() => store.getters["root/userId"]),
      communityId: 0,
      reportMsg: "",
      report: false,
      commentDescription: "",
    });

    store.dispatch("root/CommunityGetDetail", state.communityId);
    store.dispatch("root/CommunityGetLike", state.communityId);
    store.dispatch("root/CommunityGetComment", state.communityId);

    onMounted(() => {
      state.communityId = route.params.communityId;
    });

    const clickModifyDetail = function () {
      store.dispatch("root/CommunityModifyDetail", {
        community_id: state.communityId,
        title: state.title,
        description: state.description,
      });
    };

    const clickDelete = function () {
      store.dispatch("root/CommunityDeleteDetail", state.communityId);
    };

    const clickReportDetail = function () {
      store
        .dispatch("root/CommunityReportDetail", {
          report_user_id: state.communityId,
          user_id: state.userId,
          report_msg: state.reportMsg,
        })
        .then((res) => {
          console.log(res);
          if (res.status == 200) {
            alert("신고 성공");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const clickLike = function () {
      store.dispatch("root/CommunityModifyLike", state.communityId);
    };

    const clickComment = function () {
      store
        .dispatch("root/CommunityCreateComment", {
          community_id: state.communityId,
          user_id: state.userId,
          desc: state.commentDescription,
        })
        .then((res) => {
          console.log(res);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    return {
      state,
      clickModifyDetail,
      clickDelete,
      clickReportDetail,
      clickLike,
      clickComment,
    };
  },
};
</script>

<style></style>
