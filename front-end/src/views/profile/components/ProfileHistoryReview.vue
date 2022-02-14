<template>
  <div>
    <br>
    <h3>Received Review</h3>
    <br>
    <main class="d-flex">
      <a class="card mx-4" v-for="(receivedReview, idx) in state.profileHistoryReceivedReview" :key="idx">
        <div class="inner">
          <h2 class="title">{{ receivedReview.description }}</h2>
          <p class="subtitle">Point : {{ receivedReview.point }} pt</p>
            <p style="color: black;">asd</p>
        </div>
      </a>
    </main>
    <br>
    <div class="d-flex justify-content-center mb-5">
      <button id="rec_prev" class="paginate left" @click="checkPage($event)"><i></i><i></i></button>
      <div class="counter">{{state.receivedpage}}페이지 / {{ state.profileHistoryReceivedReviewLastPageNum }}페이지 </div>
      <button id="rec_next" class="paginate right" @click="checkPage($event)"><i></i><i></i></button>
    </div>

    <hr>
    <h3>Written Review</h3>
    <main class="d-flex">
      <a class="card mx-4" v-for="(writtenReview, idx) in state.profileHistoryWrittenReview" :key="idx">
        <div class="inner">
          <h2 class="title">{{ writtenReview.description }}</h2>
          <p class="subtitle" style="text-align: left;">Point : {{ writtenReview.point }} pt</p>
          <p style="color: black;">Nickname : </p>
        </div>
      </a>
    </main>
    <br>
    <div class="d-flex justify-content-center mb-5">
      <button id="wri_prev" class="paginated left" @click="checkPage($event)"><i></i><i></i></button>
      <div class="counter">{{state.writtenpage}}페이지 / {{ state.profileHistoryWrittenReviewLastPageNum }}페이지 </div>
      <button id="wri_next" class="paginated right" @click="checkPage($event)"><i></i><i></i></button>
    </div>
    <hr>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileHistoryReview",
  props: {
    userInfo: Object,
  },
  setup (props) {
    const store = useStore()
    const state = reactive ({
      userInfo: props.userInfo,
      profileHistoryReceivedReview: computed(() => store.getters['root/profileHistoryReceivedReview']),
      profileHistoryReceivedReviewLastPageNum: computed(() => store.getters['root/profileHistoryReceivedReviewLastPageNum']),
      profileHistoryWrittenReview: computed(() => store.getters['root/profileHistoryWrittenReview']),
      profileHistoryWrittenReviewLastPageNum: computed(() => store.getters['root/profileHistoryWrittenReviewLastPageNum']),
      receivedpage: 1,
      writtenpage: 1,
    })

    const checkReceivedPage = async function(event) {
      let targetId = event.currentTarget.id;
      if(targetId == "rec_prev") {
        state.receivedpage -= 1;
      }
      else if(targetId == "rec_next") {
        state.receivedpage += 1;
      }

      const pr = document.querySelector('.paginate.left')
      const pl = document.querySelector('.paginate.right')

      pr.setAttribute('data-state', state.receivedpage===1 ? 'disabled': '')
      if (state.receivedpage===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.receivedpage===state.profileHistoryReceivedReviewLastPageNum ? 'disabled' : '')
      if (state.receivedpage === state.profileHistoryReceivedReviewLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }

      await store.dispatch("root/profileGetHistoryReceivedReviewPageSearch", {
        user_id: state.userInfo.id,
        page: state.receivedpage,
        size: 3,
      })
    }

    const checkWrittenPage = async function(event) {
      let targetId = event.currentTarget.id;
      if(targetId == "wri_prev") {
        state.writtenpage -= 1;
      }
      else if(targetId == "wri_next") {
        state.writtenpage += 1;
      }

      const pr = document.querySelector('.paginated.left')
      const pl = document.querySelector('.paginated.right')

      pr.setAttribute('data-state', state.writtenpage===1 ? 'disabled': '')
      if (state.writtenpage===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.writtenpage===state.profileHistoryReceivedReviewLastPageNum ? 'disabled' : '')
      if (state.writtenpage === state.profileHistoryWrittenReviewLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }

      await store.dispatch("root/profileGetHistoryWrittenReviewPageSearch", {
        user_id: state.userInfo.id,
        page: state.writtenpage,
        size: 3,
      })
    }

    return { state, checkReceivedPage, checkWrittenPage }
  }
}
</script>

<style scoped lang="scss">
main {
  width: 100%;
  margin: 0 auto;
}

.card {
  // height: 400px;
  width: 30%;
  position: relative;
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  align-items: flex-end;
  text-decoration: none;
  border-radius: 30px;
  margin-bottom: 20px;
  background: #c2d6f8;
  background-size: cover;
}

.inner {
  height: 250px;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center; 
  background: white;
  border-radius: 20px;
  box-sizing: border-box;
  padding: 40px;
}

.title {
  font-size: 24px;
  color: black;  
  text-align: center;
  font-weight: 700;
  color: #181818;
  position: relative;
  margin: 0 0 20px 0;
}

.subtitle {
  color: #b0215e;
  text-align: center;
}


// pagination

button {
  -webkit-appearance: none;
  background: transparent;
  border: 0;
  outline: 0;
}

