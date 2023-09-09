import React from 'react'
import { toTime } from '../constants/SystemConstant'
import { MessageModel } from '../types/MessageModel'
import { MessageItemProps } from './ReceivedMessageItem'

export default function SentMessageItem({ data, showSentTime }: MessageItemProps) {
  return (
    <div className='col-start-1 md:col-start-6 col-end-13 rounded-lg p-0'>
      <div className='flex items-center justify-start flex-row-reverse'>
        <div className='flex flex-col'>
          <div className='flex flex-row'>
            <div className='mr-3 bg-indigo-100 py-2 px-4 shadow rounded-xl'>
              <div className='text-[16px]'>{data.content}</div>
            </div>
            <div className='flex items-center justify-center h-10 w-10 rounded-full bg-purple-500 flex-shrink-0'>{data.sender.fullName[0]}</div>
          </div>
          {
            showSentTime && <div className='mb-10'>
              <div className='text-xs bottom-0 right-0 mr-[70px] text-gray-500 text-right mt-2'>
                {toTime(new Date(data.createdAt))}
              </div>
            </div>
          }
        </div>
      </div>
    </div>
  )
}
