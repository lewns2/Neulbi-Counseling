<template bgcolor="white">
  <div class="container">
    <div class="row"> </div>
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">

    <div style="margin-top: 5%">
      <h2>커뮤니티</h2>
    </div>

     <div class="column intro row-hover pos-relative py-4 px-4 mt-5 mb-5 row text-center">      
      <h6 style="color:black">'열린게시판'는 사용자들 간의 유용한 정보를 공유하고 서로 도움을 받을 수 있는 소통하는 공간입니다. 다양한 의견과 글을 자유롭게 게시하고 공유해주세요.</h6>
     </div>

    <!-- <div class="col-lg-9 mb-3">  -->
    <div class="inner-main-header">
      <!-- 검색부분 -->
      <span class="dropdown col-lg-3 row text-center op-7">
      <!-- 카테고리별 정렬 -->
        <div class="col px-1">
          <a @click="clickCommunityList">최신순</a>  
        </div>
        <div class="col px-1" v-for="(type, idx) in (['공감순', '조회순'])" :key="idx">
          <!-- <span v-for="(type, idx) in (['공감순', '조회순'])" :key="idx"> -->
            <a @click="clickSelectList(idx)">{{ type }}</a>
          <!-- </span>   -->
        </div>
        <div class="col px-1" @click="goToCreateCommunity">
          <a><i class="ion-edit"></i></a>
        </div>
      </span>
      
      <span class="col-lg-5"></span>
      
      <span>  
        <button class="form-control form-control-md dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
          {{ state.categoryselect }}
        </button>

        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li v-for="(category,idx) in state.categories" :key="idx" @click="clickSearchList(category)">{{ category.value }}</li>
        </ul>
      </span>

      <span class="input-icon input-icon-sm ml-auto col-lg-3 display: flex">
        <input type="text" v-model="state.searchInput" class="form-control form-control-md bg-gray-200 border-gray-200 shadow-none" placeholder="찾으시는 게시글이 있으신가요?" />
      </span>

      <button @click="clickSearchCommunity" class="form-control form-control-md col-lg-2" id="input">찾기</button>
    </div>

    <!--게시판 헤더-->
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
        <div class="col-md-4 op-7">
          <div class="row text-center op-7">
            <div class="col px-1"> <i class="ion-connection-bars icon-1x"></i> <span class="d-block text-sm">투표수</span> </div>
            <div class="col px-1"> <i class="ion-ios-chatboxes-outline icon-1x"></i> <span class="d-block text-sm">댓글수</span> </div>
            <div class="col px-1"> <i class="ion-ios-eye-outline icon-1x"></i> <span class="d-block text-sm">조회수</span> </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 게시글 리스트 -->
    <div v-for="(community, index) in state.communityList" :key="index" class="card row-hover pos-relative py-3 px-3" id="board-style1">
      <div class="row align-items-center">
        <div class="col-md-2 mb-3 mb-sm-0">
          <h6>{{community.id}}</h6>
        </div>
        <div class="col-md-4 mb-3 mb-sm-0" @click="goToCommunityDetail(community.id)">
          <h6>
            {{community.title}}
          </h6>
          
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h6>
            <p class="text-black">{{ community.userNickname }}</p>
          </h6>
        </div>
        
        
        <div class="col-md-4 op-7">
          <div class="row text-center op-7">
            <div class="col px-1"><span class="d-block text-sm">141 Votes</span> </div>
            <div class="col px-1"><span class="d-block text-sm">122 Replys</span> </div>
            <div class="col px-1"><span class="d-block text-sm">290 Views</span> </div>
            <!-- <div class="col px-1"> <i class="ion-connection-bars icon-1x"></i> <span class="d-block text-sm">141 Votes</span> </div>
            <div class="col px-1"> <i class="ion-ios-chatboxes-outline icon-1x"></i> <span class="d-block text-sm">122 Replys</span> </div>
            <div class="col px-1"> <i class="ion-ios-eye-outline icon-1x"></i> <span class="d-block text-sm">290 Views</span> </div> -->
          </div>
        </div>
      </div>
    </div>

    <!--페이지-->
    <div style="margin:3%">
      <button id="prev" @click="checkPage($event)" >이전</button>
        {{state.page}} 페이지 / {{ state.communityLastPageNum }} 페이지
      <button id="next" @click="checkPage($event)">다음</button>
    </div>
  </div>
