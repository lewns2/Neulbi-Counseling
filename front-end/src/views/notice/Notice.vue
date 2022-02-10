<template>
  <notice-view></notice-view>
</template>

<script>
import NoticeView from "./components/NoticeView.vue";
import { onMounted, reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "Notice",
  components: {
    NoticeView,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"]),
    });

    onMounted(async () => {
      await store.dispatch("root/noticeGetNoticeList");
    });

    const goToCreateNotice = async function () {
      router.push({ name: "NoticeCreate" });
    };

    return { state, onMounted, goToCreateNotice };
  },
};
</script>

<style></style>
