<template>
  <!-- 카테고리 선택 -->
  <span class="mt-4 mb-4 row" style="line-height: 40px;">
    <div class="col-8 d-flex justify-content-evenly" style="width: 60%;">
      <div @click="getConfessionView">
        <a href="#" class="cta">
          <span>모두</span>
        </a>
      </div>
      <div
        v-for="(category, index) in state.categories"
        :key="index"
        @click="clickConfessionCategory(category.number)"
      >
        <a href="#" class="cta">
          <span>{{ category.value }}</span>
        </a>
      </div>
    </div>

    <!-- 검색 후 결과 얻기 -->
    <div class="col-4 d-flex justify-content-end">
      <!-- <details class="custom-select">
        <summary class="radios">
          <input type="radio" name="item" id="default" :title="state.showKey" checked>
          <input type="radio" name="item" id="nickname" title="닉네임">
          <input type="radio" name="item" id="description" title="설명">
        </summary>
        <ul class="list">
          <li v-for="(searchCategory, idx) in state.searchCategories" :key="idx">
            <label :for="searchCategory.backValue" @click="clickModifyShowCategory(searchCategory)">{{ searchCategory.value }}</label>
          </li>
        </ul>
      </details> -->
      <div class="dropdown me-3">
        <input type="checkbox" id="dropdown">

        <label class="dropdown__face" for="dropdown">
          <div class="dropdown__text ps-3">{{ state.showKey }}</div>

          <div class="dropdown__arrow"></div>
        </label>

        <ul class="dropdown__items">
          <li>🙂</li>
          <li>😺</li>
          <li>😽</li>
          <li>😎</li>
          <li>🤗</li>
        </ul>
      </div>
      
      <!-- <button type="button" class="select-btn mx-2">
        {{ state.showKey }}
      </button> -->
      <!-- <div class="d-flex justify-content-center my-3"> -->
      <div class="searchBox">
        <input class="searchInput" type="text" placeholder="Search" v-model="state.word">
      </div>
      <button
        type="button"
        class="search-btn ms-2"
        @click="clickConfessionSearch"
      >
        검색
      </button>
    </div>

    <!-- </div> -->
    

  </span>


  <hr>
  <!-- 상담가 리스트 -->
  <div class="row d-flex justify-content-start">
    <div v-for="(confessionMeeting, index) in state.confessionMeetingList" :key="index" class="col-3 mx-5 my-5">
      <div class="card">
        <img :src="confessionMeeting.profileImg" class="card__image" alt="" />
        <div class="card__overlay">
          <div class="card__header">
            <svg class="card__arc" xmlns="http://www.w3.org/2000/svg"><path /></svg>                     
            <img class="card__thumb" src="https://i.imgur.com/7D7I6dI.png" alt="" />
            <div class="card__header-text">
              <h3 class="card__title">{{ confessionMeeting.ownerNickname }}</h3>            
              <span class="card__status">주제: {{ confessionMeeting.topicCategoryName }}</span>
            </div>
          </div>
          <div class="card__description" style="text-align: center;">
            <span style="font-weight: bold;">{{ confessionMeeting.description }}</span><br><hr>
            <span>{{ confessionMeeting.currJoinParticipants }} / {{ confessionMeeting.participants }}</span><br><br>
            <button
              type="button"
              class="front__text-hover"
              @click="clickEnterMeeting(confessionMeeting.meetingId)"
            >
              참가하기
            </button>
          </div>

        </div>
      </div>      
    </div>
  </div>
  <br>

  <!-- pagination -->
  <br>
  <div class="d-flex justify-content-center mb-5">
    <button id="prev" class="paginate left" @click="checkPage($event)"><i></i><i></i></button>
    <div class="counter">{{state.page}}페이지 / {{ state.confessionLastPageNum }}페이지 </div>
    <button id="next" class="paginate right" @click="checkPage($event)"><i></i><i></i></button>
  </div>
</template>

