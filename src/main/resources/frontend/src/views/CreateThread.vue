<template>
  <div v-if="user" class="thread container">
    <form @submit.prevent="createThread" class="mx-auto">
      <div class="form-group font mt-3">
        <label for="title">Thread title</label>
        <input
          type="title"
          required
          v-model="thread.threadTitle"
          class="form-control mb-2"
          id="title"
          placeholder="Title.."
        />

        <label for="title">Thread message</label>
        <input
          type="message"
          required
          v-model="thread.threadMessage"
          class="form-control"
          id="message"
          placeholder="message.."
        />
        <button class="mt-2 button" type="submit">Create thread</button>
      </div>
    </form>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component
class CreateThread extends Vue {
  thread = {
    threadTitle: null,
    threadMessage: null,
  };

  get user() {
    return this.$store.state.loggedInUser;
  }

  async createThread() {
    let id = this.$route.params.id;

    let newThread = await fetch(`/api/v1/forums/${id}/threads`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(this.thread),
      credentials: "include",
    });
    newThread = await newThread.json();
    this.$store.commit("createNewThread", newThread);
    this.thread.threadTitle = null;
    this.thread.threadMessage = null;
  }
}
export default CreateThread;
</script>

<style>
.font {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: bold;
}

.button {
  border-radius: 10%;
  border-color: rgba(1, 146, 1, 0.788);
}
</style>
