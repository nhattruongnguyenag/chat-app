import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import SockJS from 'sockjs-client'
import { Client, Frame, Message, over } from 'stompjs'
import AppHeader from '../components/AppHeader'
import Conversation from '../components/Conversation'
import UserItem from '../components/UserItem'
import UserLoginInfo from '../components/UserLoginInfo'
import { getPrinciple } from '../constants/SystemConstant'
import { User } from '../types/User'

let stompClient: Client
function DashboardPage() {
  const navigation = useNavigate()
  const [users, setUsers] = useState<User[]>()

  useEffect(() => {
    const connect = () => {
      let Sock = new SockJS('http://localhost:8080/chat-ws')
      stompClient = over(Sock)
      stompClient.connect({}, onConnected, onError)
    }

    const onConnected = () => {
      stompClient.subscribe('/topic/users', onMessageReceived)
      const data = {
        id: getPrinciple()?.id,
        status: 1
      }
      stompClient.send('/app/users/status', {}, JSON.stringify(data))
    }

    const onMessageReceived = (payload: Message) => {
      let tempUsers = JSON.parse(payload.body) as User[]
      setUsers(tempUsers.filter((user) => user.id !== getPrinciple()?.id))
    }

    const onError = (err: string | Frame) => {
      console.log(err)
    }

    connect()
  }, [])

  const logout = () => {
    if (!confirm('Đăng xuất khỏi tài khoản của bạn ?')) {
      return
    }
    const data = {
      id: getPrinciple()?.id,
      status: 0
    }
    stompClient.send('/app/users/status', {}, JSON.stringify(data))
    navigation('/')
  }

  const navigateToChatPage = (username: string) => {
    navigation('/chat/' + username)
  }

  return (
    <div className='p-5 mx-auto'>
      <div className='flex h-screen antialiased text-gray-800'>
        <div className='flex flex-col md:flex-row h-full w-full overflow-x-auto'>
          <div className='flex flex-col py-8 pl-6 pr-2 w-full md:w-[550px] mx-auto bg-slate-50 flex-shrink-0'>
            <AppHeader />
            <UserLoginInfo data={getPrinciple()} />
            <div className='flex flex-col mt-8'>
              <div className='flex flex-row items-center justify-between text-xs'>
                <span className='font-bold'>Danh sách bạn bè</span>
                <span className='flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full'>
                  {users?.length}
                </span>
              </div>
              <div className='flex flex-col space-y-1 mt-4 -mx-2 h-96 scroll-m-0 overflow-y-auto'>
                {users?.map((user, index) => (
                  <UserItem onClick={() => navigateToChatPage(user.username)} key={index} data={user} />
                ))}
              </div>
            </div>
            <button onClick={logout} className='bg-red-500 text-white p-2 rounded-sm'>
              Đăng xuất
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DashboardPage
