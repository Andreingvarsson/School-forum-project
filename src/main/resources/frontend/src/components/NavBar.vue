<template>
  <div class="navbar">
    <h1 class="point" @click="goHome">GamingForum</h1>
    <div  class="point" v-if="user ? user.roles.includes('ADMIN') : false" @click="goToAdmin">Admin</div>
    <router-link v-if="!user" to="/login">Login</router-link>
    <div class="point" v-if="user" @click="logoutUser">Logout</div>
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";


@Component({
  components: {}
})
class NavBar extends Vue {

  // Generic goto route method
  goTo(route){
    if(this.$route.path != route) this.$router.push({path: route})
  }

  goHome() {
    this.$router.push({ path: "/" });
  }

  goToAdmin(){
    if(this.user.roles.includes("ADMIN")){
      this.$router.push({path: "/admin"})
    }
  
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

.point{
  cursor: pointer;
}

</style>