<script>
import { onMounted, reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'ConfessionView',
  props: {
    userInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: props.userInfo,
      confessionMeetingList: computed(() => store.getters['root/confessionMeetingList']),
      confessionLastPageNum: computed(() => store.getters["root/confessionLastPageNum"]),
      confessionMeetingInfo: computed(() => store.getters['root/confessionMeetingInfo']),
      categories: [
        { value: "학업", number: "1" },
        { value: "가정", number: "2" },
        { value: "취업", number: "3" },
        { value: "진로", number: "4" },
        { value: "연애", number: "5" },
        { value: "결혼", number: "6" },
      ],
      searchCategories: [
        { value: "닉네임", backValue: "ownerNickname" },
        { value: "방 제목", backValue: "title" },
        { value: "방 설명" , backValue: "description"},
      ],
      showKey: 'Select',
      key: "Select",
      word: null,
      page: 1,
      topic: null,
      pageSearchTopic: 'main',
    })

    onMounted(() => {
      const pr = document.querySelector('.paginate.left')
      const pl = document.querySelector('.paginate.right')

      pr.setAttribute('data-state', state.page===1 ? 'disabled' : '')
      if (state.page===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.page===state.confessionLastPageNum ? 'disabled' : '')
      if (state.page === state.confessionLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }
    })

    // 개설된 모든 방의 정보를 보여준다.
    const getConfessionView = async function() {
      state.pageSearchTopic = 'main'
      state.page = 1
      await store.dispatch('root/confessionGetMeetingList')

      const pr = document.querySelector('.paginate.left')
      const pl = document.querySelector('.paginate.right')

      pr.setAttribute('data-state', state.page===1 ? 'disabled' : '')
      if (state.page===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.page===state.confessionLastPageNum ? 'disabled' : '')
      if (state.page === state.confessionLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }
    }

    const clickConfessionCategory = async function (topic) {
      state.pageSearchTopic = 'topic'
      state.page = 1
      state.topic = topic
      await store.dispatch("root/confessionGetCategoryList", topic)
      const pr = document.querySelector('.paginate.left')
      const pl = document.querySelector('.paginate.right')

      pr.setAttribute('data-state', state.page===1 ? 'disabled' : '')
      if (state.page===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.page===state.confessionLastPageNum ? 'disabled' : '')
      if (state.page === state.confessionLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }
    }

    // 검색 시, 주제, 키워드, 현재 페이지를 넘겨준다.
    const clickConfessionSearch = async function() {
      state.pageSearchTopic = 'search'
      state.page = 1
      await store.dispatch("root/confessionGetSearchList", {
        key: state.key,
        value: state.word,
      })
      const pr = document.querySelector('.paginate.left')
      const pl = document.querySelector('.paginate.right')

      pr.setAttribute('data-state', state.page===1 ? 'disabled' : '')
      if (state.page===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.page===state.confessionLastPageNum ? 'disabled' : '')
      if (state.page === state.confessionLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }
    }
    
    // 내가 넘겨줄 것 : 보여줄 page size
    // 데이터들, 전체 page 수, 전체 게시글 갯수,   
    // 페이지 
    const checkPage = async function(event) {
      let targetId = event.currentTarget.id;
      if(targetId == "prev") {
          state.page -= 1;
      }
      else if(targetId == "next") {
          state.page += 1;   
      }

      const pr = document.querySelector('.paginate.left')
      const pl = document.querySelector('.paginate.right')

      pr.setAttribute('data-state', state.page===1 ? 'disabled' : '')
      if (state.page===1) {
        pr.disabled = true
      } else {
        pr.disabled = false
      }
      pl.setAttribute('data-state', state.page===state.confessionLastPageNum ? 'disabled' : '')
      if (state.page === state.confessionLastPageNum) {
        pl.disabled = true
      } else {
        pl.disabled = false
      }

      if(state.pageSearchTopic === 'main') {
        await store.dispatch("root/confessionPageSearch",{
          size: 6,
          page: state.page,
        })
      } else if (state.pageSearchTopic === 'topic') {
        await store.dispatch("root/confessionTopicPageSearch",{
          topicCategoryId: state.topic,
          size: 6,
          page: state.page,
        })
      } else if (state.pageSearchTopic === 'search') {
        await store.dispatch("root/confessionSearchPageSearch",{
          key: state.key,
          value: state.word,
          size: 6,
          page: state.page,
        })
      }
    }

    const clickEnterMeeting = async function (meetingId) {
      await store.dispatch('root/confessionEnterMeeting', meetingId)
      await router.push({
        name: 'MeetingConfession',
        params: {
          meeting_id: meetingId
        }
      })
    }

    const clickConfessionList = function(category) {
      state.showKey = category.value
      state.key = category.backValue
    }

    return {state, onMounted, getConfessionView, clickConfessionSearch, checkPage, clickConfessionCategory, clickEnterMeeting, clickConfessionList }
  }
}
</script>

