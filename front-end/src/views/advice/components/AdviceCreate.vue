<template>
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
  <br />
  <div class="input-group mb-3">
    <span class="input-group-text">설명글</span>
    <textarea
      class="form-control"
      id="description"
      rows="3"
      v-model="state.description"
    ></textarea>
  </div>

  <br />
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button
      type="button"
      class="btn btn-light me-md-2"
      @click="clickIsConsultant"
    >
      상담가 신청
    </button>
  </div>
  <br />
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";
export default {
  name: "AdviceCreate",
  setup() {
    const store = useStore();
    const state = reactive({
      description: "",
      topic: "",
      categories: [
        { value: "학업", number: "1" },
        { value: "취업", number: "2" },
        { value: "이직", number: "3" },
      ],
      userInfo: computed(() => store.getters["root/userInfo"]),
    });

    const clickIsConsultant = function () {
      console.log(state.description);
      console.log(state.topic);
      store
        .dispatch("root/adviceCreateConsultant", {
          description: state.description,
          topicCategoryId: state.topic,
          userId: state.userInfo.id,
        })
        .then((res) => {
          console.log(res);
          if (res.status == 200) {
            console.log("상담가 신청 완료");
            store.dispatch("root/adviceGetView");
            store.dispatch("root/adviceGetRank");
          } else {
            console.log("오류 발생");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };

    return { state, clickIsConsultant };
  },
};
</script>

<style></style>
