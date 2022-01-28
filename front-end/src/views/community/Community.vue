<template>
  <div>
    <community-view :userInfo="state.userInfo"></community-view>
    <!-- router.push로 이동하게 하기 -->
    <community-create :userInfo="state.userInfo"></community-create>
  </div>
</template>

<script>
import { reactive} from "vue";
import { useStore } from "vuex";
import CommunityView from './components/CommunityView.vue';
import CommunityCreate from './components/CommunityCreate.vue';

export default {
    name: "Confession",
    components: { CommunityCreate, CommunityView },

    setup() {
      const store = useStore();

      const state = reactive({
        userInfo: store.getters['root/userInfo'],
      })

      return {state}
    }

}
</script>

<style></style>
