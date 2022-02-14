<template>
  <div class="row mx-5 mb-5" style="margin-top: 100px;">
    <div class="col-1"></div>
    <ol class="list col-3">
      <li v-for="(person, idx) in state.confessionReviewList" :key="idx">
        <div class="item d-flex" v-if="person.userId!=state.user_id">
          <h2 class="headline mx-5">{{ person.nickName }}</h2>
          <button class="front__text-hover" @click="clickIsReport(idx)">신고</button>
        </div>
      </li>
    </ol>
    <div class="col-5">
      <div v-if="state.reviewIndex!=-1">
        <main class="main">
          <div class="container news flow">
              <h2 class="news__title">신고 하기</h2>
              
              <div class="news__form">
                <input type="report" placeholder="신고 내용을 적어주세요" v-model="state.reportMsg">
              </div>
              <br>
              <button class="front__text-hover" @click="clickReviewReportDetail(person.userId)">신고하기</button>
          </div>
        </main>
      </div>
    </div>
    <div>
      <button class="front__text-hover" style="width: 10%;" @click="goToHome">Home</button>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from '@vue/reactivity'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'

export default {
  name: "ReviewConfession",
  setup() {
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const state = reactive({
      confessionReviewList: computed(() => store.getters['root/confessionReviewList']),
      reviewIndex: -1,
      user_id: route.params.user_id,
      meeting_id: route.params.meeting_id,
      reportBool: false,
      reportMsg: "",
    })

    const clickIsReport = async function (idx) {
      if (state.reviewIndex === -1) {
        state.reviewIndex = idx
      } else {
        state.reviewIndex = -1
      }
    }

    const clickReviewReportDetail = function (reportUserId) {
      store.dispatch("root/reportDetail", {
        category: 'confession',
        reportUserId: reportUserId,
        userId: state.user_id,
        reportMsg: state.reportMsg,
      })
      state.reportMsg = ""
      state.reviewIndex = -1
    }

    const goToHome = function () {
      router.push({
        name: 'Home',
      })
    }

    return { state, clickIsReport, clickReviewReportDetail, goToHome }
  }
}
</script>

<style scoped lang="scss">

.list {
    list-style: none;
    width: 600px;
    max-width: 90%;
}

.item {
    display: block;
    clear: both;
    counter-increment: list;
    padding-bottom: 3rem;
    font-size: 1rem;
    line-height: 1;
    position: relative;
}

.item:before {
    font: bold 2.25rem/1 Montserrat, Roboto, Helvetica, Arial, sans-serif;
    content: counter(list);
    width: 3.8rem;
    height: 3.8rem;
    float: left;
    margin: 0 1.5rem 0.75rem 0;
    color: #fdfdfd;
    background: #bbd2f9 linear-gradient(to bottom right, #bbd2f9 25%, #ededed);
    text-shadow: 0 0 2px #bbd2f9;
    border-radius: 50%;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    shape-outside: ellipse();
    z-index: 1;
}

.item:after {
    width: 2rem;
    height: 2rem;
    position: absolute;
    top: 0;
    left: 0;
    content: "";
    background: #bbd2f9;
    z-index: -1;
    border-top-left-radius: 3px;
}


.headline {
  font: normal 2rem "Space Mono", Consolas, Menlo, Monaco, "Courier New", monospace;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.front__text-hover {
  position: relative;
  // top: 10px;
  font-size: 15px;
  color: #bbd2f9;
  backface-visibility: hidden;

  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: .4px;

  border: 2px solid #bbd2f9;
  margin: 15px 15px;
  border-radius: 30px;

  background: #bbd2f9;
  color: #fff;
}

@import url("https://fonts.googleapis.com/css2?family=Mulish:wght@400;600;700&display=swap");

*,
*::before,
*::after {
   box-sizing: border-box;
}

/* general layout */

* {
   font-family: inherit;
   line-height: inherit;
   color: inherit;
}

.main,
.container {
   width: 83%;
   margin: 0 auto;
}

.news {
   background-color: #fff;
   padding: 2.125em 1.25em;
   border-radius: 30px;
   box-shadow: 0px 34px 85px -25px #bbc0ea;
}
.news__title {
   font-size: 1.75rem;
   margin-bottom: 1.5em;
}

.custom {
   opacity: 0;
   position: relative;
}

.custom + label {
   display: grid;
   grid-template-columns: 24px auto;
   grid-template-rows: auto auto;
   align-items: center;
   column-gap: 1em;
   position: relative;
   cursor: pointer;
}

.custom[type="checkbox"] + label::before {
   content: "";
   width: 24px;
   height: 24px;
   border-radius: 3px;
   border: 1px solid #8f94a1;
   background: transparent;
   margin-bottom: 10px;
}

.custom[type="checkbox"] + label::after {
   content: "\2713";
   color: white;
   font-size: 1.25rem;
   position: absolute;
   top: 0px;
   left: 4px;
   transform: scale(0);
   transition: all 300ms ease-in-out;
}

.custom[type="checkbox"]:checked + label::before {
   background-color: hsl(248, 66%, 66%);
}

.custom[type="checkbox"]:checked + label::after {
   transform: scale(1);
}

h5 {
   font-size: 1.25rem;
   white-space: nowrap;
}

p {
   grid-column: 2/ -2;
}
.card {
  width: 100%;
  padding: 0 12px;
}
.card.active {
   background-color: hsl(232, 41%, 97%);

   height: 88px;
   width: 100%;
}

// ======= form
.news__form {
   display: flex;
   flex-direction: column;
   gap: 20px;
   margin-top: 30px;
}

input[type="report"] {
   border: 0;
   outline: 0;
   padding: 30px 30px;
   width: 100%;
   border-radius: 30px;
   border: 1px solid #8f94a1;
}

@media (min-width: 550px) {
   .news-grid {
      grid-auto-flow: column;
      grid-template-columns: repeat(3, 1fr);
   }
   .news {
      padding: 4.125em 2.25em;
   }
   .news__form {
      flex-direction: row;
      gap: 10px;
   }
   input[type="email"] {
      flex: 0 0 480px;
   }
}

</style>