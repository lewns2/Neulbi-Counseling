<template>
    <div class="left">
            <div class="top">
                <input type="text" placeholder="검색" />
                <a href="javascript:;" class="search"></a>
            </div>
            <ul class="people">
                <li class="person" data-chat="person1">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/thomas.jpg" alt="" />
                    <span class="name">Thomas Bangalter</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">I was wondering...</span>
                </li>
                <li class="person" data-chat="person2">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/dog.png" alt="" />
                    <span class="name">Dog Woofson</span>
                    <span class="time">1:44 PM</span>
                    <span class="preview">I've forgotten how it felt before</span>
                </li>
                <li class="person" data-chat="person3">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/louis-ck.jpeg" alt="" />
                    <span class="name">Louis CK</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">But we’re probably gonna need a new carpet.</span>
                </li>
                <li class="person" data-chat="person4">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/bo-jackson.jpg" alt="" />
                    <span class="name">Bo Jackson</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">It’s not that bad...</span>
                </li>
                <li class="person" data-chat="person5">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/michael-jordan.jpg" alt="" />
                    <span class="name">Michael Jordan</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">Wasup for the third time like is 
you blind bitch</span>
                </li>
                <li class="person" data-chat="person6">
                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/drake.jpg" alt="" />
                    <span class="name">Drake</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">howdoyoudoaspace</span>
                </li>
            </ul>
        </div>
    <!-- <div v-for="(chatRoom, idx) in state.chatRoomList" :key=idx>
      <p @click="chatRoomGetDetail(chatRoom.id)">{{ chatRoom.id }}</p>
    </div> -->
  
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