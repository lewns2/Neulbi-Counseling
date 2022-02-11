<template>
  <div class="container">
    <div v-for="(chatRoom, idx) in state.chatRoomList" :key=idx>
      <p @click="chatRoomGetDetail(chatRoom.id)">{{ chatRoom.id }}</p>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex"
import { reactive, computed } from 'vue'
export default {
  name: "ChatRoomView",
  props: {
    chatRoomList: Array
  },
  setup(props) {
    const store = useStore()
    const state = reactive ({
      chatRoomList: computed(() => props.chatRoomList),
      userInfo: store.getters['root/userInfo'],
    })
    const chatRoomGetDetail = function (chatRoom_id) {
      console.log(chatRoom_id)
      store.dispatch("root/chatRoomGetDetail",
        { user_id: state.userInfo.id, chatRoom_id: chatRoom_id }
      )
    }

    return { state, chatRoomGetDetail }
  }
}
</script>

<style>

</style>