<template bgcolor="white">
  <div class="container" style="width: 1000px">
    <div class="row"></div>
    <link
      href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
      rel="stylesheet"
    />

    <div style="margin-top: 5%">
      <h2>Q&amp;A</h2>
    </div>

    <div
      class="column intro row-hover pos-relative py-4 px-4 mt-5 mb-5 row text-center"
    >
      <h6 style="color: #6c8093">
        'Q&amp;A'는 궁금증을 해소하는 공간입니다. 운영자에게 궁금한 점이나
        제시할 사항이 있다면 자유롭게 작성해주세요. <br />비공개로 작성시 작성자
        본인만 답글을 확인할 수 있습니다.
      </h6>
    </div>

    <div class="inner-main-header">
      <button
        @click="goToCreateQna"
        class="form-control form-control-md col-lg-2"
        id="input"
      >
        등록
      </button>
    </div>

    <!-- <div class="col-lg-9 mb-3">  -->
    <!--게시판 헤더-->
    <div class="cardheader row-hover pos-relative py-3 px-3">
      <div class="row align-items-center">
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5></h5>
        </div>
        <div class="col-md-6 mb-3 mb-sm-0">
          <h5>제목</h5>
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>작성자</h5>
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>작성일</h5>
        </div>
      </div>
    </div>

    <div v-for="(qna, idx) in state.qnaList" :key="idx">
      <!-- QnA 리스트 -->
      <div class="card row-hover pos-relative py-3 px-3" id="board-style1">
        <div class="row align-items-center">
          <div class="d-flex col-md-1 mb-3 mb-sm-0" style="color: #3a6bff">
            <button
              v-if="
                state.userInfo.role === 'ADMIN' ||
                state.userInfo.nickname == qna.userNickname
              "
              @click="goToQnaDetail(qna)"
              class="form-control form-control-md col-lg-2"
              id="input"
              style="font-size: 10px; margin-right: 50px"
            >
              답글관리
            </button>
          </div>
          <div class="d-flex col-md-1 mb-3 mb-sm-0" style="color: #3a6bff">
            <h6>Q</h6>
          </div>
          <div
            class="col-md-6 mb-3 mb-sm-0"
            style="text-align: left"
            @click="showQnaAnswer(qna, idx)"
          >
            <h6>
              <i
                v-if="qna.rocked"
                class="fas fa-lock"
                style="color: #f65566"
              ></i>
              <i v-else class="fas fa-lock-open" style="color: gold"></i>
              &nbsp;&nbsp;
              {{ qna.title }}
            </h6>
          </div>
          <div class="col-md-2 mb-3 mb-sm-0">
            <h6>
              <p class="text-black">{{ qna.userNickname }}</p>
            </h6>
          </div>

          <div class="col-md-2 mb-3 mb-sm-0">
            <h6>
              {{ qna.date.substr(0, 10) }}
            </h6>
          </div>
        </div>
      </div>

      <!-- QnA 댓글 -->
      <div
        name="qnaAnswers"
        style="background-color: rgb(250, 250, 250); display: none"
        class="card row-hover pos-relative py-3 px-3"
        id="board-style1"
      >
        <div class="row align-items-center">
          <div class="col-md-1 mb-3 mb-sm-0" style="color: #3a6bff">
            <h6></h6>
          </div>
          <div class="d-flex col-md-1 mb-3 mb-sm-0" style="color: #3a6bff">
            <h6>A</h6>
          </div>
          <div class="col-md-8 mb-3 mb-sm-0" style="text-align: left">
            <h6 v-if="state.qnaAnswerList[0]">
              <i class="fas fa-arrow-right"></i>&nbsp;&nbsp;&nbsp;
              {{ state.qnaAnswerList[0].description }}
            </h6>
          </div>
          <div class="col-md-2 mb-3 mb-sm-0">
            <h6 v-if="state.qnaAnswerList[0]">
              {{ state.qnaAnswerList[0].date.substr(0, 10) }}
            </h6>
          </div>
        </div>
      </div>
    </div>

    <!--페이지-->
    <div style="margin: 3%">
      <button id="prev" @click="checkPage($event)">이전</button>
      {{ state.page }} 페이지 / {{ state.qnaLastPageNum }} 페이지
      <button id="next" @click="checkPage($event)">다음</button>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from "@vue/reactivity";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "QnaView",
  props: {
    userInfo: Object,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userInfo: props.userInfo,
      qnaList: computed(() => store.getters["root/qnaList"]),
      qnaLastPageNum: computed(() => store.getters["root/qnaLastPageNum"]),
      qnaAnswerList: computed(() => store.getters["root/qnaAnswerList"]),
      searchWord: "",
      page: 1,
      pageSearchTopic: "main",
    });

    const clickSearchQna = async function () {
      state.page = 1;
      state.pageSearchTopic = "search";
      await store.dispatch("root/qnaGetSearchQuestionList", {
        search_word: state.searchWord,
        user_id: state.userInfo.id,
      });
    };

    const clickOnMounted = async function () {
      state.page = 1;
      state.pageSearchTopic = "main";
      await store.dispatch("root/qnaGetQuestionList", state.userInfo.id);
    };

    const checkPage = async function (event) {
      let targetId = event.currentTarget.id;
      if (targetId == "prev") {
        state.page -= 1;
        if (state.page < 1) state.page = 1;
      } else if (targetId == "next") {
        state.page += 1;
      }
      if (state.pageSearchTopic === "main") {
        await store.dispatch("root/qnaPageSearch", {
          user_id: state.userInfo.id,
          size: 10,
          page: state.page,
        });
      } else if (state.pageSearchTopic === "topic") {
        await store.dispatch("root/qnaSearchPageSearch", {
          search_word: state.searchWord,
          user_id: state.userInfo.id,
          size: 10,
          page: state.page,
        });
      }
    };

    const goToQnaDetail = async function (qna) {
      await store.dispatch("root/qnaGetQuestionDetail", {
        qna_id: qna.qnaId,
        user_id: state.userInfo.id,
      });
      await router.push({
        name: "QnaDetail",
        params: {
          qna_id: qna.qnaId,
        },
      });
    };

    const showQnaAnswer = async function (qna, idx) {
      if (
        document.getElementsByName("qnaAnswers")[idx].style.display == "block"
      ) {
        document.getElementsByName("qnaAnswers")[idx].style.display = "none";
        return;
      }
      for (var i = 0; i < state.qnaList.length; i++) {
        document.getElementsByName("qnaAnswers")[i].style.display = "none";
      }
      if (!qna.rocked) {
        await store.dispatch("root/qnaGetQuestionDetail", {
          qna_id: qna.qnaId,
          user_id: state.userInfo.id,
        });
        if (state.qnaAnswerList[0])
          document.getElementsByName("qnaAnswers")[idx].style.display = "block";
      } else {
        alert("비공개된 글입니다.");
      }
    };

    const goToNotice = function () {
      router.push({ name: "Notice" });
    };

    const goToCreateQna = async function () {
      router.push({ name: "QnaCreate" });
    };

    return {
      state,
      clickSearchQna,
      clickOnMounted,
      checkPage,
      goToQnaDetail,
      goToNotice,
      goToCreateQna,
      showQnaAnswer,
    };
  },
};
</script>

