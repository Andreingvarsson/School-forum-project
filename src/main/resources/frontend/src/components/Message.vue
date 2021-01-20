<template>
  <div class="message mt-3">
    <div class="card container">
      <div class="card-body">
        <blockquote class="blockquote mb-0 message">
          <p>{{message.message}}</p>
          <footer class="blockquote-footer message">
            {{message.messageOwner.username}} replies:
          </footer>
        </blockquote>
      </div>
    </div>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component()
class Message extends Vue {
    @Prop({
    required: true,
  })
  message;

}

export default Message;
</script>

<style scoped>

.message {
  font-size: 13px;
}
</style>
