import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import SockJS from 'sockjs-client'
import { Client, Frame, Message, over } from 'stompjs'
import Conversation from '../components/Conversation'
import { getPrinciple, SERVER_ADDRESS } from '../constants/SystemConstant'
import { MessageModel } from '../types/MessageModel'
import { MessageSection } from '../types/MessageSection'
import { User } from '../types/User'

let stompClient: Client

const convertToMessageSections = (messages: MessageModel[]) => {
  let messageSections: MessageSection[] = []
  if (messages) {
    let index = -1
    messages.forEach((message) => {
      if (messageSections.length === 0) {
        messageSections.push({
          title: message.createdAt,
          payload: [message]
        })
        index++
      } else {
        if (new Date(message.createdAt).toDateString() === new Date(messageSections[index].title).toDateString()) {
          messageSections[index].payload.push(message)
        } else {
          messageSections.push({
            title: message.createdAt,
            payload: [message]
          })
          index++
        }
      }
    })
  }

  return messageSections
}

function ChatPage() {
  const navigation = useNavigate()
  const { username } = useParams()
  const [receiver, setReceiver] = useState<User | null>(null)
  const [messageSections, setMessageSections] = useState<MessageSection[] | null>(null)

  useEffect(() => {
    let url = SERVER_ADDRESS + '/api/users/' + username
    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        const user = data as User
        setReceiver(user)
      })
      .catch((error) => console.error('Error:', error))
  }, [username])

  useEffect(() => {
    const connect = () => {
      let Sock = new SockJS(SERVER_ADDRESS + '/chat-ws')
      stompClient = over(Sock)
      stompClient.connect({}, onConnected, onError)
    }

    const onConnected = () => {
      stompClient.subscribe(`/topic/messages/${getPrinciple()?.id}/${receiver?.id}`, onMessageReceived)
      stompClient.send(`/app/messages/${getPrinciple()?.id}/${receiver?.id}/listen`)
    }

    const onMessageReceived = (payload: Message) => {
      const messages = JSON.parse(payload.body) as MessageModel[]
      setMessageSections(convertToMessageSections(messages))
    }

    const onError = (err: string | Frame) => {
      console.log(err)
    }

    if (receiver) {
      connect()
    }
  }, [receiver])

  return (
    <div className='container mx-auto'>
      <div className='flex h-screen antialiased text-gray-800'>
        <div className='flex flex-col md:flex-row h-full w-full'>
          <Conversation
            stompClient={stompClient}
            messageSections={messageSections}
            receiver={receiver} />
        </div>
      </div>
    </div>
  )
}

export default ChatPage
