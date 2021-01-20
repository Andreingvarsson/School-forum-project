<template>
  <div class="forum-list mt-2 mb-4">
    <div @click="navigate" class="forum">{{ forum.name }}</div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component()
class ForumListItem extends Vue {
  @Prop({
    required: true,
  })
  forum;

  navigate() {
    this.$store.commit("setForum", this.forum);
    this.$router.push({ path: `/forum/${this.forum.forum_id}` });
  }
}

export default ForumListItem;
</script>

<style scoped>
.forum {
  color: Black;
  font: bold;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-size: 20px;
  cursor: pointer;
  padding: 5px 5px;
}

.forum:hover {
  font-weight: bolder;
}
</style>
