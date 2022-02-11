<template>
  <div class="right">
      <div class="top"><span>To: 
                    <span class="name">Dog Woofson</span></span>
                    </div>
      <div class="chat" data-chat="person1">
          <div class="conversation-start">
              <span>Today, 6:48 AM</span>
          </div>
          <div class="bubble you">
              Hello,
          </div>
          <div class="bubble you">
              it's me.
          </div>
          <div class="bubble you">
              I was wondering...
          </div>
      </div>
      <div class="chat" data-chat="person2">
          <div class="conversation-start">
              <span>Today, 5:38 PM</span>
          </div>
          <div class="bubble you">
              Hello, can you hear me?
          </div>
          <div class="bubble you">
              I'm in California dreaming
          </div>
          <div class="bubble me">
              ... about who we used to be.
          </div>
          <div class="bubble me">
              Are you serious?
          </div>
          <div class="bubble you">
              When we were younger and free...
          </div>
          <div class="bubble you">
              I've forgotten how it felt before
          </div>
      </div>
      <div class="chat" data-chat="person3">
          <div class="conversation-start">
              <span>Today, 3:38 AM</span>
          </div>
          <div class="bubble you">
              Hey human!
          </div>
          <div class="bubble you">
              Umm... Someone took a shit in the hallway.
          </div>
          <div class="bubble me">
              ... what.
          </div>
          <div class="bubble me">
              Are you serious?
          </div>
          <div class="bubble you">
              I mean...
          </div>
          <div class="bubble you">
              It’s not that bad...
          </div>
          <div class="bubble you">
              But we’re probably gonna need a new carpet.
          </div>
      </div>
      <div class="chat" data-chat="person4">
          <div class="conversation-start">
              <span>Yesterday, 4:20 PM</span>
          </div>
          <div class="bubble me">
              Hey human!
          </div>
          <div class="bubble me">
              Umm... Someone took a shit in the hallway.
          </div>
          <div class="bubble you">
              ... what.
          </div>
          <div class="bubble you">
              Are you serious?
          </div>
          <div class="bubble me">
              I mean...
          </div>
          <div class="bubble me">
              It’s not that bad...
          </div>
      </div>
      <div class="chat" data-chat="person5">
          <div class="conversation-start">
              <span>Today, 6:28 AM</span>
          </div>
          <div class="bubble you">
              Wasup
          </div>
          <div class="bubble you">
              Wasup
          </div>
          <div class="bubble you">
              Wasup for the third time like is <br />you blind bitch
          </div>

      </div>
      <div class="chat" data-chat="person6">
          <div class="conversation-start">
              <span>Monday, 1:27 PM</span>
          </div>
          <div class="bubble you">
              So, how's your new phone?
          </div>
          <div class="bubble you">
              You finally have a smartphone :D
          </div>
          <div class="bubble me">
              Drake?
          </div>
          <div class="bubble me">
              Why aren't you answering?
          </div>
          <div class="bubble you">
              howdoyoudoaspace
          </div>
      </div>
      <div class="write">
          <a href="javascript:;" class="write-link attach"></a>
          <input type="text" />
          <a href="javascript:;" class="write-link smiley"></a>
          <a href="javascript:;" class="write-link send"></a>
      </div>
  </div>
  <!-- <div class="container">
    <div v-if="state.userInfo.id === state.chatRoomUserList[1]">
      <button @click="clickCreateMeeting">화상채팅 생성</button>
    </div>
    <div v-else>
      <div v-if="state.meetingIsActive">
        <button @click="clickEnterMeeting">화상채팅 참가</button>
      </div>
    </div>
    <div id="app">
      내용: 
      <input
        v-model="data.message"
        type="text"
        @keyup="sendMessage"
      >
       <button @click="sendMessage">작성</button> 
       {{ chatRoom.chatRoomId }}
      <div class="container"
        v-for="(message, idx) in chatRoom.chatRoomMessage"
        :key="idx"
      >
        <p>{{ message.userId }}: {{ message.message }}</p>
      </div>
    </div>
  </div> -->
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import { computed, onMounted, reactive } from 'vue'
import { useStore } from "vuex"
import { useRouter } from 'vue-router'

export default {
  name: "ChatRoomPersonal",
  setup () {
    const store = useStore()
    const router = useRouter()
    onMounted(() => {
      connect()
    })

    const state = reactive ({
      stompClient: null,
      socketConnected: false,
      recvList: [],
      userInfo: computed(() => store.getters["root/userInfo"]),
      // chatRoomUserList 순서 => user_id / consultant_id / topic_id of consultant
      chatRoomUserList: computed(() => store.getters["root/chatRoomUserList"]),
      adviceMeetingInfo: computed(() => store.getters['root/adviceMeetingInfo']),
      meetingIsActive: computed(() => store.getters['root/meetingIsActive'])
    })

    const chatRoom = reactive ({
      chatRoomId: computed(() => store.getters['root/chatRoomId']),
      chatRoomMessage: computed(() => store.getters['root/chatRoomMessage']),
    })

    const data = reactive ({
      userId: state.userInfo.id,
      message: "",
    })
    
    const connect = function () {
      let socket = new SockJS("http://localhost:8080/chat");
      state.stompClient = Stomp.over(socket);
      state.stompClient.connect({},
        frame => {
          state.socketConnected = TextTrackCue
          state.stompClient.subscribe("/send", res => {
            store.dispatch('root/chatRoomGetDetail', { user_id: data.userId, chatRoom_id: chatRoom.chatRoomId })
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
    const send = async function () {
      if (state.stompClient && state.socketConnected) {
        const body = { 
          chatRoomId: chatRoom.chatRoomId,
          userId: data.userId,
          message: data.message 
        };
        await state.stompClient.send("/receive", JSON.stringify(body), {});
        await store.dispatch('root/chatRoomGetDetail', { user_id: body.userId, chatRoom_id: body.chatRoomId })

      }
    }

    const clickCreateMeeting = async function () {
      const body = {
        title: 'Advice',
        description: 'Advice Start',
        participants: 2,
        meetingCategoryId: 2,
        topicCategoryId: state.chatRoomUserList[2],
        ownerId: state.chatRoomUserList[1],
        chatRoomId: chatRoom.chatRoomId,
      }
      await store.dispatch("root/chatRoomCreateMeeting", body)
      await router.push({
        name: 'MeetingAdvice',
        params: {
          meeting_id: state.adviceMeetingInfo.meetingId
        }
      })
    }

    const clickEnterMeeting = async function () {
      await store.dispatch("root/chatRoomEnterMeeting", chatRoom.chatRoomId)
      await router.push({
        name: 'MeetingAdvice',
        params: {
          meeting_id: state.adviceMeetingInfo.meetingId
        }
      })
    }

    return { 
      data,
      state,
      chatRoom,
      onMounted,
      connect,
      sendMessage,
      send,
      clickCreateMeeting,
      clickEnterMeeting
    }
  }
}
</script>

<style>

</style>