<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css?family=Raleway:400,400i,700");

* {
  box-sizing: border-box;
}

body {
  font-family: 'Noto Sans JP', sans-serif;
/*   background-color: #fef8f8; */
}

input {
  display: none;
}

.like {
  display: block;
  width: var(--size);
  height: var(--size);
  cursor: pointer;
  border-radius: 999px;
  overflow: visible;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  -webkit-tap-highlight-color: transparent;
    --size: 80px;
  --frames: 62;
}

.hearth {
  background-image: url('https://assets.codepen.io/23500/Hashflag-AppleEvent.svg');
  background-size: calc(var(--size) * var(--frames)) var(--size);
  background-repeat: no-repeat;
  background-position-x: calc(var(--size) * (var(--frames) * -1 + 2));
  background-position-y: calc(var(--size) * 0.02);
  width: var(--size);
  height: var(--size);
  --size: 80px;
  --frames: 62;
}

input:checked + .hearth {
  animation: like 1s steps(calc(var(--frames) - 3));  
  animation-fill-mode: forwards;
}

@keyframes like {
  0% {
    background-position-x: 0;
  }
  100% {
    background-position-x: calc(var(--size) * (var(--frames) * -1 + 3));
  }
}

@media (hover: hover) {
  .like:hover {
    background-color: #E1255E15;
    .hearth {
      background-position-x: calc(var(--size) * (var(--frames) * -1 + 1));
    }
  }
}

a {
  text-decoration: none;
  color: inherit;
}
.cta { 
  position: relative;
  margin: auto;
  padding: 19px 22px;
  transition: all .2s ease;
  &:before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    display: block;
    border-radius: 28px;
    background: rgba(#bbd2f9,.5);
    width: 56px;
    height: 56px;
    transition: all .3s ease;
  }
  span {
    position: relative;
    font-size: 16px;
    line-height: 18px;
    font-weight: 900;
    letter-spacing: .25em;
    text-transform: uppercase;
    vertical-align: middle;
  }
  svg {
    position: relative;
    top: 0;
    margin-left: 10px;
    fill: none;
    stroke-linecap: round;
    stroke-linejoin: round;
    stroke: #111;
    stroke-width: 2;
    transform: translateX(-5px);
    transition: all .3s ease;
  }
  &:hover {
    &:before {
      width: 100%;
      background: rgba(#bbd2f9,1);
    }
    svg {
      transform: translateX(0);
    }
    &:active {
      transform: scale(.96);
    }
  }
}

.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin: 4rem 5vw;
  padding: 0;
  list-style-type: none;
}

.card {
  position: relative;
  display: flex;
  width: 100%;
  height: 40vh;  
  border-radius: calc(var(--curve) * 1px);
  overflow: hidden;
  text-decoration: none;
  --surface-color: #fff;
  --curve: 40;
  box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3),
    0 0 40px rgba(0, 0, 0, 0.1) inset;
}

.card__image {      
  width: 100%;
  height: auto;
}

.card__overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1;      
  border-radius: calc(var(--curve) * 1px);    
  background-color: var(--surface-color);      
  transform: translateY(100%);
  transition: .2s ease-in-out;
}

.card:hover .card__overlay {
  transform: translateY(0);
}

.card__header {
  position: relative;
  display: flex;
  align-items: center;
  gap: 2em;
  padding: 1em;
  border-radius: calc(var(--curve) * 1px) 0 0 0;    
  background-color: var(--surface-color);
  transform: translateY(-100%);
  transition: .2s ease-in-out;
}

.card__arc {
  width: 80px;
  height: 80px;
  position: absolute;
  bottom: 100%;
  right: 0;      
  z-index: 1;
}

