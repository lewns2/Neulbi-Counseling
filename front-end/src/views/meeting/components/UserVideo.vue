<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <div>
      <p>{{ state.clientData }}</p>
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
      console.log(JSON.parse(connection.data));
      return JSON.parse(connection.data);
    };

    return { state, getConnectionData };
  },
};
</script>