.paginate {
  position: relative;
  margin: 10px;
  width: 50px;
  height: 50px;
  cursor: pointer;
  transform: translate3d(0, 0, 0);
  position: absolute;
  margin-top: -25px;
  -webkit-filter: drop-shadow(0 2px 0px rgba(0, 0, 0, 0.2));
}
.paginate i {
  position: absolute;
  left: 0;
  width: 60%;
  height: 5px;
  border-radius: 2.5px;
  background: #708bef;
  transition: all 0.15s ease;
}
.paginate.left {
  right: 50%;
}
.paginate.left i {
  transform-origin: 0% 50%;
}
.paginate.left i:first-child {
  transform: translate(0, -1px) rotate(40deg);
}
.paginate.left i:last-child {
  transform: translate(0, 1px) rotate(-40deg);
}
.paginate.left:hover i:first-child {
  transform: translate(0, -1px) rotate(30deg);
}
.paginate.left:hover i:last-child {
  transform: translate(0, 1px) rotate(-30deg);
}
.paginate.left:active i:first-child {
  transform: translate(1px, -1px) rotate(25deg);
}
.paginate.left:active i:last-child {
  transform: translate(1px, 1px) rotate(-25deg);
}
.paginate.left[data-state=disabled] i:first-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginate.left[data-state=disabled] i:last-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginate.left[data-state=disabled]:hover i:first-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginate.left[data-state=disabled]:hover i:last-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginate.right {
  left: 60%;
}
.paginate.right i {
  transform-origin: 100% 50%;
}
.paginate.right i:first-child {
  transform: translate(0, 1px) rotate(40deg);
}
.paginate.right i:last-child {
  transform: translate(0, -1px) rotate(-40deg);
}
.paginate.right:hover i:first-child {
  transform: translate(0, 1px) rotate(30deg);
}
.paginate.right:hover i:last-child {
  transform: translate(0, -1px) rotate(-30deg);
}
.paginate.right:active i:first-child {
  transform: translate(1px, 1px) rotate(25deg);
}
.paginate.right:active i:last-child {
  transform: translate(1px, -1px) rotate(-25deg);
}
.paginate.right[data-state=disabled] i:first-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginate.right[data-state=disabled] i:last-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginate.right[data-state=disabled]:hover i:first-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginate.right[data-state=disabled]:hover i:last-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginate[data-state=disabled] {
  opacity: 0.3;
  cursor: default;
}


.paginated {
  position: relative;
  margin: 10px;
  width: 50px;
  height: 50px;
  cursor: pointer;
  transform: translate3d(0, 0, 0);
  position: absolute;
  margin-top: -25px;
  -webkit-filter: drop-shadow(0 2px 0px rgba(0, 0, 0, 0.2));
}
.paginated i {
  position: absolute;
  left: 0;
  width: 60%;
  height: 5px;
  border-radius: 2.5px;
  background: #708bef;
  transition: all 0.15s ease;
}
.paginated.left {
  right: 50%;
}
.paginated.left i {
  transform-origin: 0% 50%;
}
.paginated.left i:first-child {
  transform: translate(0, -1px) rotate(40deg);
}
.paginated.left i:last-child {
  transform: translate(0, 1px) rotate(-40deg);
}
.paginated.left:hover i:first-child {
  transform: translate(0, -1px) rotate(30deg);
}
.paginated.left:hover i:last-child {
  transform: translate(0, 1px) rotate(-30deg);
}
.paginated.left:active i:first-child {
  transform: translate(1px, -1px) rotate(25deg);
}
.paginated.left:active i:last-child {
  transform: translate(1px, 1px) rotate(-25deg);
}
.paginated.left[data-state=disabled] i:first-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginated.left[data-state=disabled] i:last-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginated.left[data-state=disabled]:hover i:first-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginated.left[data-state=disabled]:hover i:last-child {
  transform: translate(-5px, 0) rotate(0deg);
}
.paginated.right {
  left: 60%;
}
.paginated.right i {
  transform-origin: 100% 50%;
}
.paginated.right i:first-child {
  transform: translate(0, 1px) rotate(40deg);
}
.paginated.right i:last-child {
  transform: translate(0, -1px) rotate(-40deg);
}
.paginated.right:hover i:first-child {
  transform: translate(0, 1px) rotate(30deg);
}
.paginated.right:hover i:last-child {
  transform: translate(0, -1px) rotate(-30deg);
}
.paginated.right:active i:first-child {
  transform: translate(1px, 1px) rotate(25deg);
}
.paginated.right:active i:last-child {
  transform: translate(1px, -1px) rotate(-25deg);
}
.paginated.right[data-state=disabled] i:first-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginated.right[data-state=disabled] i:last-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginated.right[data-state=disabled]:hover i:first-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginated.right[data-state=disabled]:hover i:last-child {
  transform: translate(5px, 0) rotate(0deg);
}
.paginated[data-state=disabled] {
  opacity: 0.3;
  cursor: default;
}

.counter {
  text-align: center;
  position: absolute;
  width: 100%;
  margin-top: -15px;
  font-size: 20px;
  font-weight: bold;
  font-family: Helvetica, sans-serif;
  text-shadow: 0px 2px 0px rgba(0, 0, 0, 0.2);
  color: #708bef;
  z-index: -1;
}

</style>