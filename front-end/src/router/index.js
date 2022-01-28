import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/home/Home.vue";
import SignUp from "../views/main/components/SignUp.vue";
import SignIn from "../views/main/components/SignIn.vue";
import Profile from "../views/profile/Profile.vue";
import Confession from "../views/confession/Confession.vue";
import Advice from "../views/advice/Advice.vue";
import Community from "../views/community/Community.vue";
import CommunityDetail from "../views/community/components/CommunityDetail";
import Chat from "../views/chatroom/Chat.vue"
import ChatRoom from "../views/chatroom/ChatRoom.vue"
import Meeting from "../views/meeting/Meeting.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/signup",
    name: "SignUp",
    component: SignUp,
  },
  {
    path: "/signin",
    name: "SignIn",
    component: SignIn,
  },
  {
    path: "/user/:user_id",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/confession",
    name: "Confession",
    component: Confession,
  },
  {
    path: "/advice",
    name: "Advice",
    component: Advice,
  },
  {
    path: "/community",
    name: "Community",
    component: Community,
    children: [
      {
        path: "deatil/:communityId",
        name: "CommunityDetail",
        component: CommunityDetail,
      },
    ],
  },
  {
    path: "/chat",
    name: "Chat",
    component: Chat,
  },
  {
    path: "/chatroom/:user_id",
    name: "ChatRoom",
    component: ChatRoom,
  },
  {
    path: "/meeting",
    name: "Meeting",
    component: Meeting,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
