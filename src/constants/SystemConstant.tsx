import { log } from 'console'
import { MessageModel } from '../types/MessageModel'
import { User } from '../types/User'

export const SERVER_ADDRESS = 'http://103.69.193.140:8080'
// export const SERVER_ADDRESS = 'http://localhost:8080'

export const getPrinciple = (): User | null => {
    const json = localStorage.getItem('principle')
    if (json) {
        return JSON.parse(json) as User
    }

    return null
}

export const toTime = (date: Date) => {
    const hours = date.getHours()
    const minutes = date.getMinutes()
    const formattedTime = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}`
    return formattedTime
}

export const toTimeStamp = (utcDate: Date) => {
    const timestamp = utcDate.getTime()
    const timestampString = timestamp.toString()
    return timestampString
}

export const isDisplayTime = (messages: MessageModel[], index: number): boolean => {
    const length = messages.length;
    let nextMessageIndex = -1;
    for (let i = index + 1; i < length; i++) {
        if (messages[i].sender.id === messages[index].sender.id) {
            nextMessageIndex = i
            break;
        }
    }

    if (nextMessageIndex != -1) {
        const interval = (new Date(messages[nextMessageIndex].createdAt).getTime() - new Date(messages[index].createdAt).getTime()) / 1000
        console.log(interval)
        if(interval < 30) {
            return false
        }
    }

    return true
}
