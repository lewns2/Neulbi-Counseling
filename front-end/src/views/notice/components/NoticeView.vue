<template>
  <div class="container">
    <h1>공지사항</h1>
    <div class="buttons" style="margin-left: 50px">
      <div class="helpdesk">
        <a class="btn effect01" target="_blank"><span>공지사항</span></a>
        <a class="btn effect01" target="_blank" @click="goToQna"
          ><span>Q&amp;A</span></a
        >
      </div>
    </div>
    <div v-if="state.userInfo.role === 'ADMIN'">
      <a class="bt more-bt">
        <span class="fl"></span><span class="sfl"></span
        ><span class="cross"></span><i></i>
        <p>등록</p>
      </a>
      <button @click="goToCreateNotice">생성</button>
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
          v-for="(notice, idx) in state.noticeList"
          :key="idx"
          class="table-row"
          @click="goToNoticeDetail(notice.noticeId)"
        >
          <div class="col col-1" data-label="noticeId">
            {{ notice.noticeId }}
          </div>
          <div
            class="col col-2"
            data-label="title"
            style="text-align: left; padding-left: 50px"
          >
            {{ notice.title }}
          </div>
          <div class="col col-3" data-label="writer">운영자</div>
          <div class="col col-4" data-label="date">
            {{ notice.date.substr(0, 10) }}
          </div>
        </li>
      </ul>
    </div>

    <button id="prev" @click="checkPage($event)">이전</button>
    {{ state.page }} 페이지 / {{ state.noticeLastPageNum }} 페이지
    <button id="next" @click="checkPage($event)">다음</button>
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

    const goToQna = function () {
      router.push({ name: "Qna" });
    };

    return { state, checkPage, goToNoticeDetail, goToCreateNotice, goToQna };
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

.bt {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  cursor: pointer;
  position: relative;
  font-family: "Roboto";
  text-transform: uppercase;
  color: #4b84f4;
  letter-spacing: 0.5px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  outline: none;
  text-decoration: none;
  text-align: center;
}

.more-bt {
  border-right: 2px solid #4b84f4;
  border-bottom: 2px solid #4b84f4;
  padding: 17px 29px 15px 31px;
  border-color: #4b84f4;
}

.more-bt p {
  font-size: 14px;
}

#wrapper.smooth section.smoothy.show {
  visibility: visible;
}

.more-bt:before {
  left: 0;
  bottom: 0;
  height: -webkit-calc(100% - 17px);
  height: calc(100% - 17px);
  width: 2px;
}

.more-bt:after,
.more-bt:before {
  content: " ";
  display: block;
  background: #4b84f4;
  position: absolute;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  z-index: 10;
}

.more-bt:after {
  top: 0;
  right: 0;
  width: -webkit-calc(100% - 17px);
  width: calc(100% - 17px);
  height: 2px;
}

.more-bt:after,
.more-bt:before {
  content: " ";
  display: block;
  background: #4b84f4;
  position: absolute;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  z-index: 10;
}

::selection {
  background: #4b84f4;
  color: #ffffff;
  text-shadow: none;
}

.more-bt:before {
  left: 0;
  bottom: 0;
  height: -webkit-calc(100% - 17px);
  height: calc(100% - 17px);
  width: 2px;
}

.more-bt:after,
.more-bt:before {
  content: " ";
  display: block;
  background: #4b84f4;
  position: absolute;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  z-index: 10;
}

.more-bt .fl,
.more-bt .sfl {
  position: absolute;
  right: 0;
  height: 100%;
  width: 0;
  z-index: 2;
  background: #4b84f4;
  top: 0;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  -webkit-transition-delay: 0.1s;
  transition-delay: 0.1s;
}

.more-bt .fl,
.more-bt .sfl {
  position: absolute;
  right: 0;
  height: 100%;
  width: 0;
  z-index: 2;
  background: #4b84f4;
  top: 0;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  -webkit-transition-delay: 0.1s;
  transition-delay: 0.1s;
}

.more-bt .sfl {
  z-index: 1;
  background: #4431d1;
  -webkit-transition: 0.7s;
  transition: 0.7s;
}

.more-bt .cross {
  position: absolute;
  z-index: 15;
  width: 18px;
  height: 18px;
  top: -webkit-calc(50% - 8px);
  top: calc(50% - 8px);
  left: -webkit-calc(50% - 8px);
  left: calc(50% - 8px);
}

#wrapper.smooth section.smoothy {
  visibility: hidden;
}

.more-bt .cross:before {
  width: 100%;
  height: 2px;
  top: 8px;
  left: 0px;
  -webkit-transform: translateX(50px) scaleX(0);
  -ms-transform: translateX(50px) scaleX(0);
  transform: translateX(50px) scaleX(0);
}

.more-bt .cross:before,
.more-bt .cross:after {
  content: " ";
  background: #fff;
  display: block;
  position: absolute;
  opacity: 0;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  -webkit-transition-timing-function: cubic-bezier(0.86, 0, 0.07, 1);
  transition-timing-function: cubic-bezier(0.86, 0, 0.07, 1);
}

