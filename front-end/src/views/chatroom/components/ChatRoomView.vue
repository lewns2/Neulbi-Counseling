<template>
  <div>
    <div v-for="(chatRoom, idx) in state.chatRoomList" :key=idx>
      <p @click="chatRoomViewDetail(chatRoom.id)">{{ chatRoom.id }}</p>
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
    const chatRoomViewDetail = function (chatRoom_id) {
      store.dispatch("root/chatRoomViewDetail",
        { user_id: state.userInfo.id, chatRoom_id: chatRoom_id }
      )
    }

    return { state, chatRoomViewDetail }
  }
}
</script>

<style>

</style>