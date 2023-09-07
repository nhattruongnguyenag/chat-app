import { User } from '../types/User'

interface UserItemProps {
  data: User
  onClick: () => void
}

export default function UserItem({ data, onClick }: UserItemProps) {
  return (
    <button onClick={onClick} className='flex flex-row items-center hover:bg-gray-100 rounded-xl p-2'>
      <div className='flex items-center justify-center h-8 w-8 bg-indigo-200 rounded-full'>{data.fullName[0]}</div>
      <div className='ml-2 text-sm font-semibold'>{data.fullName}</div>
      {data.status ? <div className='ms-auto mr-4 w-[10px] h-[10px] bg-lime-500 rounded-full'></div> : <></>}
    </button>
  )
}
