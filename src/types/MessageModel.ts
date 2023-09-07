import { User } from "./User"

export interface MessageModel {
  id: number
  content: string
  type: string
  sender: Omit<User, 'id|username|status|updatedAt'>
  receiverId: number
  createdAt: Date
  updatedAt: Date
}
