<template>
  <div v-if="forum" class="forum">
    <div class="forum-title">{{ forum.name }}</div>
    <CreateThread />
    <ThreadListItem
      v-for="thread in forum.threads"
      :key="thread.thread_id"
      :thread="thread"
    />
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import CreateThread from "../views/CreateThread";
import ThreadListItem from "../components/ThreadListItem";

@Component({
  components: { CreateThread, ThreadListItem },
})
class Forum extends Vue {
  get forum() {
    return this.$store.state.forum;
  }

  async created() {
    this.$store.dispatch("fetchForumById", this.$route.params.id);
  }
}

export default Forum;
</script>

<style scoped>
.forum-title {
  font-size: 2em;
}
</style>
