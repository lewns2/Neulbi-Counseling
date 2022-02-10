<template>
  <div id="help">
    <div>
      참가자 (<span>{{ state.participantnums }}</span
      >)
    </div>

    <div id="text_wrapper">
      <div class="avatar"></div>
      {{
        JSON.parse(state.publisher.stream.connection.data.split("%/%")[0])
          .clientData
      }}
      <i
        v-if="state.publisher.stream.audioActive"
        class="fas fa-microphone"
        style="color: green; font-size: 15px"
      ></i>
      <i
        v-else
        class="fas fa-microphone-slash"
        style="color: red; font-size: 15px"
      ></i>
      <i
        v-if="state.publisher.stream.videoActive"
        class="fas fa-video"
        style="color: green; font-size: 15px"
      ></i>
      <i
        v-else
        class="fas fa-video-slash"
        style="color: red; font-size: 15px"
      ></i>
    </div>

    <div
      id="text_wrapper"
      v-for="(sub, index) in state.subscribers"
      :key="index"
    >
      <div class="avatar"></div>
      {{ JSON.parse(sub.stream.connection.data.split("%/%")[0]).clientData }}
      <i
        v-if="sub.stream.audioActive"
        class="fas fa-microphone"
        style="color: green; font-size: 15px"
      ></i>
      <i
        v-else
        class="fas fa-microphone-slash"
        style="color: red; font-size: 15px"
      ></i>
      <i
        v-if="sub.stream.videoActive"
        class="fas fa-video"
        style="color: green; font-size: 15px"
      ></i>
      <i
        v-else
        class="fas fa-video-slash"
        style="color: red; font-size: 15px"
      ></i>
    </div>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
export default {
  name: "MeetingParticipant",
  props: {
    publisher: Object,
    subscribers: Object,
  },
  setup(props) {
    const state = reactive({
      publisher: props.publisher,
      subscribers: props.subscribers,
      participantnums: props.subscribers.length + 1,
    });

    return { state };
  },
};
</script>

<style>
#help {
  /* position: absolute; */
  width: calc(100% - 20px);
  max-width: 800px;
  height: 500px;
  border-radius: 10px;
  left: 50%;
  top: 50%;
  transform: translateX(-1%) translateY(-1%);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
  background-color: #eeeeee;
  overflow: hidden;
}

#text_wrapper {
  display: inline-block;
  padding: 20px;
  border-radius: 15px;
  width: calc(100% - 85px);
  min-width: 100px;
  position: relative;

  background-color: #ffe6cb;
  margin-left: 20px;
  margin-bottom: 20px;
}

.avatar {
  background-color: #f5886e;
  float: left;

  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: inline-block;
}
</style>
