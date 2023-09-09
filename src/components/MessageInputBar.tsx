import React, { forwardRef, LegacyRef } from "react"

interface MessageInputBarProps {
  onSendMessage?: () => void
}

type InputProps = React.HTMLProps<HTMLInputElement>

const MessageInputBar = React.forwardRef<HTMLInputElement, MessageInputBarProps>(({ onSendMessage }, forwardRef) => (
  <div className='fixed z-50 bottom-0 flex flex-row items-center h-16 rounded-xl bg-white w-full px-4'>
    <div>
      <button className='flex items-center justify-center text-gray-400 hover:text-gray-600'>
        <svg
          className='w-5 h-5'
          fill='none'
          stroke='currentColor'
          viewBox='0 0 24 24'
          xmlns='http://www.w3.org/2000/svg'
        >
          <path
            strokeLinecap='round'
            strokeLinejoin='round'
            strokeWidth={2}
            d='M15.172 7l-6.586 6.586a2 2 0 102.828 2.828l6.414-6.586a4 4 0 00-5.656-5.656l-6.415 6.585a6 6 0 108.486 8.486L20.5 13'
          />
        </svg>
      </button>
    </div>
    <div className='flex-grow ml-4'>
      <div className='relative w-full'>
        <input
          ref={forwardRef}
          type='text'
          className='pr-10 flex w-full border rounded-xl focus:outline-none focus:border-indigo-300 pl-4 h-10'
        />
        <button className='absolute flex items-center justify-center h-full w-12 right-0 top-0 text-gray-400 hover:text-gray-600'>
          <svg
            className='w-6 h-6'
            fill='none'
            stroke='currentColor'
            viewBox='0 0 24 24'
            xmlns='http://www.w3.org/2000/svg'
          >
            <path
              strokeLinecap='round'
              strokeLinejoin='round'
              strokeWidth={2}
              d='M14.828 14.828a4 4 0 01-5.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z'
            />
          </svg>
        </button>
      </div>
    </div>
    <div className='ml-4'>
      <button onClick={onSendMessage} className='flex items-center justify-center bg-indigo-500 hover:bg-indigo-600 rounded-xl text-white px-4 py-1 flex-shrink-0'>
        Send
      </button>
    </div>
  </div>
)
)

export default MessageInputBar