</template>

<script>
import { computed, reactive } from '@vue/runtime-core'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'

export default {
  name: 'CommunityView',
  props: {
    userInfo: Object,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter()
    const state = reactive({
      userInfo: props.userInfo,
      communityList: computed(() => store.getters["root/communityList"]),
      communityLastPageNum: computed(() => store.getters["root/communityLastPageNum"]),
      categories: [
        { key: "description", value: "내용" },
        { key: "title", value: "제목" },
        { key: "nickname", value: "닉네임" },
      ],
      categoryselect: '선택',
      categorykey: '',
      searchInput: '',
      page: 1,
      topic: null,
      pageSearchTopic: 'main',
    })

    const clickCommunityList = async function () {
      state.pageSearchTopic = 'main'
      state.page = 1
      await store.dispatch('root/communityGetCommunityList')
    }

    const clickSearchList = function (category) {
      state.categoryselect = category.value
      state.categorykey = category.key
    }

    const clickSearchCommunity = async function () {
      state.pageSearchTopic = 'search'
      state.page = 1
      await store.dispatch('root/communityGetSearchList', {
        key: state.categorykey,
        value: state.searchInput,
      })
    }

    const clickSelectList = async function (idx) {
      state.pageSearchTopic = 'topic'
      state.page = 1
      state.topic = idx===0 ? 'like' : 'view'
      await store.dispatch('root/communityGetSelectList',{
        key: idx===0 ? 'like' : 'view'
      })
    }

    const checkPage = async function(event) {
      let targetId = event.currentTarget.id;
      if(targetId == "prev") {
          state.page -= 1;
          if(state.page < 1) state.page = 1;
      }
      else if(targetId == "next") {
          state.page += 1;   
      }
      if(state.pageSearchTopic === 'main') {
        await store.dispatch("root/communityPageSearch",{
          size: 8,
          page: state.page,
        })
      } else if (state.pageSearchTopic === 'topic') {
        await store.dispatch("root/communityTopicPageSearch",{
          topic: state.topic,
          size: 8,
          page: state.page,
        })
      } else if (state.pageSearchTopic === 'search') {
        await store.dispatch("root/communitySearchPageSearch",{
          key: state.categorykey,
          value: state.searchInput,
          size: 8,
          page: state.page,
        })
      }
    }

    const goToCommunityDetail = async function (community_id) {
      await store.dispatch("root/communityGetDetail", {
        user_id: state.userInfo.id, community_id: community_id
      })
      await router.push({
        name: 'CommunityDetail',
          params: {
            community_id: community_id
          }
      })
    }

    const goToCreateCommunity = function () {
      router.push({ name : "CommunityCreate" })
    }

    return { state,
    clickCommunityList,
    goToCommunityDetail,
    clickSelectList,
    clickSearchList,
    clickSearchCommunity,
    checkPage,
    goToCreateCommunity
    }
  }
}
</script>

<style>


body{
    margin-top:20px;
    background:#eee;
    color: #708090;
}
.icon-1x {
  font-size: 24px !important;
}
a{
  text-decoration:none;    
}
.text-primary, a.text-primary:focus, a.text-primary:hover {
    color: #1f4b97!important;
}
.text-black, .text-hover-black:hover {
    color: #000 !important;
}
.font-weight-bold {
    font-weight: 700 !important;
}

.inner-main-header {
    height: 6.0rem;
    border-bottom: 1px solid #cbd5e0;
    display: flex;
    align-items: center;
    padding: 0 1rem;
    flex-shrink: 0;
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
    background-color: #eceff3;
    background-clip: border-box;
    border-top: 2px solid black;
    border-bottom: 2px solid rgba(0,0,0,.125);
    /* border-radius: 0.25rem; */
}

#board-style1 {
  border-top: 0;
  border-bottom: 1px solid rgba(0,0,0,.125);
  border-left: 0;
  border-right: 0;
}

#board-style1:hover {
  background-color: snow;
}

.intro {
  background-color: #eceff3;
}

</style>