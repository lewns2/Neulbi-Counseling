<template>
    <div>
        <h2>전체 게시글 보기</h2>

        <p v-for="(communityView, index) in state.communityView" :key="index">
            <div>글 번호 : {{communityView.id}}</div>
            <div>제목 : {{communityView.title}}</div>
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
            communityView: computed(() => store.getters["root/communityView"])
        })

        onMounted(() => {
            store.dispatch("root/CommunityGetView")
            .then((res)=> {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            })
        });
        return {state, onMounted}
    }

}
</script>

<style>

</style>