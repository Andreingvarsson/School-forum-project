<template>
  <div class="account-background">
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Username</label>
        <input
          type="text"
          required
          class="form-control"
          id="username"
          placeholder="Username.."
        />
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          required
          class="form-control"
          id="password"
          placeholder="Password.."
        />
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <router-link to="/register" class="register-account"
      >Don't have an account?</router-link
    >
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component
class Login extends Vue {
  login(e) {
    let credentialsToLogin = {
      username: e.target.username.value,
      password: e.target.username.value,
    };
    this.loginAttempt(credentialsToLogin);
  }

  async loginAttempt(userToLogin) {
    let credentials =
      "username=" +
      encodeURIComponent(userToLogin.username) +
      "&password=" +
      encodeURIComponent(userToLogin.password);

    let response = await fetch("/auth/login", {
      method: "POST",
      headers: {
        "Content-type": "application/x-www-form-urlencoded",
      },
      body: credentials,
    });
    if (response.url.includes("error")) {
      console.log("ERROR: Login attempt failed..");
    } else {
      console.log("SUCESS: Login attempt succeded..");
      await this.$store.dispatch("whoami");
      this.$router.push("/");
    }
  }
}

export default Login;
</script>

<style scoped>
.btn {
  margin-bottom: 15px;
}

.register-account:hover {
  font-weight: bold;
  cursor: pointer;
  text-decoration: none;
}
</style>
