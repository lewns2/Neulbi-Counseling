<template>
  <div>
    <h1>My Consultant</h1>
    <br>
    <div v-if="(state.profileConsultantLikeActive)">
      <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="card">
              <img :src="'/profile/image/'+state.profileConsultantLikeActive.id" class="card__image" alt="" />
              <div class="card__overlay">
                <div class="card__header">
                  <svg class="card__arc" xmlns="http://www.w3.org/2000/svg"><path /></svg>                     
                  <img class="card__thumb" src="https://i.imgur.com/7D7I6dI.png" alt="" />
                  <div class="card__header-text">
                    <h3 class="card__title">{{ state.profileConsultantLikeActive.nickname }}</h3>            
                    <span class="card__status">주제: {{ state.profileConsultantLikeActive.topicCategoryName }}</span>
                  </div>
                </div>
                <p class="card__description">
                  포인트 : {{ state.profileConsultantLikeActive.pointTot }}<br>
                  상담횟수 : {{ state.profileConsultantLikeActive.consultingCnt }}<br>
                  한 줄 소개 : <br>
                  {{ state.profileConsultantLikeActive.description }} <br><br>
                  <button
                    type="button"
                    class="front__text-hover"
                    @click="clickCreateChatRoom(state.profileConsultantLikeActive.id)"
                  >
                    1:1 채팅하기
                  </button>
                </p>
              </div>
            </div>   
          </div>
          <div class="carousel-item" v-for="(consultant, index) in state.profileConsultantLike" :key="index">
            <div class="card">
              <img :src="'/profile/image/'+consultant.id" class="card__image" alt="" />
              <div class="card__overlay">
                <div class="card__header">
                  <svg class="card__arc" xmlns="http://www.w3.org/2000/svg"><path /></svg>                     
                  <img class="card__thumb" src="https://i.imgur.com/7D7I6dI.png" alt="" />
                  <div class="card__header-text">
                    <h3 class="card__title">{{ consultant.nickname }}</h3>            
                    <span class="card__status">주제: {{ consultant.topicCategoryName }}</span>
                  </div>
                </div>
                <p class="card__description">
                  포인트 : {{ consultant.pointTot }}<br>
                  상담횟수 : {{ consultant.consultingCnt }}<br>
                  한 줄 소개 : <br>
                  {{ consultant.description }} <br><br>
                  <button
                    type="button"
                    class="front__text-hover"
                    @click="clickCreateChatRoom(consultant.id)"
                  >
                    1:1 채팅하기
                  </button>
                </p>

              </div>
            </div>   
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true" style="background-color: #c2d6f8;"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true" id="next-button" style="background-color: #c2d6f8;"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
    <div v-else>없습니다.</div>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive, computed } from 'vue'

export default {
  name: "ProfileConsultantList",
  props:{
    userInfo: Object,
  },
  setup (props) {
    const store = useStore()
    const state = reactive ({
      userInfo: props.userInfo,
      profileConsultantLikeActive: computed(() => store.getters['root/profileConsultantLikeActive']),
      profileConsultantLike: computed(() => store.getters['root/profileConsultantLike']),
    })

    return { 
      state,
    }
  }
}
</script>

<style scoped lang="scss">

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
  width: 50%;
  height: 40vh;
  left: 26%;
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
    top: 10px;
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
</style>