import { User } from '../types/User'

interface UserLoginInfoProps {
  data: User | null
}

export default function UserLoginInfo({ data }: UserLoginInfoProps) {
  return (
    <div className='flex flex-col items-center bg-indigo-100 border border-gray-200 mt-4 w-full py-6 px-4 rounded-lg'>
      <div className='text-xs text-gray-500 py-2'>Xin chào, {data?.fullName} !!!</div>
    </div>
  )
}
