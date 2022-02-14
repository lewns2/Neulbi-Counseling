<template>
  <video ref="root" autoplay />
</template>

<script>
import { onMounted } from "@vue/runtime-core";
import { ref } from "vue";

export default {
  name: "OvVideo",
  props: {
    streamManager: Object,
  },

  setup(props) {
    const root = ref(null);

    onMounted(() => {
      props.streamManager.addVideoElement(root.value);
    });

    return { root };
  },
};
</script>

<style scoped lang="scss">
#video-container video {
  display: flex;
  justify-content: center;
  align-items: center;

  position: relative;
  float: left;
  width: 47%;
  cursor: pointer;
  margin-left: 25px;
  padding-top: 5%;
  border-radius: 10px;
}

#video-container video + div {
  float: left;
  width: 50%;
  position: relative;
  margin-left: -50%;
  display: grid;
}

#video-container p {
  display: inline-block;
  padding-left: 5px;
  padding-right: 5px;
  color: #ffe54c;
  font-weight: bold;
  border-bottom-right-radius: 4px;
}

video {
  width: 100%;
  height: auto;
}
</style>