<style scoped lang="scss">
body {
  margin-top: 20px;
  background: #eee;
  color: #708090;
}
.icon-1x {
  font-size: 24px !important;
}
a {
  text-decoration: none;
}
.text-primary,
a.text-primary:focus,
a.text-primary:hover {
  color: #1f4b97 !important;
}
.text-black,
.text-hover-black:hover {
  color: #000 !important;
}
.font-weight-bold {
  font-weight: 700 !important;
}

.inner-main-header {
  height: 6rem;
  border-bottom: 1px solid #cbd5e0;
  display: flex;
  align-items: center;
  padding: 0 1rem;
  flex-shrink: 0;
  justify-content: flex-end;
}

#input {
  margin-left: 10px;
  width: auto;
  display: inline;
}

#dropdownMenuButton1 {
  background-color: white;
}

a:hover {
  font-weight: bold;
  font-size: large;
}

.cardheader {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fafbfc;
  background-clip: border-box;
  border-top: 2px solid black;
  border-bottom: 2px solid rgba(0, 0, 0, 0.125);
  /* border-radius: 0.25rem; */
}

#board-style1 {
  border-top: 0;
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
  border-left: 0;
  border-right: 0;
}

#board-style1:hover {
  background-color: snow;
}

.intro {
  background-color: #fafbfc;
}
</style>
