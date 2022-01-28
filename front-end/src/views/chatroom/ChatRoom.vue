<template>
  <h1>chatroom</h1>
  <chat-room-view :chatRoomList="state.chatRoomList"></chat-room-view>
  <chat-room-personal></chat-room-personal>
</template>

<script>
import ChatRoomView from "./components/ChatRoomView.vue"
import ChatRoomPersonal from "./components/ChatRoomPersonal.vue"
import { useStore } from "vuex";
import { reactive, computed } from 'vue';

export default {
  name: "ChatRoom",
  components: {
    ChatRoomView,
    ChatRoomPersonal,
  },
  setup() {
    const store = useStore()

    const state = reactive({
      chatRoomList: computed(() => store.getters["root/chatRoomList"])
    })

    return { state }
  }
};
</script>

<style></style>
