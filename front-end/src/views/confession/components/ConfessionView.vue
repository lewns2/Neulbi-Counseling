<template>
  <div>
      <h1>방 보기</h1>

      <!-- 카테고리 클릭 시, 주제에 해당하는 값을 부여한다. -->
      <div>
        <!-- 모든 방 보기 -->
        <button type="button" @click="getConfessionView">모든 방 보기</button>
        
        <!-- 원하는 카테고리 보기 -->
        <button type="button" id="study" @click="getTopicCategoryId($event)">학업</button>
        <button type="button" id="course" @click="getTopicCategoryId($event)">진로</button>
        <button type="button" id="employment" @click="getTopicCategoryId($event)">취업</button>
      </div>
    
      <!-- 검색 창 -->
      <div>
          <input class="room-search" type="text" v-model="search" placeholder="검색하는 곳"  
          @input="handleSearchInput"  @keydown.tab="KeydownTab">
      </div>
      

      <!-- 방을 출력하는 곳 : 한 페이지에 보여줄 것 정해야한다.-->
      <div>
          방을 보여주는 곳입니다. <br>
          {{state.rooms}}
      </div>
      


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
            rooms: ['a', 'b', 'c', 'd', 'e', 'aa', 'bb', 'abc', 'ef'],
            topic_category_id : null,
        })

        // 개설된 모든 방의 정보를 보여준다.
        const getConfessionView = function() {
                store.dispatch('root/getConfessionView')
                .then((res)=> {
                    console.log(res)
                    state.rooms.push(res)
                })
                .catch((err)=> {
                    console.log(err);
                })
        }

        // 특정 카테고리 클릭 시, 해당 카테고리의 방 정보를 보여준다.
        const getTopicCategoryId = function(event) {
            // state.rooms.push("test")
            let targetId = event.currentTarget.id;
            // console.log(targetId)
            if(targetId == 'study') {
                state.topic_category_id = 1;
            }
            if(targetId == 'course') {
                state.topic_category_id = 2;
            }
            if(targetId == 'employment') {
                state.topic_category_id = 3;
            }
            // console.log(state.topic_category_id)
            store.dispatch('root/getTopicConfessionView', state.topic_category_id)
            .then((res) => {
                console.log(res)
                state.rooms = []
                state.rooms.push(res)
            })
            .catch((err) => {
                console.log(err)
            })
        }

        // 검색 창 구현 : 디바운싱 사용이 필요해보임. -> lodash가 필요함. -> 일단 보류
        // ! 지우면 돌아오지 않는 문제 해결해야함.
        const handleSearchInput = function(event) {
            let search = event.target.value;
            console.log(search);

            const filteredList = state.rooms.filter(item => item.includes(search));
            console.log(filteredList)
            state.rooms = filteredList
        }

        return {state, onMounted, getConfessionView, getTopicCategoryId, handleSearchInput}
    }
}
</script>

<style>

</style>