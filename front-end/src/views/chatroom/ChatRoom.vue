<template>
  <main-header></main-header>
  <div class="d-flex justify-content-around">
    <chat-room-view :chatRoomList="state.chatRoomList"></chat-room-view>
    <chat-room-personal></chat-room-personal>
  </div>
</template>

<script>
import MainHeader from "../main/components/MainHeader.vue";
import ChatRoomView from "./components/ChatRoomView.vue"
import ChatRoomPersonal from "./components/ChatRoomPersonal.vue"
import { useStore } from "vuex";
import { reactive, computed, onMounted } from 'vue';

export default {
  name: "ChatRoom",
  components: {
    MainHeader,
    ChatRoomView,
    ChatRoomPersonal,
  },
  setup() {
    const store = useStore()

    const state = reactive({
      userInfo: computed(() => store.getters['root/userInfo']),
      chatRoomList: computed(() => store.getters["root/chatRoomList"]),
    })

    onMounted(async() => {
      store.dispatch('root/chatRoomGetList', { userId: state.userInfo.id })
    })

    return { state, onMounted }
  }
};
</script>

<style></style>
