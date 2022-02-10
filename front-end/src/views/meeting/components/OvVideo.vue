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
