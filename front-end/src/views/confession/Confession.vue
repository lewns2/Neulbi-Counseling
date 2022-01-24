<template>
    <div>
        <h1>고해성사</h1>

        <div class="row">
          <div class="col-sm-8" align="left">
            <ConfessionView />
            <!-- 페이지네이션 -->
            <!-- <advice-pagination></advice-pagination> -->
          </div>
          <div class="col-sm-4">
            <!-- <advice-user></advice-user> -->
            <ConfessionCreate />
          </div>
        </div>
        
        
        
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
