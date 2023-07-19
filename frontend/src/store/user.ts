import { defineStore } from 'pinia'

export const userStore = defineStore('user', {
  state: () => {
    return {
      userId: {} as number,
      userName: {} as string,
      isStudent: {} as boolean,
      userScore: {} as number,
      userRank: {} as number,
      pname: {} as string,
      subject: {} as string,
    }
  },
  persist: true
})