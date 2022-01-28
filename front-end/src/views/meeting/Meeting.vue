<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!state.session">
      <div id="img-div">
        <img src="resources/images/openvidu_grey_bg_transp_cropped.png" />
      </div>
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>Join a video session</h1>
        <div class="form-group">
          <p>
            <label>Participant</label>
            <input
              v-model="state.myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>Session</label>
            <input
              v-model="state.mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              Join!
            </button>
          </p>
        </div>
      </div>
    </div>

    <div id="session" v-if="state.session">
      <div id="session-header">
        <h1 id="session-title">{{ state.mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
      </div>
      <div id="main-video" class="col-md-6">
        <user-video :stream-manager="state.mainStreamManager" />
      </div>
      <div id="video-container" class="col-md-6">
        <user-video
          :stream-manager="state.publisher"
          @click.native="updateMainVideoStreamManager(state.publisher)"
        />
        <user-video
          v-for="sub in state.subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click.native="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "./components/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

import { reactive } from "vue";

export default {
  name: "Meeting",

  components: {
    UserVideo,
  },

  setup() {
    const state = reactive({
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    });
    const joinSession = function () {
      // --- Get an OpenVidu object ---
      state.OV = new OpenVidu();
      // --- Init a session ---
      state.session = state.OV.initSession();
      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      state.session.on("streamCreated", ({ stream }) => {
        const subscriber = state.session.subscribe(stream);
        state.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      state.session.on("streamDestroyed", ({ stream }) => {
        const index = state.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          state.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      state.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      getToken(state.mySessionId).then((token) => {
        state.session
          .connect(token, { clientData: state.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = state.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            state.mainStreamManager = publisher;
            state.publisher = publisher;

            // --- Publish your stream ---
            state.session.publish(state.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", leaveSession);
    };

    const leaveSession = function () {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (state.session) {
        console.log("끝났나요");
        state.session.disconnect();
      }

      state.session = undefined;
      state.mainStreamManager = undefined;
      state.publisher = undefined;
      state.subscribers = [];
      state.OV = undefined;

      window.removeEventListener("beforeunload", leaveSession);
    };

    const updateMainVideoStreamManager = function (stream) {
      if (state.mainStreamManager === stream) return;
      state.mainStreamManager = stream;
    };

    const getToken = function (mySessionId) {
      return createSession(mySessionId).then((sessionId) =>
        createToken(sessionId)
      );
    };

    const createSession = function (sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    };

    const createToken = function (sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    };

    return {
      state,
      joinSession,
      leaveSession,
      updateMainVideoStreamManager,
      getToken,
      createSession,
      createToken,
    };
  },
};
</script>

<style>
input.btn {
  font-weight: bold;
}

.btn {
  font-weight: bold !important;
}

.btn-success {
  background-color: #06d362 !important;
  border-color: #06d362;
}

.btn-success:hover {
  background-color: #1abd61 !important;
  border-color: #1abd61;
}

.openvidu-logo {
  height: 35px;
  float: right;
  margin: 12px 0;
  -webkit-transition: all 0.1s ease-in-out;
  -moz-transition: all 0.1s ease-in-out;
  -o-transition: all 0.1s ease-in-out;
  transition: all 0.1s ease-in-out;
}

.openvidu-logo:hover {
  -webkit-filter: grayscale(0.5);
  filter: grayscale(0.5);
}

.demo-logo {
  margin: 0;
  height: 22px;
  float: left;
  padding-right: 8px;
}

a:hover .demo-logo {
  -webkit-filter: brightness(0.7);
  filter: brightness(0.7);
}

#join-dialog {
  margin-left: auto;
  margin-right: auto;
  max-width: 70%;
}

#join-dialog h1 {
  color: #4d4d4d;
  font-weight: bold;
  text-align: center;
}

#img-div {
  text-align: center;
  margin-top: 3em;
  margin-bottom: 3em;
  /*position: relative;
	top: 20%;
	left: 50%;
	transform: translate(-50%, -50%);*/
}

#img-div img {
  height: 15%;
}

#join-dialog label {
  color: #0088aa;
}

#join-dialog input.btn {
  margin-top: 15px;
}

#session-header {
  margin-bottom: 20px;
}

#session-title {
  display: inline-block;
}

#buttonLeaveSession {
  float: right;
  margin-top: 20px;
}

#video-container video {
  position: relative;
  float: left;
  width: 50%;
  cursor: pointer;
}

#video-container video + div {
  float: left;
  width: 50%;
  position: relative;
  margin-left: -50%;
}

#video-container p {
  display: inline-block;
  background: #f8f8f8;
  padding-left: 5px;
  padding-right: 5px;
  color: #777777;
  font-weight: bold;
  border-bottom-right-radius: 4px;
}

video {
  width: 100%;
  height: auto;
}

#main-video p {
  position: absolute;
  display: inline-block;
  background: #f8f8f8;
  padding-left: 5px;
  padding-right: 5px;
  font-size: 22px;
  color: #777777;
  font-weight: bold;
  border-bottom-right-radius: 4px;
}

#session img {
  width: 100%;
  height: auto;
  display: inline-block;
  object-fit: contain;
  vertical-align: baseline;
}

#session #video-container img {
  position: relative;
  float: left;
  width: 50%;
  cursor: pointer;
  object-fit: cover;
  height: 180px;
}

/* xs ans md screen resolutions*/

@media screen and (max-width: 991px) and (orientation: portrait) {
  #join-dialog {
    max-width: inherit;
  }
  #img-div img {
    height: 10%;
  }
  #img-div {
    margin-top: 2em;
    margin-bottom: 2em;
  }
  .container-fluid > .navbar-collapse,
  .container-fluid > .navbar-header,
  .container > .navbar-collapse,
  .container > .navbar-header {
    margin-right: 0;
    margin-left: 0;
  }
  .navbar-header i.fa {
    font-size: 30px;
  }
  .navbar-header a.nav-icon {
    padding: 7px 3px 7px 3px;
  }
}

@media only screen and (max-height: 767px) and (orientation: landscape) {
  #img-div {
    margin-top: 1em;
    margin-bottom: 1em;
  }
  #join-dialog {
    max-width: inherit;
  }
}
</style>
