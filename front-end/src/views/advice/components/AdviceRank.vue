<template>
  <h3>랭킹 순위 주르륵</h3>
  <p v-for="(adviceRank, index) in state.adviceRanks" :key="index">
    {{ adviceRank.id }} {{ adviceRank.nickname }}{{ adviceRank.pointTot }}
  </p>
</template>

<script>
import { computed, onMounted, reactive } from "vue";
import { useStore } from "vuex";

export default {
  name: "AdviceRank",
  setup() {
    const store = useStore();

    const state = reactive({
      adviceRanks: computed(() => store.getters["root/adviceRank"]),
    });

    store.dispatch("root/adviceGetRank");

    return { state, onMounted };
  },
};
</script>

<style></style>
