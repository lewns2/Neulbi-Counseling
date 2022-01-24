<template>
    <div>
        <h1>방생성</h1>
        <div class="container" id="container">
            <form :model="confessionChatRoom">
                <input type="text" placeholder="title" id="title"
						v-model="confessionChatRoom.title" />

                <!-- 선택하여 정수를 반환하도록 수정해야함 -->
                <input type="number" placeholder="topic_category_id" id="topic_category_id"
						v-model="confessionChatRoom.topicCategoryId" />

                <!-- 제한 인원 수 : 정수형 -->
                <input type="number" placeholder="participants" id="participants"
						v-model="confessionChatRoom.participants" />

                <input type="text" placeholder="description" id="description"
						v-model="confessionChatRoom.description" />
                <button type="button" @click="createConfessionChatRoom">생성</button>
            </form>
        </div>
    </div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
    name: 'ConfessionCreate',
    setup() {
        const store = useStore()

        const confessionChatRoom = reactive({
            title: null, 
            topicCategoryId: null,  
            participants: null, 
            description: null,
        })

        const createConfessionChatRoom = function() {
            console.log(confessionChatRoom)
            store.dispatch('root/createConfessionChatRoom', confessionChatRoom)
        }

        return { confessionChatRoom, createConfessionChatRoom }
    }
}
</script>

<style>

</style>