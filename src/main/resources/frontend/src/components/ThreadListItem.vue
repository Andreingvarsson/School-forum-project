<template>
  <div class="thread ">
    <div class="card mt-3 container">
      <div
        @click="navigate"
        class="card-body row"
        :class="{
          isCardLocked: thread.lockedThread,
        }"
      >
        <h1 class="card-title title-text col-5">{{ thread.title }}</h1>
        <p class="card-text message-text col-7">{{ thread.threadMessage }}</p>
        <!-- <span class="col-6 size">{{ thread.timestamp }}</span> -->

        <div
          class="card-footer"
          v-if="user ? user.roles.includes('ADMIN') : false"
         
        >
          <svg
             @click.stop="lockThread"
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-lock point "
            viewBox="0 0 16 16"
            :class="{
              isLocked: thread.lockedThread,
              isOpen: !thread.lockedThread,
            }"
          >
            <path
              d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"
            />
          </svg>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component()
class ThreadListItem extends Vue {
  @Prop({
    required: true,
  })
  thread;

  get user() {
    return this.$store.state.loggedInUser;
  }

  navigate() {
    this.$store.commit("setThread", this.thread);
    this.$router.push({ path: `/forum/thread/${this.thread.thread_id}` });
  }

  async lockThread() {
    console.log("Inne i lockThread");
    let response = await fetch(
      `/api/v1/forums/${this.thread.thread_id}/threads`,
      {
        method: "PUT",
        credentials: "include",
      }
    );
    console.log(response);
    console.log(this.thread.forum_id);
    if (response.status === 200) {
      this.$store.dispatch("fetchForumById", this.thread.forum_id);
    }
  }

}

export default ThreadListItem;
</script>

<style scoped>
.card-body:hover {
  border-style: solid;
  border-width: 1px;
  border-color: rgba(0, 0, 0, 0.61);
}

.title-text {
  font-size: 20px;
  font-weight: bold;
  display: flex;
  justify-content: flex-start;
}

.message-text {
  display: flex;
  justify-content: flex-start;
}

.size {
  font-size: 13px;
  display: flex;
  justify-content: flex-start;
}

.isLocked {
  color: red;
}

.isOpen {
  color: green;
  cursor: pointer;
}

.isCardLocked{
  color: red;
  border-color: red;
  border-style: solid;
  border-width: 1px;
}

.point{
  cursor: pointer;
}
</style>
