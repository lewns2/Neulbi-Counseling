<template>
  <div>
      <CommunityView />
      <CommunityCreate />
  </div>
</template>

<script>
import CommunityCreate from '@/views/community/components/CommunityCreate.vue'
import CommunityView from '@/views/community/components/CommunityView.vue'

export default {
    name: "Confession",
    components: { CommunityCreate, CommunityView },
    setup() {

    }

}
</script>


<style>

</style>