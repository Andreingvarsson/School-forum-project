<template>
  <div>
    <div class="card">
      <div class="card-body ">
        <h5 class="card-title">{{ user.username }}</h5>
        <p class="card-text">
          {{ user.email }}
        </p>
        <span class="card-text">Role: {{ user.roles }}</span>
      </div>
      <div>
        <svg
          @click="deleteUser"
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="20"
          fill="currentColor"
          class="bi bi-trash"
          viewBox="0 0 16 16"
        >
          <path
            d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
          />
          <path
            fill-rule="evenodd"
            d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
          />
        </svg>
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
