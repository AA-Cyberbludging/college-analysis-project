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
    <el-row>
      <el-col :span="15">
        <h3> {{ uname }} </h3>
      </el-col>
      <el-col :span="9">
        <div class="popularity">🔥{{ upopularity === null ? 0 : upopularity }}</div>
      </el-col>
    </el-row>
    
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
  font-size: 120%;
}

.popularity {
  color: rgb(186, 42, 23);
  padding-top: 20px;
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
  padding-left: 10px;
  padding-right: 10px;
  font-size: 90%;
}
</style>
