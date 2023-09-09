import { User } from '../types/User'
import UserItem from './UserItem'

interface ChatHeaderProps {
  data: User | null
}

export default function ChatHeader({ data }: ChatHeaderProps) {
  return (
    <div className='fixed bg-blue-500 ps-2 left-0 right-0 md:rounded-tl-xl md:rounded-tr-xl'>
      <div className='flex flex-row items-center rounded-xl p-2'>
        <div className='flex items-center justify-center h-14 w-14 bg-indigo-200 rounded-full'>{data?.fullName[0]}</div>
        <div className='ml-2 text-lg font-semibold text-white'>{data?.fullName}</div>
      </div>
    </div>
  )
}
