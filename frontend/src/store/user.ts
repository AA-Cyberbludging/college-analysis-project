import { defineStore } from 'pinia'

export const userStore = defineStore('user', {
  state: () => {
    return {
      userId: {} as number,
      userName: {} as string,
      isStudent: {} as boolean,
      userScore: {} as number,
      pname: {} as string,
      subject: {} as string,
    }
  },
  persist: true
})

export const adminStore = defineStore('admin', {
  state: () => {
    return {
      id: {} as number,
      name: {} as string
    }
  },
  persist: true
})