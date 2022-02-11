<template bgcolor="white">
  <div class="container">
    <div class="row"></div>
    <link
      href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
      rel="stylesheet"
    />

    <div style="margin-top: 5%">
      <h2>공지사항</h2>
    </div>

    <div
      class="column intro row-hover pos-relative py-4 px-4 mt-5 mb-5 row text-center"
    >
      <h6 style="color: #6c8093">
        '공지사항'는 운영자가 사용자들에게 알리고자 하는 내용이 작성된
        공간입니다.
      </h6>
    </div>

    <!-- <div class="col-lg-9 mb-3">  -->
    <div class="inner-main-header">
      <button
        @click="goToCreateNotice"
        class="form-control form-control-md col-lg-2"
        id="input"
      >
        등록
      </button>
    </div>

    <div class="cardheader row-hover pos-relative py-3 px-3">
      <div class="row align-items-center">
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>번호</h5>
        </div>
        <div class="col-md-4 mb-3 mb-sm-0">
          <h5>제목</h5>
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>작성자</h5>
        </div>
        <div class="col-md-4 mb-3 mb-sm-0">
          <h5>작성일</h5>
        </div>
      </div>
    </div>
    <!-- 게시글 리스트 -->
    <div
      v-for="(notice, index) in state.noticeList"
      :key="index"
      class="card row-hover pos-relative py-3 px-3"
      id="board-style1"
    >
      <div class="row align-items-center">
        <div class="col-md-2 mb-3 mb-sm-0">
          <h6>{{ notice.noticeId }}</h6>
        </div>
        <div
          class="col-md-4 mb-3 mb-sm-0"
          @click="goToNoticeDetail(notice.noticeId)"
          style="text-align: left"
        >
          <h6>
            {{ notice.title }}
          </h6>
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h6>
            <p class="text-black">운영자</p>
          </h6>
        </div>

        <div class="col-md-4 mb-3 mb-sm-0">
          <h6>
            {{ notice.date.substr(0, 10) }}
          </h6>
        </div>
      </div>
    </div>

    <!--페이지-->
    <div style="margin: 3%">
      <button id="prev" @click="checkPage($event)">이전</button>
      {{ state.page }} 페이지 / {{ state.communityLastPageNum }} 페이지
      <button id="next" @click="checkPage($event)">다음</button>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from "@vue/reactivity";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "NoticeView",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"]),
      noticeList: computed(() => store.getters["root/noticeList"]),
      noticeLastPageNum: computed(
        () => store.getters["root/noticeLastPageNum"]
      ),
      page: 1,
    });

    const checkPage = async function (event) {
      let targetId = event.currentTarget.id;
      if (targetId == "prev") {
        state.page -= 1;
        if (state.page < 1) state.page = 1;
      } else if (targetId == "next") {
        state.page += 1;
      }
      await store.dispatch("root/noticePageSearch", {
        size: 10,
        page: state.page,
      });
    };

    const goToNoticeDetail = async function (noticeId) {
      await store.dispatch("root/noticeGetNoticeDetail", noticeId);
      await router.push({
        name: "NoticeDetail",
        params: {
          notice_id: noticeId,
        },
      });
    };

    const goToCreateNotice = async function () {
      router.push({ name: "NoticeCreate" });
    };

    return { state, checkPage, goToNoticeDetail, goToCreateNotice };
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
