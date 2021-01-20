<template>
  <div class="account-background">
    <form @submit.prevent="registerForm">
      <div class="form-group">
        <label for="">Username</label>
        <input
          type="text"
          required
          class="form-control"
          id="username"
          v-model="user.username"
          placeholder="Username.."
        />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input
          type="email"
          required
          class="form-control"
          id="email"
          v-model="user.email"
          placeholder="Email.."
          pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+"
        />
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input
          type="password"
          required
          class="form-control"
          id="password"
          v-model="user.password"
          placeholder="Password.."
        />
      </div>
      <button type="submit" class="btn btn-primary">Register</button>
    </form>
    <router-link to="/login" class="login-account"
      >Already have an account?</router-link
    >
  </div>
</template>

<script>
import { Vue, Component } from "vue-property-decorator";

@Component
class Register extends Vue {
  user = {
    username: null,
    email: null,
    password: null,
    roles: "USER",
  };
  errorMessage = "";

  async registerForm() {
    await fetch("/api/v1/users", {
      method: "POST",
      headers: {
        "Content-Type": "Application/json",
      },
      body: JSON.stringify(this.user),
    })
      .then((response) => {
        if (response.ok) return response.json();
        else if (response.status == 400) {
          this.errorMessage = `${this.user.username} already exists`;
        }
      })
      .then((data) => {
        if (data) {
          this.$router.push("/login");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

export default Register;
</script>

<style>
.btn {
  margin-bottom: 15px;
}

.login-account:hover {
  font-weight: bold;
  cursor: pointer;
  text-decoration: none;
}

.account-background {
  background-color: rgba(128, 128, 128, 0.541);
  padding: 10px;
  margin: 0px 15px 0px 15px;
}
</style>
