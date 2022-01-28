<template>
    <div>
        <h2>전체 게시글 보기</h2>
        {{state.userInfo}}
        <p v-for="(communityView, index) in state.communityView" :key="index">
            <div>글 번호 : {{communityView.id}}</div>
            <div>
            <router-link :to="{name:'CommunityDetail', params:{communityId: communityView.id}}">제목 : {{communityView.title}}</router-link>
            </div>
            <div>글쓴이 : {{communityView.author}}</div>
        </p>
        
    </div>
</template>

<script>
import { computed, onMounted, reactive } from '@vue/runtime-core'
import { useStore } from "vuex";

export default {
    name: 'CommunityView',
    setup() {
        const store = useStore();
        
        const state = reactive({
            communityView: computed(() => store.getters["root/communityView"]),
        })

        onMounted(() => {
            store.dispatch("root/CommunityGetView")
        });
        return {state, onMounted}
    }

}
</script>

<style>

</style>