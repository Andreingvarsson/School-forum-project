<template>
  <div class="thread ">
    <div class="card mt-3 container">
      <div @click="navigate" class="card-body row">
        <h1 class="card-title title-text col-6">{{ thread.title }}</h1>
        <p class="card-text message-text col-7">{{ thread.threadMessage }}</p>
        <!-- <span class="col-6 size">{{ thread.timestamp }}</span> -->
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

  navigate() {
    this.$store.commit("setThread", this.thread);
    this.$router.push({ path: `/forum/thread/${this.thread.thread_id}` });
  }
}

export default ThreadListItem;
</script>

<style scoped>
.card-body:hover {
  border-style: solid;
  border-width: 1px;
  border-color: rgba(0, 0, 0, 0.61);
  cursor: pointer;
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
</style>
