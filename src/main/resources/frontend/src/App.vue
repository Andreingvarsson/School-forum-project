<template>
  <div id="app">
    <NavBar />
    <ForumList />
    <router-view />
  </div>
</template>
<script>
import { Vue, Component } from "vue-property-decorator";

import NavBar from "../src/components/NavBar";
import ForumList from "../src/components/ForumList";
@Component({
  components: {
    NavBar,
    ForumList,
  },
})
class App extends Vue {
  created() {
    this.$store.dispatch("fetchAllForums");
  }
}

export default App;
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
