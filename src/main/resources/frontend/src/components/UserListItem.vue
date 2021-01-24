<template>
  <div>
    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">{{ user.username }}</h5>
        <p class="card-text">
          {{ user.email }}
        </p>
        <span class="card-text">Role: {{ user.roles }}</span>
      </div>

      <div v-if="!user.roles.includes('ADMIN')" class="col">
        <div class="row">
          <div class="input-group mb-3 col-xs-12 col-sm-12 col-md-6 col-xl-6">
            <div class=" text-left  col-12 mb-3">
              Add moderator to a forum
            </div>

            <div class="input-group-prepend">
              <button class="btn btn-success" type="button">
                Add
              </button>
            </div>
            <select class="custom-select" id="addModerator">
              <option selected>choose a forum...</option>
              <option
                :class="forum.name"
                :value="forum.forum_id"
                :key="forum.forum_id"
                v-for="forum in forums"
                >{{ forum.name }}</option
              >
            </select>
          </div>

          <div class="input-group mb-3 col-xs-12 col-sm-12 col-md-6 col-xl-6">
            <div class=" text-left font-bold col-12 mb-3">
              Remove moderator from a forum
            </div>
            <div class="input-group-prepend">
              <button class="btn btn-success" type="button">
                Remove
              </button>
            </div>
            <select class="custom-select" id="removeModerator">
              <option selected>choose a forum...</option>
              <option
                :class="forum.name"
                :value="forum.forum_id"
                :key="forum.forum_id"
                v-for="forum in forums"
                >{{ forum.name }}</option
              >
            </select>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component()
class UserListItem extends Vue {
  @Prop({
    required: true,
  })
  user;

  get forums() {
    return this.$store.state.forums;
  }

  // HAVE TO FIX DELETE ISSUE X-TABLE WITH MESSAGES, AND THREADS SINCE THEY LOOK FOR USER_ID ASWELL THE PROGRAM STOPS WORKING
  //   async deleteUser() {
  //     console.log("Inne i deleteUser");
  //     await fetch(`/api/v1/users/${this.user.user_id}`, {
  //       method: "DELETE",
  //       credentials: "include",
  //     });
  //   }
}

export default UserListItem;
</script>

<style scoped>
.card-text {
  font-size: 15px;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
</style>
