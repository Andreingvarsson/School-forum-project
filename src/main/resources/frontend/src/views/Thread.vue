<template>
  <div class="thread container ">
    <div class="card">
      <div v-if="thread" class="card-header title">
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
    />
    <!-- v-for message etc to loop all messages.-->
    <form @submit.prevent="createMessage" class="form-group mt-3">
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
  };

  // get message(){
  //   return this.$store.state.message;
  // }

  get thread() {
    return this.$store.state.thread;
  }

  async created() {
    if (!this.thread) {
      let getThread = await fetch(
        `http://localhost:3000/api/v1/threads/${this.$route.params.id}`
      );
      const thread = await getThread.json();
      this.$store.commit("setThread", thread);
      console.log(thread, "TREAD");
    }
  }

  async createMessage() {
    console.log("Inne i createMessage funktionen");
    let id = this.$route.params.id;

    let newMessage = await fetch(
      `http://localhost:3000/api/v1/threads/${id}/messages`,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.message),
        credentials: "include",
      }
    );
    newMessage = await newMessage.json();
    console.log(newMessage, "new Message");
    this.$store.commit("createNewMessage", newMessage);
    this.message.createdMessage = null;
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
