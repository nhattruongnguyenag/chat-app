import React, { useCallback, useEffect, useRef } from 'react'
import { Client } from 'stompjs'
import { getPrinciple } from '../constants/SystemConstant'
import { MessageSection } from '../types/MessageSection'
import { User } from '../types/User'
import AudioMessageItem from './AudioMessageItem'
import ChatHeader from './ChatHeader'
import MessageInputBar from './MessageInputBar'
import MessageSectionItem from './MessageSectionItem'
import MessageSectionTitle from './MessageSectionTitle'
import ReceivedMessageItem from './ReceivedMessageItem'
import SentMessageItem from './SentMessageItem'
import UserItem from './UserItem'

interface ConversationProps {
  receiver: User | null
  messageSections: MessageSection[] | null
  stompClient: Client
}

export default function Conversation({ receiver, messageSections, stompClient }: ConversationProps) {
  const messagesEndRef = React.createRef<HTMLDivElement>()
  const messageContentInputRef = useRef<HTMLInputElement>(null)

  useEffect(() => {
    if (messagesEndRef) {
      messagesEndRef.current?.scrollIntoView({ behavior: 'auto' })
    }
  }, [messageSections, messagesEndRef])

  const onSendMessage = useCallback(() => {
    if (stompClient) {
      const content = messageContentInputRef.current?.value
      stompClient.send(`/app/messages/${getPrinciple()?.id}/${receiver?.id}`, {}, content)
    }
  }, [stompClient])

  return (
    <div className='md:flex md:flex-col md:flex-auto md:p-6'>
      <div className='flex flex-col flex-auto flex-shrink-0 bg-gray-100 h-full'>
        <div className='flex flex-col mb-4'>
          <ChatHeader data={receiver} />
          <div className='h-full mt-[50px] p-4'>
            <div className='flex flex-col overflow-y-auto'>
              {messageSections?.map((item, index) => {
                return <MessageSectionItem key={index} data={item} />
              })}
            </div>
          </div>
        </div>
        <div ref={messagesEndRef} />
        <MessageInputBar ref={messageContentInputRef} onSendMessage={onSendMessage} />
      </div>
    </div>
  )
}
