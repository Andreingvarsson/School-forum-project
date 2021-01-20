<template>
  <div class="forums container row mx-auto">
    <ForumListItem
      class="col"
      v-for="forum in forums"
      :key="forum.forum_id"
      :forum="forum"
    />
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";
import ForumListItem from "../components/ForumListItem";

@Component({
  components: {
    ForumListItem,
  },
})
class ForumList extends Vue {
  get forums() {
    return this.$store.state.forums;
  }
}

export default ForumList;
</script>

<style scoped></style>
