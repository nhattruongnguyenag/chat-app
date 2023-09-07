import React, { useEffect } from 'react'
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
  data: User | null
  messageSections: MessageSection[] | null
}

export default function Conversation({ data, messageSections }: ConversationProps) {
  const messagesEndRef = React.createRef<HTMLDivElement>()

  useEffect(() => {
    if (messagesEndRef) {
      messagesEndRef.current?.scrollIntoView({ behavior: 'auto' })
    }
  }, [messageSections, messagesEndRef])

  return (
    <div className='md:flex md:flex-col md:flex-auto h-screen md:p-6'>
      <div className='flex flex-col flex-auto flex-shrink-0 rounded-2xl bg-gray-100 h-full'>
        <div className='flex flex-col h-full overflow-x-auto mb-4'>
          <ChatHeader data={data} />
          <div className='flex flex-col h-full  p-4'>
            <div className='grid grid-cols-12 gap-y-2'>
              {messageSections?.map((item, index) => {
                return <MessageSectionItem key={index} data={item} />
              })}
              <div ref={messagesEndRef} />
            </div>
          </div>
        </div>
        <MessageInputBar />
      </div>
    </div>
  )
}
