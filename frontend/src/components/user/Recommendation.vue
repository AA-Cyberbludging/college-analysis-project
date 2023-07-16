<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  data() {
    return {}
  }
})
</script>

<template>
  
</template>

<style scoped>
.card {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 300px;
  width: 600px;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.left,
.right {
  flex: 1;
  height: 100%;
  position: relative;
}

.left:before,
.right:before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  width: 50%;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.left:before {
  right: 50%;
}

.right:before {
  left: 50%;
}

h2 {
  text-align: center;
}
</style>
