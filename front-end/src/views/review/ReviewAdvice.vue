<template>
  <div class="container row" style="margin-top: 100px;">
    <div class="col-6">
      <main class="main">
        <div class="container news flow">
            <h2 class="news__title">신고 하기</h2>
            
            <div class="news__form">
              <input type="report" placeholder="신고 내용을 적어주세요" v-model="state.reportMsg">
            </div>
            <br>
            <button class="front__text-hover" @click="clickReviewReportDetail">신고하기</button>
        </div>
      </main>
    </div>
    <div class="col-6">
      <main class="main">
        <div class="container news flow">
            <h2 class="news__title">칭찬하기</h2>
            <form name="myform" id="myform" method="post" action="./save">
                <fieldset>
                    <!-- <legend>이모지 별점</legend> -->
                    <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
                    <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
                    <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
                    <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
                    <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
                </fieldset>
            </form>
            <!-- <div class="news__form">
              <input type="report" placeholder="1점 ~ 10점을 부여해주세요" v-model="state.point" class="py-2">
            </div> -->
            <div class="news__form">
              <input type="report" placeholder="해당 점수를 준 이유를 적어주세요" v-model="state.description" class="py-2">
            </div>
            <br>
            <button class="front__text-hover" @click="clickSubmitReview">점수부여</button>
        </div>
      </main>
    </div>
  </div>
  <br><br>
  <br><br>
  <div>
    <button class="front__text-hover" style="width: 10%;" @click="goToHome">Home</button>
  </div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
export default {
  name: "ReviewAdvice",
  setup() {
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const state = reactive({
      user_id: route.params.user_id,
      consultant_id: route.params.consultant_id,
      description: '',
      point: '',
      reportBool: false,
      reportMsg: "",
    })

    const clickSubmitReview = async function () {
      const body = {
        userId: state.user_id,
        consultantId: state.consultant_id,
        description: state.description,
        point: state.point
      }
      await store.dispatch('root/reviewSubmitReview', body)
      await store.dispatch('root/userGetInfo', localStorage.getItem('jwt'))
    }

    const clickReviewReportDetail = function () {
      store.dispatch("root/reportDetail", {
        category: 'advice',
        reportUserId: state.consultant_id,
        userId: state.user_id,
        reportMsg: state.reportMsg,
      })
    }

    const goToHome = function () {
      router.push({
        name: 'Home',
      })
    }

    return { state, clickSubmitReview, clickReviewReportDetail, goToHome }
  }
}
</script>

<style scoped lang="scss">

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
   width: 80%;
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
   padding: 45px 30px;
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

#myform fieldset{
    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    border: 0; /* 필드셋 테두리 제거 */
    direction: rtl; /* 이모지 순서 반전 */
}
#myform input[type=radio]{
    display: none; /* 라디오박스 감춤 */
}
#myform label{
    font-size: 3em; /* 이모지 크기 */
    color: transparent; /* 기존 이모지 컬러 제거 */
    text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}

#myform label:hover{
    text-shadow: 0 0 0 #a00; /* 마우스 호버 */
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 #a00; /* 마우스 호버 뒤에오는 이모지들 */
}

#myform fieldset legend{
    text-align: left;
}

#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 #a00; /* 마우스 클릭 체크 */
}

</style>