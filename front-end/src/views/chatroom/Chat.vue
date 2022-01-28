<template>
  <div id="app">
    유저이름: 
    <input
      v-model="data.userId"
      type="text"
    >
    내용: <input
      v-model="data.message"
      type="text"
      @keyup="sendMessage"
    >
    <!-- <button @click="sendMessage">작성</button> -->
    <div class="container"
      v-for="(item, idx) in state.recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.userId }}</h3>
      <h3>내용: {{ item.message }}</h3>
      <p>작성일: {{ item.createdDate }}</p>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { onMounted, reactive } from 'vue'

export default {
  name: "Chat",
  setup () {
    onMounted(() => {
      connect()
    })

    const data = reactive ({
      userId: "",
      message: "",
    })
    const state = reactive ({
      stompClient: null,
      socketConnected: false,
      recvList: []
    })
    
    const connect = function () {
      let socket = new SockJS("http://localhost:8080/chat");
      state.stompClient = Stomp.over(socket);
      state.stompClient.connect({},
        frame => {
          state.socketConnected = TextTrackCue
          state.stompClient.subscribe("/send", res => {
            state.recvList.push(JSON.parse(res.body))
          })
        },
        error => {
          state.socketConnected = false;
        }
      );        
    }
    const sendMessage = function (e) {
      if(e.keyCode === 13 && data.userId !== '' && data.message !== ''){
        send()
        data.message = ''
      }
    }
    const send = function () {
      if (state.stompClient && state.socketConnected) {
        const msg = { 
          userId: data.userId,
          message: data.message 
        };
        state.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    }

    return { data, state, onMounted, connect, sendMessage, send }
  }
}
</script>

<style>

</style>