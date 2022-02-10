<template>
  <div>
    <!-- community_detail -->
    <p>{{ state.communityDetail }}</p>

    <!-- 작성자일 때 나오는 수정, 삭제 버튼 -->
    <div v-if="state.userInfo.id == state.communityDetail.user_id">
      <button type="button" @click="clickModifyCommunity">
        수정하기
      </button>
      <button type="button" @click="clickDeleteCommunity">
        삭제하기
      </button>
    </div>

    <!-- 게시글 신고 버튼 -->
    <button type="button" @click="state.reportBool = !state.reportBool">신고</button>
    <div v-if="state.reportBool">
      <textarea
        cols="30"
        rows="5"
        v-model="state.reportMsg"
        placeholder="신고 내용을 작성해주세요."
      ></textarea
      ><br />
      <button type="button" @click="clickCommunityReportDetail">보내기</button>
    </div>

    <!-- 게시글 공감 버튼 -->
    <button type="button" @click="clickCommunityLike">하트 이모티콘</button>
    <br>
    <br>

    <!-- 게시글에 대한 댓글 -->
    <comment-view
      :userInfo="state.userInfo"
      :communityDetail="state.communityDetail"
    >
    </comment-view>
  </div>
</template>

<script>
import { computed, reactive } from "vue"
import { useStore } from "vuex"
import { useRouter } from "vue-router"
import CommentView from './CommentView.vue'

export default {
  name: "CommunityDetail",
  components: {
    CommentView,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"]),
      communityDetail: computed(() => store.getters["root/communityDetail"]),
      communityLike: computed(() => store.getters["root/communityLike"]),
      reportBool: false,
      reportMsg: "",
    });

    const clickModifyCommunity = function () {
      router.push({
        name: 'CommunityModify',
        params: {
          community_id: state.communityDetail.community_id
        }
      })
    }

    const clickDeleteCommunity = async function () {
      const body = {
        user_id: state.userInfo.id, community_id: state.communityDetail.community_id
      }
      await store.dispatch("root/communityDeleteDetail", body)
      await router.push({ name: 'Community' })
    };

    const clickCommunityReportDetail = function () {
      store.dispatch("root/reportDetail", {
        category: 'community',
        reportUserId: state.communityDetail.user_id,
        userId: state.userInfo.id,
        reportMsg: state.reportMsg,
      })
    }

    const clickCommunityLike = async function () {
      await store.dispatch("root/communityModifyLike", {
        userId: state.userInfo.id,
        communityId: state.communityDetail.community_id
      })
      await store.dispatch('root/communityGetDetail', {
        user_id: state.userInfo.id,
        community_id: state.communityDetail.community_id
      })
    }

    return {
      state,
      router,
      clickModifyCommunity,
      clickDeleteCommunity,
      clickCommunityReportDetail,
      clickCommunityLike,
    };
  },
};
</script>

<style></style>
