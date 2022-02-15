<template>
  <main-header></main-header>
  <div class="wrapper">
    <div class="container">
      <div class="left">
        <chat-room-view :chatRoomList="state.chatRoomList"></chat-room-view>
      </div>
      <div class="right">
        <chat-room-personal></chat-room-personal>
      </div>
    </div>
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

<style scoped lang="scss">
@charset "UTF-8";
*, *:before, *:after {
  box-sizing: border-box;
}

.wrapper {
  position: absolute;
  left: 50%;
  width: 100%;
  height: 800px;
  transform: translate(-50%, 0);
}

.container {
  position: relative;
  margin-top: 400pt;
  margin-left: 50%;
  width: 80%;
  height: 100%;
  background-color: var(#fff);
  border: 1px solid black;
  border-radius: 30px;
  transform: translate(-50%, -50%);
  padding-left: 0px;
  padding-right: 0px;
}
.container .left {
  float: left;
  width: 37.6%;
  height: 100%;
  border: 1px solid var(#e6e6e6);
  background-color: var(#fff);
}

.container .right {
  position: relative;
  border-left: 1px solid #eceff1;
  float: left;
  width: 62.4%;
  height: 100%;
}

</style>
