<template>
    <div>
        <h2>게시글 생성</h2>
        <form :model="article">
            <input type="text" placeholder="title" id="title"
					v-model="article.title" />

            <input type="text" placeholder="description" id="description"
					v-model="article.description" />
            <button type="button" @click="createArticle">생성</button>
                
        </form>
    </div>
  
</template>

<script>
import { reactive} from "vue";
import { useStore } from "vuex";
export default {
    name: "CommunityCreate",
    props: {
        userInfo: Array,
    },
    setup(props) {
        const store = useStore();
        
        const article = reactive({
            title: null, 
            description: null,
            user: props.userInfo.id,
        })

        const createArticle = function() {
            // console.log(props.userInfo)
            // console.log(article)
            store.dispatch("root/communityCreateChatRoom", article)
            .then((res) => {
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
        } 

        return {article, createArticle, reactive};
    }

}
</script>

<style>

</style>