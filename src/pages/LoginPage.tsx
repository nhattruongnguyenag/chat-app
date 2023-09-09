import { useEffect, useRef } from 'react'
import { NavLink, useNavigate } from 'react-router-dom'
import SockJS from 'sockjs-client'
import { Client, Message, over } from 'stompjs'
import { SERVER_ADDRESS } from '../constants/SystemConstant'
import { User } from '../types/User'

let stompClient: Client

export default function LoginPage() {
  const usernameInput = useRef<HTMLInputElement | null>(null)
  const passwordInput = useRef<HTMLInputElement | null>(null)
  const navigate = useNavigate()

  const login = () => {
    const url = SERVER_ADDRESS + '/api/login'
    const data = {
      username: usernameInput.current?.value,
      password: passwordInput.current?.value
    }

    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
      .then((response) => {
          return response.json()
      })
      .then((data) => {
        localStorage.setItem('token', JSON.stringify(data))
        getUserFromToken(data.token)
      })
      .catch((error) => {
        alert("Tên đăng nhập hoặc mật khẩu không đúng")
        console.error('Error:', error)
      })
  }

  const getUserFromToken = (token: string) => {
    const url = SERVER_ADDRESS + '/api/users/token/' + token

    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        const user = data as User
        localStorage.setItem('principle', JSON.stringify(user))
        navigate('dashboard')
      })
      .catch((error) => console.error('Error:', error))
  }

  return (
    <div className='flex h-screen justify-center items-center'>
      <div>
        <h1 className='text-center text-[30px] pb-7'>Đăng nhập</h1>
        <form className='w-full max-w-sm'>
          <div className='md:flex md:items-center mb-6'>
            <div className='md:w-1/3'>
              <label
                className='block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4'
                htmlFor='inline-full-name'
              >
                Tên đăng nhập
              </label>
            </div>
            <div className='md:w-2/3'>
              <input
                ref={usernameInput}
                className='bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500'
                id='inline-full-name'
                type='text'
                placeholder='Nhập tên đăng nhập'
              />
            </div>
          </div>
          <div className='md:flex md:items-center mb-6'>
            <div className='md:w-1/3'>
              <label
                className='block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4'
                htmlFor='inline-password'
              >
                Mật khẩu
              </label>
            </div>
            <div className='md:w-2/3'>
              <input
                className='bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500'
                id='inline-password'
                type='password'
                placeholder='Nhập mật khẩu của bạn'
                ref={passwordInput}
              />
            </div>
          </div>
          <div className='md:flex md:items-center mb-6'>
            <div className='md:w-1/3' />
            Chưa có tài khoản?
            <NavLink to='/register' className='text-blue-500'>
              &nbsp;Đăng ký
            </NavLink>
          </div>
          <div className='md:flex md:items-center text-center md:text-left'>
            <div className='md:w-1/3' />
            <div className='md:w-2/3'>
              <button
                onClick={login}
                className='shadow bg-purple-500 hover:bg-purple-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded'
                type='button'
              >
                Đăng nhập
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  )
}