.card__arc path {
  fill: var(--surface-color);
  d: path("M 40 80 c 22 0 40 -22 40 -40 v 40 Z");
}       

.card:hover .card__header {
  transform: translateY(0);
}

.card__thumb {
  flex-shrink: 0;
  width: 50px;
  height: 50px;      
  border-radius: 50%;      
}

.card__title {
  font-size: 1em;
  margin: 0 0 .3em;
  color: #6A515E;
}

.card__tagline {
  display: block;
  margin: 1em 0;
  font-family: "MockFlowFont";  
  font-size: .8em; 
  color: #D7BDCA;  
}

.card__status {
  font-size: .8em;
  color: #D7BDCA;
}

.card__description {
  padding: 0 2em 2em;
  margin: 0;
  color: #D7BDCA;
  font-family: "MockFlowFont";   
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 8;
  overflow: hidden;

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
    padding: 8px 15px;
    border-radius: 30px;

    background: #bbd2f9;
    color: #fff;
  }
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
  right: 67%;
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
  left: 44%;
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

.counter {
  text-align: center;
  position: absolute;
  width: 100%;
  margin-top: -15px;
  font-size: 20px;
  font-family: Helvetica, sans-serif;
  text-shadow: 0px 2px 0px rgba(0, 0, 0, 0.2);
  color: #708bef;
  z-index: -1;
}

// 검색창
.searchBox {
  position: relative;
  // transform:  translate(-50%,50%);
  background: white;
  height: 100%;
  width: 50%;
  border-radius: 40px;
  padding: 10px;
  border: 2px solid #bbd2f9;
}


.searchInput {
  display: block;
  border:none;
  background: none;
  outline:none;
  float:left;
  padding: 0;
  color: black;
  font-size: 16px;
  transition: 0.4s;
  line-height: 20px;
  width: 100%;
  padding: 0 6px;
}


@media screen and (max-width: 620px) {
.searchBox:hover > .searchInput {
    width: 150px;
    padding: 0 6px;
  }
}

.search-btn {
  position: relative;
  font-size: 15px;
  color: #bbd2f9;
  backface-visibility: hidden;
  width: 20%;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: .4px;

  border: 2px solid #bbd2f9;
  // padding: 8px 15px;
  border-radius: 30px;

  background: #bbd2f9;
  color: #fff;
}

.select-btn {
  position: relative;
  font-size: 15px;
  color: #bbd2f9;
  backface-visibility: hidden;
  width: 25%;
  font-weight: 700;
  // text-transform: uppercase;
  letter-spacing: .4px;

  border: 2px solid #bbd2f9;
  // padding: 8px 15px;
  border-radius: 30px;

  background: #bbd2f9;
  color: black !important;
}



// dropdown
.dropdown {
  position: relative;
  height: 100%;
  width: 45%;
  letter-spacing: 0.4px;
  border: 2px solid #bbd2f9;
  border-radius: 30px;
  background: #bbd2f9;
  color: black !important;;
}
.dropdown__face {
  display: block;
  position: relative;
}
.dropdown__items {
  margin: 0;
  position: absolute;
  right: 0;
  border: 2px solid #bbd2f9;
  border-radius: 30px;
  background: #bbd2f9;
  top: 50%;
  width: 100%;
  list-style: none;
  list-style-type: none;
  display: flex;
  justify-content: space-between;
  visibility: hidden;
  z-index: -1;
  opacity: 0;
  transition: all 0.4s cubic-bezier(0.93, 0.88, 0.1, 0.8);
}
.dropdown__items::before {
  content: "";
  background-color: #fff;
  position: absolute;
  bottom: 100%;
  right: 20%;
  height: 40px;
  width: 20px;
}
.dropdown__arrow {
  border-bottom: 2px solid #000;
  border-right: 2px solid #000;
  position: absolute;
  top: 50%;
  right: 30px;
  width: 10px;
  height: 10px;
  transform: rotate(45deg) translateY(-50%);
  transform-origin: right;
}
.dropdown input {
  display: none;
}
.dropdown input:checked ~ .dropdown__items {
  top: calc(100% + 25px);
  visibility: visible;
  opacity: 1;
}


</style>