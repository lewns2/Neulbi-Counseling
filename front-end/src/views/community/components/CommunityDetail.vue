<template>
  <div class="container">
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
    <!--디테일 헤더-->
    <div class="detailheader row-hover pos-relative py-3 px-3 mt-5 row-lg-4">
      <div class="row align-items-center">
        <div class="col-md-10" id="leftalign">
          <h3>{{state.communityDetail.title}}</h3>
        </div>
        <div class="col-md-2" @click="clickCommunityLike">
          <div v-if="state.communityDetail.like">
            <i class="ion-ios-heart icon-1x"></i>
          </div>
          <div v-else>
            <i class="ion-ios-heart-outline icon-1x"></i>
          </div>
        </div>
      </div>
      <div class="row align-items-center">
        <div class="col-md-8 op-7" id="leftalign">
          <h5>{{state.communityDetail.userNickname}} | 1999-99-99</h5>
        </div>
        <!-- <div class="col-md-4 op-7" id="leftalign">
          <h5>1999-99-99</h5>
        </div> -->
        <div class="col-md-4 op-7">
          <div class="row"> 
            <div class="col px-1"> <i class="ion-ios-eye-outline icon-1x"></i> <span class="d-block text-sm">{{state.communityDetail.viewCnt}}</span></div>
            <div class="col px-1"> <i class="ion-thumbsup icon-1x"></i> <span class="d-block text-sm">{{state.communityDetail.likeCnt}}</span></div>
          </div>
        </div> 
      </div>
    </div>

    <!-- community_detail -->
    <!-- <p>{{ state.communityDetail }}</p> -->
    <!--디테일 본문-->
    <div class="card row-hover pos-relative mt-2 py-3 px-3 row-lg-4" id="maintext">
      <div>
        {{state.communityDetail.description}}
      </div>
    </div>
    
    <!-- 게시글 공감 / 신고 버튼 -->
    <div class="detailbutton row-hover pos-relative py-3 px-3 mt-2 row-lg-4">
      <div class="col">
        <button type="button" @click="state.reportBool = !state.reportBool">신고</button>
      </div>
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
      <div class="col">
        <button type="button" @click="clickCommunityLike">하트 이모티콘</button>
      </div>
    </div>

    <hr>

    <!-- 게시글에 대한 댓글 -->
    <comment-view
      :userInfo="state.userInfo"
      :communityDetail="state.communityDetail"
    >
    </comment-view>

    <!-- 작성자일 때 나오는 수정, 삭제 버튼 -->
    <div v-if="state.userInfo.id == state.communityDetail.user_id">
      <button type="button" @click="clickModifyCommunity">
        수정하기
      </button>
      <button type="button" @click="clickDeleteCommunity">
        삭제하기
      </button>
    </div>
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

<style>
.detailheader {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: white;
    background-clip: border-box;
    border-top: 2px solid black;
    border-bottom: 2px solid rgba(0,0,0,.125);
    /* border-radius: 0.25rem; */
}

#maintext {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: white;
  background-clip: border-box;
  height: 500px;
  border-top: 0;
  border-left: 0;
  border-right: 0;
  border-bottom: 0;
}

.detailbutton {
  position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: white;
    background-clip: border-box;
    border-top: 0;
    border-bottom: 0;
    border-left:0;
    border-right:0;
}

#leftalign {
  text-align: left;
}
</style>
