<template>
    <div>
        <h1>고해성사</h1>

        <ConfessionCreate />
        <ConfessionView />
        
    </div>
</template>

<script>
import ConfessionCreate from '@/views/confession/components/ConfessionCreate.vue'
import ConfessionView from '@/views/confession/components/ConfessionView.vue'

export default {
  name: "Confession",
  components: { ConfessionCreate, ConfessionView },
  setup() {

  }
}
</script>

<style></style>