.more-bt .cross:after {
  width: 2px;
  height: 100%;
  left: 8px;
  top: 0;
  -webkit-transform: translateY(20px) scaleY(0);
  -ms-transform: translateY(20px) scaleY(0);
  transform: translateY(20px) scaleY(0);
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
}

.more-bt .cross:before,
.more-bt .cross:after {
  content: " ";
  background: #fff;
  display: block;
  position: absolute;
  opacity: 0;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  -webkit-transition-timing-function: cubic-bezier(0.86, 0, 0.07, 1);
  transition-timing-function: cubic-bezier(0.86, 0, 0.07, 1);
}

.more-bt i {
  position: absolute;
  display: block;
  top: 1px;
  left: 1px;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  z-index: 10;
}

.more-bt i:before {
  -webkit-transform: rotate(90deg);
  -ms-transform: rotate(90deg);
  transform: rotate(90deg);
}

.more-bt i:after,
.more-bt i:before {
  content: " ";
  display: block;
  width: 2px;
  height: 20px;
  background: #4b84f4;
  position: absolute;
  margin: -10px -1px;
  left: 50%;
  top: 50%;
  transition: 0.3s;
}

.more-bt:hover i:after {
  content: " ";
  display: block;
  width: 2px;
  height: 20px;
  background: #4b84f4;
  position: absolute;
  margin: 0px -1px;
  left: 50%;
  top: 50%;
}

.more-bt:hover i:before {
  content: " ";
  display: block;
  width: 2px;
  height: 20px;
  background: #4b84f4;
  position: absolute;
  margin: -10px 0px -10px 8px;
  left: 50%;
  top: 50%;
}

.more-bt p {
  -webkit-transition: 0.5s;
  transition: 0.5s;
  position: relative;
  z-index: 1;
}

.more-bt:hover:before,
.more-bt:before {
  height: 100%;
}

.more-bt:before {
  left: 0;
  bottom: 0;
  height: -webkit-calc(100% - 17px);
  height: calc(100% - 17px);
  width: 2px;
}

.more-bt:hover .fl,
.more-bt .fl {
  -webkit-transition: 0.7s;
  transition: 0.7s;
}

.more-bt:hover .fl,
.more-bt:hover .sfl,
.more-bt .fl,
.more-bt .sfl {
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  width: 100%;
}

.more-bt .fl,
.more-bt .sfl {
  position: absolute;
  right: 0;
  height: 100%;
  width: 0;
  z-index: 2;
  background: #4b84f4;
  top: 0;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  -webkit-transition-delay: 0.1s;
  transition-delay: 0.1s;
}

.more-bt:hover .sfl,
.more-bt .sfl {
  -webkit-transition: 0.5s;
  transition: 0.5s;
}

.more-bt:hover .fl,
.more-bt:hover .sfl,
.more-bt.hvd .fl,
.more-bt.hvd .sfl {
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  width: 100%;
}

.more-bt .sfl {
  z-index: 1;
  background: #4431d1;
  -webkit-transition: 0.7s;
  transition: 0.7s;
}

.more-bt .cross {
  position: absolute;
  z-index: 15;
  width: 18px;
  height: 18px;
  top: -webkit-calc(50% - 8px);
  top: calc(50% - 8px);
  left: -webkit-calc(50% - 8px);
  left: calc(50% - 8px);
}

.more-bt:hover .cross:before,
.more-bt .cross:before {
  -webkit-transition-duration: 0.5s;
  transition-duration: 0.5s;
}

.more-bt:hover .cross:after,
.more-bt:hover .cross:before,
.more-bt .cross:after,
.more-bt .cross:before {
  -webkit-transform: none;
  -ms-transform: none;
  transform: none;
  opacity: 1;
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}

.more-bt .cross:after {
  width: 2px;
  height: 100%;
  left: 8px;
  top: 0;
  -webkit-transform: translateY(20px) scaleY(0);
  -ms-transform: translateY(20px) scaleY(0);
  transform: translateY(20px) scaleY(0);
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
}

.more-bt .cross:before,
.more-bt .cross:after {
  content: " ";
  background: #fff;
  display: block;
  position: absolute;
  opacity: 0;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  -webkit-transition-timing-function: cubic-bezier(0.86, 0, 0.07, 1);
  transition-timing-function: cubic-bezier(0.86, 0, 0.07, 1);
}

.more-bt:hover .cross:after,
.more-bt:hover .cross:before,
.more-bt.hvd .cross:after,
.more-bt.hvd .cross:before {
  -webkit-transform: none;
  -ms-transform: none;
  transform: none;
  opacity: 1;
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}

.more-bt:hover .cross:after,
.more-bt.hvd .cross:after {
  -webkit-transition-duration: 0.6s;
  transition-duration: 0.6s;
}
</style>
