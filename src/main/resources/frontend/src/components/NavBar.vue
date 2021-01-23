<template>
  <div class="navbar">
    <h1 class="point" @click="goTo('/')">GamingForum</h1>
    <div
      class="point"
      v-if="user ? user.roles.includes('ADMIN') : false"
      @click="goTo('/admin')"
    >
      Admin
    </div>
    <div class="point" v-if="!user" @click="goTo('/login')">Login</div>
    <div class="point" v-if="user" @click="logoutUser">Logout</div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component({
  components: {},
})
class NavBar extends Vue {
  // Generic goto route method
  goTo(route) {
    if (this.$route.path != route) this.$router.push({ path: route });
  }

  get user() {
    return this.$store.state.loggedInUser;
  }

  async logoutUser() {
    fetch("/auth/logout", {
      method: "GET",
    })
      .then((response) => {
        if (response.ok) {
          this.$store.commit("setLoggedInUser", null);
          if (this.$route.path === "/") return;
          this.$router.push("/");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }
}
export default NavBar;
</script>

<style scoped>
.navbar {
  align-items: center;
  background: black;
  height: 4em;
}
.h1 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.point {
  cursor: pointer;
  color: rgba(1, 146, 1, 0.788);
}
</style>
