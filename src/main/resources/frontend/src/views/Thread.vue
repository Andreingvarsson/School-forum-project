<template>
  <div v-if="thread" class="thread container ">
    <div class="card">
      <div class="card-header title">
        {{ thread.title }}
      </div>
      <div class="card-body">
        <blockquote class="blockquote mb-0 message">
          <p>{{ thread.threadMessage }}</p>
          <footer class="blockquote-footer">
            Author: {{ thread.threadOwner.username }}
          </footer>
        </blockquote>
      </div>
    </div>
    <Message
      v-for="message in thread.messages"
      :key="message.message_id"
      :message="message"
      :forum_id="thread.forum_id"
    />
    <form
      v-if="!this.thread.lockedThread"
      @submit.prevent="createMessage"
      class="form-group mt-3"
    >
      <label for="message">Reply on post</label>
      <input
        type="message"
        required
        v-model="message.createdMessage"
        class="form-control"
        id="message"
        placeholder="message.."
      />
      <button class="mt-2 button" type="submit">Send message</button>
      <div class="form-group" v-if="admin || moderator">
        <div class="form-check">
          <input
            class="form-check-input"
            type="checkbox"
            id="warningMessage"
            v-model="message.warningMessage"
          />
          <label class="form-check-label" for="warningMessage">
            Warning message
          </label>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import Message from "../components/Message";

@Component({
  components: { Message },
})
class Thread extends Vue {
  message = {
    createdMessage: null,
    warningMessage: null,
  };

  get user() {
    return this.$store.state.loggedInUser;
  }

  get thread() {
    return this.$store.state.thread;
  }

  get forums() {
    return this.$store.state.forums;
  }

  get admin() {
    return this.user ? this.user.roles.includes("ADMIN") : false;
  }

  get moderator() {
    console.log(this.thread.forum_id, "FORUMID Thread");
    return this.user?.moderatedForums.includes(this.thread.forum_id);
  }

  async createMessage() {
    console.log("Inne i createMessage funktionen");
    let id = this.$route.params.id;
    console.log(this.message, "MESSAGE");

    let newMessage = await fetch(`/api/v1/threads/${id}/messages`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(this.message),
      credentials: "include",
    });
    newMessage = await newMessage.json();
    console.log(newMessage, "new Message");
    this.$store.commit("createNewMessage", newMessage);
    this.message.createdMessage = null;
    this.message.warningMessage = null;
  }

  async created() {
    if (!this.thread) {
      console.log("INNE I Created thread");
      await this.$store.dispatch("fetchThread", this.$route.params.id);
    }
  }
}

export default Thread;
</script>

<style scoped>
.card {
  border: solid;
}

.title {
  font-size: 20px;
  font-weight: bold;
  background-color: rgba(1, 146, 1, 0.788);
}

.message {
  font-size: 17px;
}
</style>
