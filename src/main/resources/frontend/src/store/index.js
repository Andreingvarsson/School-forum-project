import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedInUser: null,
    forum: null,
    forums: null,
    thread: null,
    threads: null,
    message: null,
    messages: null,
    users: null,
    user: null,
  },
  mutations: {
    setForums(state, data) {
      state.forums = data;
    },
    setForum(state, data) {
      state.forum = data;
    },
    createNewThread(state, data) {
      state.forum.threads.push(data);
    },
    setThreads(state, data) {
      state.thread = data;
    },
    setThread(state, data) {
      state.thread = data;
    },
    setLoggedInUser(state, data) {
      state.loggedInUser = data;
    },
    setMessage(state, data){
      state.message = data;
    },
    setMessages(state, data){
      state.messages = data;
    },
    createNewMessage(state, data){
      state.thread.messages.push(data);
    },
    setUser(state, data){
      state.user = data;
    },
    setUsers(state, data){
      state.users = data;
    }
  },
  actions: {
    async fetchAllForums({ commit }) {
      console.log("Inne i fetchAllForums");
      const forumResult = await fetch("/api/v1/forums");
      const forums = await forumResult.json();
      console.log(forums, "Alla forums");
      commit("setForums", forums);
    },

    async fetchForumById({ commit }, id) {
      console.log("inne i BYID");
      const forumResult = await fetch(`/api/v1/forums/${id}`);
      const forum = await forumResult.json();
      console.log(forum, "ett forum");
      commit("setForum", forum);
    },

    async fetchMessageByThreadId({commit}, id){
      console.log("INNE I MESSAGEBYTHREADID");
      const messageResult = await fetch(`/api/v1/threads/${id}/messages`);
      const message = await messageResult.json();
      console.log(message, "ETT MESSAGE");
      commit("setMessage", message);
    },

    async fetchAllUsers({commit}){
      console.log("INNE I FETCH USERS BY ID");
      const userResult = await fetch("/api/v1/users");
      const user = await userResult.json();
      console.log(user, "Alla users");
      commit("setUser", user);
    },

    async whoami({ commit }) {
      let user = await fetch("/auth/whoami");
      try {
        user = await user.json();
        console.log("User", user);
        commit("setLoggedInUser", user);
      } catch {
        console.log("Not authenticated");
      }
    },
  },
  modules: {},
});
