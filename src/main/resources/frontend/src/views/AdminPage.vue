<template>
  <div class="admin-page">
    <div class="admin mb-3">{{ user.username }}</div>

    <UserListItem v-for="user in users" :key="user.user_id" :user="user" />
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

import UserListItem from "../components/UserListItem";

@Component({
  components: { UserListItem },
})
class AdminPage extends Vue {
  get users() {
    return this.$store.state.user;
  }

  get user() {
    return this.$store.state.loggedInUser;
  }

  async created() {
    if (!this.user) {
      await this.$store.dispatch("whoami");
      if (this.user || this.user.roles.indcludes("ADMIN")) {
        this.$router.push({ path: "/" });
      }
    }
    this.$store.dispatch("fetchAllUsers");
  }

}

export default AdminPage;
</script>

<style scoped>

.admin{
    font-size: 24px;
    font-weight: bold;
}
</style>
