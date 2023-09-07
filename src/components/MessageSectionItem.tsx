import { getPrinciple, isDisplayTime } from '../constants/SystemConstant'
import { MessageSection } from '../types/MessageSection'
import MessageSectionTitle from './MessageSectionTitle'
import ReceivedMessageItem from './ReceivedMessageItem'
import SentMessageItem from './SentMessageItem'

interface MessageSectionItemProps {
  data: MessageSection
}

export default function MessageSectionItem({ data }: MessageSectionItemProps) {
  return (
    <>
      <MessageSectionTitle title={new Date(data.title).toDateString()} />
      {data.payload.map((message, index) => {
        if (message.sender.id === getPrinciple()?.id) {
          return <SentMessageItem key={index} data={message} showSentTime={isDisplayTime(data.payload, index)} />
        } else {
          return <ReceivedMessageItem key={index} data={message} showSentTime={isDisplayTime(data.payload, index)} />
        }
      })}
    </>
  )
}
