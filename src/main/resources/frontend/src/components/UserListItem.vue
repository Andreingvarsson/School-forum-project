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

      <div v-if="!admin" class="col">
        <div class="row">
          <div class="input-group mb-3 col-xs-12 col-sm-12 col-md-6 col-xl-6">
            <div class=" text-left  col-12 mb-3">
              Add moderator to a forum
            </div>

            <div class="input-group-prepend">
              <button class="btn btn-success" type="button" @click="addModerator(add)" > 
                Add
              </button>
            </div>
            <select class="custom-select" id="addModerator" v-on:change="setAdd($event.target.value)">
              <option selected>choose a forum...</option>
              <option
                :class="forum.name"
                :value="forum.forum_id"
                :key="forum.forum_id"
                v-for="forum in forumsAvailableForModeration"
                >{{ forum.name }}</option
              >
            </select>
          </div>

          <div class="input-group mb-3 col-xs-12 col-sm-12 col-md-6 col-xl-6">
            <div class=" text-left font-bold col-12 mb-3">
              Remove moderator from a forum
            </div>
            <div class="input-group-prepend">
              <button class="btn btn-success" type="button" @click="removeModerator(remove)"> 
                Remove
              </button>
            </div>
            <select class="custom-select" id="removeModerator" v-on:change="setRemove($event.target.value)">
              <option selected>choose a forum...</option>
              <option
                :class="forum.name"
                :value="forum.forum_id"
                :key="forum.forum_id"
                v-for="forum in forumsWithModeration"
                >{{ forum.name }}</option
              >
            </select>
          </div>
        </div>
        <div>delete</div>
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
  
  add = null;
  remove = null;


  get forums() {
    return this.$store.state.forums;
  }

  get admin(){
    return this.user ? this.user.roles.includes('ADMIN') : false;
  }

  get moderator(){
    return this.user?.moderatedForums.includes(this.forum_id)
  }

  
    async deleteUser() {
      console.log("Inne i deleteUser");
      await fetch(`/api/v1/users/${this.user.user_id}`, {
        method: "DELETE",
        credentials: "include",
      });
    }

get forumsAvailableForModeration(){
  return this.forums.filter(
    (forum) => !this.user.moderatedForums.includes(forum.forum_id)
  )
}

get forumsWithModeration(){
  return this.forums.filter(
    (forum) => this.user.moderatedForums.includes(forum.forum_id)
  )
}

setAdd(e){
  this.add = e;
}

setRemove(e){
  this.remove = e;
}

  // Finish updates on moderator!

  async addModerator(id) {
    console.log("Inne i addModerator", "ID = ", id)
    let response = await fetch(
    `/api/v1/forums/${id}/moderator/${this.user.user_id}`,
    { 
    method: "PUT", 
    credentials: "include" 
    }
    );
    if(response.status === 204){ 
      this.$store.dispatch("fetchAllUsers", this.$store.state.user)
    }
    console.log(response)
  }

    async removeModerator(id) {
      console.log("Inne i removeModerator")
    let response = await fetch(
    `/api/v1/forums/${id}/moderator/${this.user.user_id}`,
    { 
    method: "DELETE", 
    credentials: "include" 
    }
    );
    if(response.status === 204){
      this.$store.dispatch("fetchAllUsers", this.$store.state.user)
    
    }
    console.log(response)
  }

  created(){

  }
}

export default UserListItem;
</script>

<style scoped>
.card-text {
  font-size: 15px;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
</style>
