<template>
  <div>
    <!-- 댓글 작성 -->
    <div class="newcommnet">
      <form>
        <h5 class="pull-left" id="leftalign">총 N개의 댓글이 있습니다.</h5>
        <fieldset>
            <div class="row">
                <div class="col-2">
                  <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzAxMTRfMjYy%2FMDAxNDg0MzcxOTkxNzA4._N73NTpWleCLp8M6gXR8vpdDAZoAQ2mTJLimKBYFtRwg.5LEqnsukFugxlrTdlYk5hkxEKoVdUbTVsjL6gqJ03vIg.PNG.koomarin%2F%253F%253F%253F%253F%257B%253F_%253F%253F%253F%253F%253F%253F%253F.png&type=sc960_832" alt="" />
                  <!-- <img class="img-responsive" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt=""> -->
                </div>
                <div class="form-group col-8">
                  <textarea class="form-control" v-model="state.commentDescription" id="message" placeholder="댓글을 작성해주세요" required=""></textarea>
                </div>
                <div class="col-2">
                  <button type="button" @click="clickCreateComment(0)" class="btn btn-normal">Submit</button>
                </div>
            </div>  	
        </fieldset>
      </form>
    </div>
    
    <hr>

    <!-- 댓글 보여주기 -->
    <div v-for="(comment, idx) in state.communityComment" :key=idx class="card row-hover pos-relative py-3 px-3" id="board-style1"> 
      <div v-if="comment.layer==0" class="row align-items-center">
        <div class="col-2">
          <span>유저이미지</span>
        </div>
        <div class="col-8" id="leftalign">
          <div>
            {{comment.userNickname}}
            <span>2주 전</span>
          </div>
          <div>
            {{comment.description}}
          </div>
          <!--대댓글에 관한-->
          <button @click="clickCommentIsComment(idx, comment.groupNum)" v-if="state.commentIsCommentNumber!=idx">더보기</button>
          <span>답글 쓰기</span> |
          <span>답글 보기</span> |
          <span>취소</span>
          
        </div>
        <!--root 댓글의 수정/삭제-->
        <div class="col-1" v-if="state.userInfo.id === comment.userId">
          <span >수정</span>
        </div>
        <div class="col-1" v-if="state.userInfo.id === comment.userId">
            <span>삭제</span>
        </div>
      </div>
      
      <!-- <hr> -->
      
        <div v-if="idx===state.commentIsCommentNumber">
          <div v-for="(coComment, index) in state.communityComment" :key=index>
            <div v-if="coComment.layer==1&coComment.groupNum==state.commentGroupNum" class="row align-items-center">
              <!-- {{ coComment }} -->
              
              <div class="col-2">
                <span>유저이미지</span>
              </div>
              <div class="col-8" id="leftalign">
                <div>
                  {{ coComment.userNickname }}
                  <span>방금 전</span>
                </div>
                <div>
                  {{ coComment.description }}
                </div>
              </div>
              <div class="col-1" v-if="state.userInfo.id === comment.userId">
                <span @click="clickModifyComment(coComment)" >수정</span>
              </div>
              <div class="col-1" v-if="state.userInfo.id === comment.userId">
                  <span @click="clickDeleteComment(coComment.commentId)">삭제</span>
              </div>
              <!-- 대댓글 수정 부분 -->
          </div>
        </div>

              <!-- 대댓글 수정 부분 -->
              <!-- <span v-if="state.userInfo.id === coComment.userId">
                <button @click="clickCommentUpdate(index)" v-if="state.commentIsCommentUpdateNumber!=index">수정</button>
                <div v-if="index==state.commentIsCommentUpdateNumber">
                  <textarea
                    cols="30"
                    rows="1"
                    v-model="coComment.description"
                    placeholder="댓글을 작성해주세요."
                  ></textarea>
                  <button @click="clickModifyComment(coComment)">수정</button>
                </div> -->
                <!-- 대댓글 삭제 부분 -->
                <!-- <button @click="clickDeleteComment(coComment.commentId)">삭제</button>
              </span> -->
            

          <!-- 대댓글 작성부분 -->
          <textarea class="form-control" v-model="state.commentCommentDescription" id="message" placeholder="댓글을 작성해주세요" required=""></textarea>
          <!-- <input type="text" v-model="state.commentCommentDescription"> -->
          <button @click="clickCreateComment(comment.commentId)">대댓글 작성</button>
          <button @click="clickCommentIsComment(-1, '')">더보기 닫기</button>
          <div></div>
        </div>
      

    </div>

    

  </div>
</template>

<script>
import { computed, reactive } from "vue"
import { useStore } from "vuex"

export default {
  name: 'CommentView',
  props: {
    userInfo: Object,
    communityDetail: Object,
  },
  setup (props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      communityDetail: props.communityDetail,
      commentDescription: "",
      communityComment: computed(() => store.getters["root/communityComment"]),
      commentIsUpdateNumber: -1,
      commentIsCommentNumber: -1,
      commentGroupNum: '',
      commentIsCommentUpdateNumber: -1,
      commentCommentDescription: '',
    })

    const clickCreateComment = async function (commentId) {
      await store.dispatch("root/communityCreateComment", {
        communityId: state.communityDetail.community_id,
        userId: state.userInfo.id,
        description: commentId===0 ? state.commentDescription : state.commentCommentDescription,
        groupNum: commentId
      })
      state.commentDescription = ""
      state.commentCommentDescription = ""
    }

    const clickIsUpdate = function (idx) {
      if (state.commentIsUpdateNumber === -1) {
        state.commentIsUpdateNumber = idx
      } else {
        state.commentIsUpdateNumber = -1
      }
    }

    const clickModifyComment = async function (comment) {
      await store.dispatch('root/communityModifyComment',{
        userId: state.userInfo.id,
        commentId: comment.commentId,
        community_id: state.communityDetail.community_id,
        description: comment.description
      })
      state.commentIsUpdateNumber = -1
      state.commentIsCommentUpdateNumber = -1
    }

    const clickDeleteComment = async function (commentId) {
      await store.dispatch("root/communityDeleteComment",{
        userId: state.userInfo.id,
        commentId: commentId,
        community_id: state.communityDetail.community_id
      })
    }

    const clickCommentIsComment = function (idx, groupNum) {
      if (state.commentIsCommentNumber === -1) {
        state.commentIsCommentNumber = idx
        state.commentGroupNum = groupNum
      } else {
        state.commentIsCommentNumber = idx
        state.commentGroupNum = groupNum
      }
    }

    const clickCommentUpdate = function (index) {
      if (state.commentIsCommentUpdateNumber === -1) {
        state.commentIsCommentUpdateNumber = index
      } else {
        state.commentIsCommentUpdateNumber = -1
      }
    }

    

    return {
      state,
      clickCreateComment,
      clickIsUpdate,
      clickModifyComment,
      clickDeleteComment,
      clickCommentIsComment,
      clickCommentUpdate,
    }
  }
}
</script>

<style scoped lang="scss">
#leftalign {
  text-align: left;
}

img {
  float: left;
  width: 40px;
  height: 40px;
  margin-left: 15px;
  margin-right: 12px;
  border-radius: 50%;
  -o-object-fit: cover;
  object-fit: cover;
}

#board-style1 {
  border-top: 0;
  border-bottom: 1px solid rgba(0,0,0,.125);
  border-left: 0;
  border-right: 0;
}

</style>