<template>
  <div class="message mt-3">
    <div class="card container">
      <div class="card-body">
        <blockquote class="blockquote mb-0 message">
          <p :class="{ isWarning: message.warningMessage }">
            {{ message.message }}
          </p>
          <footer class="blockquote-footer message">
            {{ message.messageOwner.username }} replies:
          </footer>
        </blockquote>
      </div>
      <div v-if="admin || moderator" @click="deleteMessage" class="del">
        Delete
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component()
class Message extends Vue {
  @Prop({
    required: true,
  })
  message;
  @Prop({
    required: true,
  })
  forum_id;

  get user() {
    return this.$store.state.loggedInUser;
  }

  // get forums() {
  //   return this.$store.state.forums;
  // }

  get admin() {
    return this.user ? this.user.roles.includes("ADMIN") : false;
  }

  get moderator() {
    return this.user?.moderatedForums.includes(this.forum_id);
  }

  async deleteMessage() {
    let response = await fetch(`/api/v1/message/${this.message.message_id}`, {
      method: "DELETE",
    });
    if (response.status === 204) {
      await this.$store.dispatch("fetchThread", this.$route.params.id);
    }
  }
}

export default Message;
</script>

<style scoped>
.message {
  font-size: 13px;
}

.isWarning {
  color: red;
}

.del:hover {
  color: rgb(230, 62, 62);
  cursor: pointer;
  font-weight: bolder;
}
</style>
