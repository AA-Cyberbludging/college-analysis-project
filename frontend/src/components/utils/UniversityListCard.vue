<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  props: {
    uname: String,
    utype: String,
    pname: String,
    upopularity: Number
  },
  data() {
    return {}
  }
})
</script>

<template>
  <div class="card-container">
    <h3> {{ uname }} </h3>
    <div class="popularity">🔥{{ upopularity }}</div>
    <span>{{ utype }}</span> <span>{{ pname }}</span>
    <hr>
  </div>
</template>

<style scoped>
.card-container {
  transition: all 0.1s ease-in;
  padding-left: 20px;
  padding-top: 5px;
}

h3 {
  width: 150px;
}

.popularity {
  color: orange
}

.card-container:hover {
  background-color: rgb(128, 128, 128, 0.25);
}

.card-container:active {
  background-color: rgb(128, 128, 128, 0.5);
}

span {
  background-color: rgb(53, 141, 241);
  color: white;
  border-radius: 4px;
  padding: 5px;
  font-size: 90%;
}
</style>
