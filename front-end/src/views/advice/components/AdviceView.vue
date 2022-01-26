<template>
  <h3>주제 선택</h3>
  {{ state.adviceView }}
  <button
    type="button"
    class="btn btn-light"
    v-for="(category, index) in state.categories"
    :key="index"
    @click="clickAdviceCategory(category.number)"
  >
    {{ category.value }}
  </button>
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
  
  <button id="prev" @click="checkPage($event)">이전</button>
    {{state.page}} 페이지 / {{ state.total_page }} 페이지
  <button id="next" @click="checkPage($event)">다음</button>

  <h3>상담가들 주르륵</h3>
  <p v-for="(adviceView, index) in state.adviceView" :key="index">
    앞면 정보<br />
    상담가번호: {{ adviceView.id }} 상담가 닉네임:
    {{ adviceView.nickname }} 상담가 프로필이미지:
    {{ adviceView.profileImg }} 상담가 포인트: {{ adviceView.pointTot }}
    <br />
    뒷면 정보<br />
    포인트: {{ adviceView.pointTot }}주제:
    {{ adviceView.topicCategoryId }}한줄소개:
    {{ adviceView.description }} 상담수:{{ adviceView.consultingCnt }}
    <button
      type="button"
      class="btn btn-light me-md-2"
      @click="clickCreateChatRoom(adviceView.id)"
    >
      1:1 채팅하기
    </button>
  </p>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
import router from "@/router";
export default {
  name: "AdviceView",
  setup() {
    const store = useStore();
    const state = reactive({
      adviceView: computed(() => store.getters["root/adviceView"]),
      categories: [
        { value: "학업", number: "1" },
        { value: "취업", number: "2" },
        { value: "이직", number: "3" },
      ],
      searchCategories: [
        { value: "닉네임", backValue: "nickname" },
        { value: "설명", backValue: "description" },
      ],
      key: null,
      word: null,
      page: 1,
      total_page: computed(() => store.getters["root/adviceTotalLength"]),
    });

    store.dispatch("root/adviceGetView");

    const clickAdviceCategory = function (topic) {
      console.log(topic);
      store.dispatch("root/adviceGetCategory", topic);
    };

    const clickCreateChatRoom = function (consultant_id) {
      store
        .dispatch("root/adviceCreateChatRoom", consultant_id)
        .then((res) => {
          console.log(res);
          if (res.status == 200) {
            console.log("채팅방 생성 성공");
            router.push({ name: "ChatRoom" });
          } else {
            console.log("오류 발생");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const clickSearch = function () {
      console.log(state.key);
      store
        .dispatch("root/adviceSearch", {
          key: state.key,
          value: state.word,
          size: 1,
          page: state.page,
        })
        .then((res) => {
          if (res.status == 200) {
            console.log('검색', res)
            store.commit("root/SET_ADVICE_PAGENUM", 1);
            store.commit("root/CLEAR_ADVICE_VIEW_TOTAL");
            store.commit("root/SET_ADVICE_VIEW_TOTAL", res.data.totalPages);
            store.commit("root/CLEAR_ADVICE_VIEW");
            store.commit("root/SET_ADVICE_VIEW", res.data.content);
          } else {
            console.log("오류 발생");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };

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
                size: 1,
                page: state.page,
            })
            .then((res) => {
              store.commit("root/CLEAR_ADVICE_VIEW");
              store.commit("root/SET_ADVICE_VIEW", res.data.content);
              console.log(res)
            })
            .catch((err)=> {
              console.log(err)
            })
        }

    return { state, clickAdviceCategory, clickCreateChatRoom, clickSearch, checkPage};
  },
};
</script>

<style></style>
