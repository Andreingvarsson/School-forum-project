import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login";
import Register from "../views/Register";
import Forum from "../views/Forum";
import Thread from "../views/Thread";
import AdminPage from "../views/AdminPage";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/forum/:id",
    name: "Forum",
    component: Forum,
  },
  {
    path: "/forum/thread/:id",
    name: "Thread",
    component: Thread,
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminPage,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
