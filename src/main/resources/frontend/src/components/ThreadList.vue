<template>
  <div class="forums container row mx-auto">
    <ThreadListItem />
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import ThreadListItem from "../components/ThreadListItem";

@Component({
  components: {
    ThreadListItem,
  },
})
class ThreadList extends Vue {
  get threads() {
    return this.$store.state.threads;
  }
}

export default ThreadList;
</script>

<style scoped></style>
