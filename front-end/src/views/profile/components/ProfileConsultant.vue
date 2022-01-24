<template>
  <div>
    <p>ProfileConsultant</p>
    <p>{{ userInfo }}</p>
    <p>{{ state.profileConsultant }}</p>
    <button type="button" @click="clickModifyConsultantProfile">
      수정하기
    </button>
  </div>
</template>

<script>
import { computed, reactive } from "vue";
import { useStore } from "vuex";
export default {
  name: "ProfileConsultant",
  props: {
    userInfo: Array,
  },
  setup(props) {
    const store = useStore();
    const state = reactive({
      profileConsultant: computed(
        () => store.getters["root/profileConsultant"]
      ),
      description: "",
      topic: "",
    });

    store.dispatch("root/profileGetConsultantProfile", props.userInfo.id);

    const clickModifyConsultantProfile = function () {
      store
        .dispatch("root/profileModifyConsultantProfile", {
          id: props.userInfo.id,
          description: state.description,
          topicCategoryId: state.topic,
        })
        .then((res) => {
          console.log(res);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    return { state, clickModifyConsultantProfile };
  },
};
</script>

<style></style>
