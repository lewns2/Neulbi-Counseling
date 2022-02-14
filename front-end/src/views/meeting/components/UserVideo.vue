<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <div id="whatis">
      <div id="nameismute">
        <p>
          {{ state.clientData.clientData }}
        </p>
        <p v-if="streamManager.stream.videoActive">
          <i class="fas fa-video" style="color: green; font-size: 15px"></i>
        </p>
        <p v-else>
          <i class="fas fa-video-slash" style="color: red; font-size: 15px"></i>
        </p>
        <p v-if="streamManager.stream.audioActive">
          <i
            class="fas fa-microphone"
            style="color: green; font-size: 15px"
          ></i>
        </p>
        <p v-else>
          <i
            class="fas fa-microphone-slash"
            style="color: red; font-size: 15px"
          ></i>
        </p>
      </div>
      <div id="maskimg" v-if="!streamManager.stream.videoActive">
        <!-- <img
          :src="streamManager.stream.connection.data.split('%/%')[1]"
          alt=""
        /> -->
        <img src="@/assets/back1.jpg" alt="" />
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from "@vue/reactivity";
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },
  setup(props) {
    const state = reactive({
      clientData: computed(() => getConnectionData()),
    });

    const getConnectionData = function () {
      const { connection } = props.streamManager.stream;
      const conn = connection.data.split("%/%");
      console.log(conn[0]);
      return JSON.parse(conn[0]);
    };

    return { state, getConnectionData };
  },
};
</script>

<style scoped>
#maskimg {
  position: relative;
  width: 100%;
  cursor: pointer;
}

#nameismute {
  margin-top: 20px;
  display: inline-block;
  justify-content: center;
  align-items: center;
}

#maskimg {
  display: inline-block;
  justify-content: center;
  align-items: center;
}

#whatis {
  display: grid;
  justify-content: center;
  align-items: center;
  justify-items: center;
}
</style>
