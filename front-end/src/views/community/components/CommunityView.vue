<template>
  <div class="container">
    <div class="row"> </div>
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
    
    <div>
      <h2>커뮤니티</h2>
    </div>

    <!-- <div class="col-lg-9 mb-3">  -->
    <div class="inner-main-header">
      <!-- 검색부분 -->
      <span class="dropdown col-lg-3 row text-center op-7">
      <!-- 카테고리별 정렬 -->
        <div class="col px-1">
          <h6 @click="clickCommunityList">최신순</h6>  
        </div>
        <div class="col px-1" v-for="(type, idx) in (['공감순', '조회순'])" :key="idx">
          <!-- <span v-for="(type, idx) in (['공감순', '조회순'])" :key="idx"> -->
            <h6 @click="clickSelectList(idx)">{{ type }}</h6>
          <!-- </span>   -->
        </div>
        <div class="col px-1"></div>
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
    <div class="card row-hover pos-relative py-3 px-3 mb-3 border-warning border-top-0 border-right-0 border-bottom-0 rounded-0">
      <div class="row align-items-center">
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>
            <a href="#" class="text-primary">번호</a>
          </h5>
        </div>
        <div class="col-md-4 mb-3 mb-sm-0">
          <h5>
            <a href="#" class="text-primary">제목</a>
          </h5>
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>
            <a href="#" class="text-primary">작성자</a>
          </h5>
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
    <div v-for="(community, index) in state.communityList" :key="index" class="card row-hover pos-relative py-3 px-3 mb-3 border-warning border-top-0 border-right-0 border-bottom-0 rounded-0">
      <div class="row align-items-center">
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>
            <p>{{community.id}}</p>
          </h5>
        </div>
        <div class="col-md-4 mb-3 mb-sm-0">
          <h5 @click="goToCommunityDetail(community.id)" class="text-primary">
            {{community.title}}
          </h5>
          
        </div>
        <div class="col-md-2 mb-3 mb-sm-0">
          <h5>
            <p class="text-black">{{ community.userNickname }}</p>
          </h5>
        </div>
        
        
        <div class="col-md-4 op-7">
          <div class="row text-center op-7">
            <div class="col px-1"> <i class="ion-connection-bars icon-1x"></i> <span class="d-block text-sm">141 Votes</span> </div>
            <div class="col px-1"> <i class="ion-ios-chatboxes-outline icon-1x"></i> <span class="d-block text-sm">122 Replys</span> </div>
            <div class="col px-1"> <i class="ion-ios-eye-outline icon-1x"></i> <span class="d-block text-sm">290 Views</span> </div>
          </div>
        </div>
      </div>
    </div>

    <!--페이지-->
    <button id="prev" @click="checkPage($event)">이전</button>
      {{state.page}} 페이지 / {{ state.communityLastPageNum }} 페이지
    <button id="next" @click="checkPage($event)">다음</button>
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

    return { state,
    clickCommunityList,
    goToCommunityDetail,
    clickSelectList,
    clickSearchList,
    clickSearchCommunity,
    checkPage
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
    color: #00ADBB!important;
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



</style>