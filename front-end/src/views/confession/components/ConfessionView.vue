<template>
  <div>
      <h1>방 보기</h1>

      <!-- 카테고리 클릭 시, 주제에 해당하는 값을 부여한다. -->
      <div>
        <!-- 모든 방 보기 -->
        <button type="button" @click="getConfessionView">모든 방 보기</button>
        
        <!-- 원하는 카테고리 보기 -->
        <button
            type="button"
            class="btn btn-light"
            v-for="(category, index) in state.categories"
            :key="index"
            @click="clickConfessionCategory(category.number)"
        >
            {{ category.value }}
        </button>
      </div>
    
      <!-- 검색 창 -->
        <h3>검색</h3>
            <select name="" id="" v-model="state.key">
                <option
                v-for="(searchCategory, index) in state.searchCategories"
                :key="index"
                :value="searchCategory.backValue"
                >
                {{ searchCategory.value }}
                </option>
            </select>
        <input type="text" v-model="state.word" />
        <button type="button" class="btn btn-light me-md-2" @click="clickSearch">
              검색
        </button>

      <!-- 방을 출력하는 곳 : 한 페이지에 보여줄 것 정해야한다.-->
      <div>
          방을 보여주는 곳입니다. <br>
          {{state.rooms}}
      </div>
      
        <button id="prev" @click="checkPage($event)">이전</button>
        {{state.page}} 페이지
        <button id="next" @click="checkPage($event)">다음</button>

  </div>
</template>

<script>
import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'

export default {
    name: 'ConfessionView',
    setup() {
        const store = useStore()

        const state = reactive({
            rooms: [],
            topic_category_id : null,
            categories: [
                { value: "학습", number: 1 },
                { value: "취업", number: 2 },
                { value: "이직", number: 3 },
            ],

            searchCategories: [
                { value: "닉네임", backValue: "nickname" },
                { value: "방 제목", backValue: "title" },
            ],
            key: null,
            word: null,
            page: 1,
        })

        // 개설된 모든 방의 정보를 보여준다.
        const getConfessionView = function() {
                store.dispatch('root/confessionGetView')
                .then((res)=> {
                    console.log(res)
                    state.rooms.push(res)
                })
                .catch((err)=> {
                    console.log(err);
                })
        }
        const clickConfessionCategory = function(topic) {
            console.log(topic);
            store.dispatch('root/confessionGetTopicView', topic)
            .then((res) => {
                console.log(res)
                state.rooms = []
                state.rooms.push(res)
            })
            .catch((err) => {
                console.log(err)
            })

        }
        // 검색 시, 주제, 키워드, 현재 페이지를 넘겨준다.
        const clickSearch = function() {
            console.log(state.key, state.word, state.page);
            store.dispatch("root/confessionSearch", {
                key: state.key,
                value: state.word,
                pageSize: 6,
                page: state.page,
            })
            .then((res) => {
                console.log(res);
                state.page = 1;
            })
            .catch((error) => {
                console.log(error);
            })
        }
        
        // 내가 넘겨줄 것 : 보여줄 page size
        // 데이터들, 전체 page 수, 전체 게시글 갯수,   
        // 페이지 
        const checkPage = function(event) {
            let targetId = event.currentTarget.id;
            
            if(targetId == "prev") {
                state.page -= 1;
                if(state.page < 1) state.page = 1;

            }
            else if(targetId == "next") {
                state.page += 1;
                
            }
            store.dispatch("root/advicePageSearch",{
                pageSize: 6,
                page: state.page,
            })
            .then((res) => {
                console.log(res)
            })
            .catch((err)=> {
                console.log(err)
            })
        }

        return {state, onMounted, getConfessionView, clickSearch, checkPage, clickConfessionCategory }
    }
}
</script>

<style>

</style>