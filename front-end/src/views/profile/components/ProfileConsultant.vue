<template>
  <div>
    <h1>ProfileConsultant</h1>
    <div v-if="state.profileConsultantProfile == 'No'">
      상담가 등록이 필요합니다.
    </div>
    <div v-else>
      <p>{{ state.profileConsultantProfile.topicCategoryName }}</p>
      <p>{{ state.profileConsultantProfile.description }}</p>

      <select class="form-select" v-model="state.topic">
        <option selected>선택</option>
        <option
          v-for="(category, index) in state.categories"
          :key="index"
          :value="category.number"
        >
          {{ category.value }}
        </option>
      </select>
      <button type="button" @click="clickProfileModifyTopicCategory">
        수정하기
      </button>
      <br><br>
      <textarea cols="30" rows="10" v-model="state.description"></textarea><br>
      <button type="button" @click="clickProfileModifyDescription">
        수정하기
      </button>
      <profile-consultant-list :userInfo="state.userInfo">
      </profile-consultant-list>
    </div>
  </div>
</template>

<script>
import ProfileConsultantList from './ProfileConsultantList.vue'

import { reactive, computed } from "vue";
import { useStore } from "vuex";
export default {
  name: "ProfileConsultant",
  components: {
    ProfileConsultantList,
  },
  props: {
    userInfo: Object,
  },
  setup(props) {
    const store = useStore();
    const state = reactive({
      userInfo: props.userInfo,
      profileConsultantProfile: computed(() => store.getters["root/profileConsultantProfile"]),
      description: "",
      topic: "",
      categories: [
        { value: "학업", number: "1" },
        { value: "취업", number: "2" },
        { value: "이직", number: "3" },
      ],
    })
    
    const clickProfileModifyTopicCategory = async function () {
      await store.dispatch("root/profileModifyTopicCategory", {
        id: props.userInfo.id,
        topicCategoryId: state.topic,
      })
      await store.dispatch('root/profileGetConsultantProfile', state.userInfo.id)
    }

    const clickProfileModifyDescription = async function () {
      console.log(state.description)
      await store.dispatch("root/profileModifyDescription", {
        user_id: props.userInfo.id,
        description: state.description
      })
      await store.dispatch('root/profileGetConsultantProfile', state.userInfo.id)
    }

    return { state, clickProfileModifyTopicCategory, clickProfileModifyDescription };
  },
}
</script>

<style></style>
