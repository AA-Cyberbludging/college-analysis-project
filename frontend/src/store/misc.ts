import { defineStore } from 'pinia'

export const miscStore = defineStore('misc', {
  state: () => {
    return {
      login: false,
      currentMenuIndex: 1
    }
  },
  persist: true
})