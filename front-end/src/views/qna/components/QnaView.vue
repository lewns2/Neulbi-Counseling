<template>
  <div class="container">
    <h1>Q&amp;A</h1>
    <div class="buttons" style="margin-left: 50px">
      <div class="helpdesk">
        <a class="btn effect01" target="_blank" @click="goToNotice"
          ><span>공지사항</span></a
        >
        <a class="btn effect01" target="_blank"><span>Q&amp;A</span></a>
      </div>
    </div>
    <div>
      <button style="float: right" @click="goToCreateQna">생성</button>
    </div>
    <div>
      <ul class="responsive-table">
        <li class="table-header" style="color: white; font-size: 15px">
          <div class="col col-1">번호</div>
          <div class="col col-2" style="text-align: left; padding-left: 50px">
            제목
          </div>
          <div class="col col-3">작성자</div>
          <div class="col col-4">작성일</div>
        </li>
        <li
          v-for="(qna, idx) in state.qnaList"
          :key="idx"
          class="table-row"
          @click="goToQnaDetail(qna)"
        >
          <div class="col col-1" data-label="qnaId">
            {{ qna.qnaId }}
          </div>
          <div
            class="col col-2"
            data-label="title"
            style="text-align: left; padding-left: 50px"
          >
            <i v-if="qna.rocked" class="fas fa-lock" style="color: #f65566"></i>
            <i v-else class="fas fa-lock-open" style="color: gold"></i>
            &nbsp;&nbsp;{{ qna.title }}
          </div>
          <div class="col col-3" data-label="nickname">
            {{ qna.userNickname }}
          </div>
          <div class="col col-4" data-label="date">
            {{ qna.date.substr(0, 10) }}
          </div>
        </li>
      </ul>
    </div>

    <button id="prev" @click="checkPage($event)">이전</button>
    {{ state.page }} 페이지 / {{ state.qnaLastPageNum }} 페이지
    <button id="next" @click="checkPage($event)">다음</button>
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
      if (!qna.rocked) {
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
    };
  },
};
</script>

<style scoped lang="scss">
body {
  font-family: "lato", sans-serif;
}

.buttons {
  // display: flex;
  // flex-direction: row;
  text-align: center;
  width: 100%;
  height: 100%;
  margin: 0 auto;
  /*   padding: 2em 0em; */
}

.helpdesk {
  align-items: center;
  display: flex;
  justify-content: center;
  text-align: center;
  padding: 40px 0px;
  width: 240px;
}

.btn {
  letter-spacing: 0.1em;
  cursor: pointer;
  font-size: 14px;
  font-weight: 400;
  line-height: 45px;
  max-width: 160px;
  position: relative;
  text-decoration: none;
  text-transform: uppercase;
  width: 100%;
  margin-left: 10px;
}
.btn:hover {
  text-decoration: none;
}

/*btn_background*/
.effect01 {
  color: #fff;
  border: 4px solid #000;
  box-shadow: 0px 0px 0px 1px #000 inset;
  background-color: #000;
  overflow: hidden;
  position: relative;
  transition: all 0.3s ease-in-out;
}
.effect01:hover {
  border: 4px solid #666;
  background-color: #fff;
  box-shadow: 0px 0px 0px 4px #eee inset;
}

/*btn_text*/
.effect01 span {
  transition: all 0.2s ease-out;
  z-index: 2;
}
.effect01:hover span {
  letter-spacing: 0.13em;
  color: #333;
}

/*highlight*/
.effect01:after {
  background: #fff;
  border: 0px solid #000;
  content: "";
  height: 155px;
  left: -75px;
  opacity: 0.8;
  position: absolute;
  top: -50px;
  -webkit-transform: rotate(35deg);
  transform: rotate(35deg);
  width: 50px;
  transition: all 1s cubic-bezier(0.075, 0.82, 0.165, 1); /*easeOutCirc*/
  z-index: 1;
}
.effect01:hover:after {
  background: #fff;
  border: 20px solid #000;
  opacity: 0;
  left: 120%;
  -webkit-transform: rotate(40deg);
  transform: rotate(40deg);
}

.container {
  max-width: 80%;
  // margin-left: auto;
  // margin-right: auto;
  margin-top: 50px;
}

.responsive-table li {
  border-radius: 3px;
  padding: 25px 30px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}
.responsive-table .table-header {
  background-color: #95a5a6;
  font-size: 14px;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}
.responsive-table .table-row {
  background-color: #ffffff;
  box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);
}
.responsive-table .col-1 {
  flex-basis: 10%;
}
.responsive-table .col-2 {
  flex-basis: 40%;
}
.responsive-table .col-3 {
  flex-basis: 30%;
}
.responsive-table .col-3 {
  flex-basis: 20%;
}
@media all and (max-width: 767px) {
  .responsive-table .table-header {
    display: none;
  }
  .responsive-table li {
    display: block;
  }
  .responsive-table .col {
    flex-basis: 100%;
  }
  .responsive-table .col {
    display: flex;
    padding: 10px 0;
  }
  .responsive-table .col:before {
    color: #6c7a89;
    padding-right: 10px;
    content: attr(data-label);
    flex-basis: 50%;
    text-align: right;
  }
}
</style>
