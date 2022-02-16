<template>
  <div style="background-color: rgb(225 236 255)">
    <div id="main-container" class="container" style="height: 100vh">
      <!-- meeting 입장 초기 화면 -->
      <div id="review">
        <div
          id="review-dialog"
          class="jumbotron vertical-center"
          style="padding-top: 50px"
        >
          <div
            class="d-flex justify-content-center"
            style="border-bottom: 3px solid #a6c0fe"
          >
            <i class="fas fa-quote-left" style="font-size: 20px"></i>
            <h1 style="color: #333333; font-family: Century Gothic, sans-serif">
              후기
            </h1>
            <i class="fas fa-quote-right" style="font-size: 20px"></i>
          </div>
          <br />
          <div
            class="form-group"
            style="color: #333333; font-family: Century Gothic, sans-serif"
          >
            <div class="col-md-2">
              <button
                @click="state.reportBool = !state.reportBool"
                class="custom-form-control-red form-control-md col-lg-2"
                data-bs-toggle="modal"
                data-bs-target="#exampleModal2"
                id="input"
              >
                신고
              </button>
              <button
                id="input"
                class="form-control form-control-md col-lg-2"
                @click="goToHome"
              >
                Home
              </button>
            </div>
            <!-- 신고 모달 -->
            <div
              class="modal fade"
              id="exampleModal2"
              v-if="state.reportBool"
              tabindex="-1"
              aria-labelledby="exampleModalLabel2"
              aria-hidden="true"
            >
              <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel2">
                      신고하기
                    </h5>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body">
                    <form>
                      <div class="row">
                        <div class="col-md-3">
                          <span>
                            <button
                              class="form-control form-control-md dropdown-toggle"
                              type="button"
                              id="dropdownMenuButton1"
                              data-bs-toggle="dropdown"
                              aria-expanded="false"
                            >
                              {{ state.reportNickname }}
                            </button>

                            <ul
                              class="dropdown-menu"
                              aria-labelledby="dropdownMenuButton1"
                              style="
                                min-width: 10rem;
                                border: 2px solid #bbd2f9;
                                border-radius: 30px;
                                padding: 0px;
                              "
                            >
                              <li
                                v-for="(
                                  person, idx
                                ) in state.confessionReviewList"
                                :key="idx"
                                @click="clickSelectNickname(person)"
                              >
                                {{ person.nickName }}
                              </li>
                            </ul>
                          </span>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-8">
                          <textarea
                            cols="30"
                            rows="5"
                            v-model="state.reportMsg"
                            placeholder="신고 내용을 작성해주세요."
                          ></textarea>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button
                      class="custom-form-control-red"
                      type="button"
                      @click="clickReviewReportDetail"
                    >
                      보내기
                    </button>
                    <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Select</button> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from "@vue/reactivity";
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";

export default {
  name: "ReviewConfession",
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      confessionReviewList: computed(
        () => store.getters["root/confessionReviewList"]
      ),
      reviewIndex: -1,
      user_id: route.params.user_id,
      meeting_id: route.params.meeting_id,
      reportBool: false,
      reportMsg: "",
      reportNickname: "닉네임",
      reportUserId: "",
    });

    const clickIsReport = async function (idx) {
      if (state.reviewIndex === -1) {
        state.reviewIndex = idx;
      } else {
        state.reviewIndex = -1;
      }
    };

    const clickReviewReportDetail = function () {
      store.dispatch("root/reportDetail", {
        category: "confession",
        reportUserId: state.reportUserId,
        userId: state.user_id,
        reportMsg: state.reportMsg,
      });
      state.reportMsg = "";
      state.reviewIndex = -1;
    };

    const goToHome = function () {
      router.push({
        name: "Home",
      });
    };

    const clickSelectNickname = function (person) {
      console.log(person);
      state.reportNickname = person.nickName;
      state.reportUserId = person.userId;
      console.log(state.reportNickname);
    };

    return {
      state,
      clickIsReport,
      clickReviewReportDetail,
      goToHome,
      clickSelectNickname,
    };
  },
};
</script>

<style scoped lang="scss">
textarea {
  width: 100%;
  height: 150px;
  border: none;
  resize: none;
}

#input {
  margin-left: 10px;
  width: auto;
  display: inline;
}

.custom-form-control {
  color: black;
  font-weight: bold;
  display: block;
  width: 100%;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.custom-form-control-red {
  // color: red;
  font-weight: bold;
  display: block;
  width: 100%;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: red;
  background-color: white;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

#review-dialog {
  margin-left: auto;
  margin-right: auto;
  max-width: 70%;
}

#review-dialog h1 {
  color: #4d4d4d;
  font-weight: bold;
  text-align: center;
}

#main-container {
  padding-bottom: 80px;
  padding-top: 1%;
}
</style>
