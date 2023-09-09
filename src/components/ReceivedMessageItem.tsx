import { toTime } from '../constants/SystemConstant'
import { MessageModel } from '../types/MessageModel'

export interface MessageItemProps {
  data: MessageModel
  showSentTime: boolean
}

export default function ReceivedMessageItem({ data, showSentTime }: MessageItemProps) {
  return (
    <div className='col-start-1 md:col-end-8 col-end-13 rounded-lg'>
      <div className='flex flex-row items-center'>
        <div className='flex flex-col'>
          <div className='flex flex-row'>
            <div className='flex items-center justify-center h-10 w-10 rounded-full bg-indigo-200 flex-shrink-0'>{data.sender.fullName[0]}</div>
            <div className='ml-3 text-sm bg-white py-2 px-4 shadow rounded-xl'>
              <div className='text-sm'>{data.content}</div>
            </div>
          </div>
          {
            showSentTime && <div className='mb-10'>
              <div className='text-xs bottom-0 ms-[70px] mt-2 text-gray-500'>
                {toTime(new Date(data.createdAt))}
              </div>
            </div>
          }
        </div>
      </div>
    </div>
  )
}
