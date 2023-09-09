import { useRef } from 'react'
import { NavLink, useNavigate } from 'react-router-dom'
import { SERVER_ADDRESS } from '../constants/SystemConstant'
import { User } from '../types/User'

export default function RegisterPage() {
  const fullNameInput = useRef<HTMLInputElement | null>(null)
  const usernameInput = useRef<HTMLInputElement | null>(null)
  const passwordInput = useRef<HTMLInputElement | null>(null)
  const prePasswordInput = useRef<HTMLInputElement | null>(null)
  const navigate = useNavigate()

  const register = () => {
    if (passwordInput.current?.value !== prePasswordInput.current?.value) {
      alert('Mật khẩu không khớp')
      return
    }

    if (fullNameInput.current && fullNameInput.current.value.length === 0) {
      alert('Họ tên không được để trống')
      return
    }

    if (usernameInput.current && usernameInput.current.value.length === 0) {
      alert('Tên đăng nhập bắt buộc phải điền')
      return
    }

    if (passwordInput.current && passwordInput.current.value.length < 5) {
      alert('Mật khẩu từ 6 ký tự trở lên')
      return
    }

    const url = SERVER_ADDRESS + '/api/users'

    const data = {
      fullName: fullNameInput.current?.value,
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
      .then((response) => response.json())
      .then((data) => {
        localStorage.setItem('token', JSON.stringify(data))
        getUserFromToken(data.token)
      })
      .catch((error) => console.error('Error:', error))
  }

  const getUserFromToken = (token: string) => {
    const url = SERVER_ADDRESS + '/api/users/token/' + token

    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        const user = data as User
        localStorage.setItem('principle', JSON.stringify(user))
        navigate('/dashboard')
      })
      .catch((error) => console.error('Error:', error))
  }

  return (
    <div className='flex h-screen justify-center items-center'>
      <div>
        <h1 className='text-center text-[30px] pb-7'>Đăng ký</h1>
        <form className='w-full max-w-sm'>
          <div className='md:flex md:items-center mb-6'>
            <div className='md:w-1/3'>
              <label
                className='block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4'
                htmlFor='inline-full-name'
              >
                Họ tên
              </label>
            </div>
            <div className='md:w-2/3'>
              <input
                className='bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500'
                id='inline-full-name'
                type='text'
                placeholder='Nhập họ tên'
                ref={fullNameInput}
              />
            </div>
          </div>
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
                className='bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500'
                id='inline-user-name'
                type='text'
                placeholder='Nhập tên đăng nhập'
                ref={usernameInput}
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
                placeholder='Nhập mật khẩu'
                ref={passwordInput}
              />
            </div>
          </div>

          <div className='md:flex md:items-center mb-6'>
            <div className='md:w-1/3'>
              <label
                className='block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4'
                htmlFor='inline-password'
              >
                Nhập lại mật khẩu
              </label>
            </div>

            <div className='md:w-2/3'>
              <input
                className='bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500'
                id='inline-pre-password'
                type='password'
                placeholder='Nhập lại mật khẩu'
                ref={prePasswordInput}
              />
            </div>
          </div>

          <div className='md:flex md:items-center mb-6'>
            <div className='md:w-1/3' />
            Đã có tài khoản?
            <NavLink to='/' className='text-blue-500'>
              &nbsp;Đăng nhập
            </NavLink>
          </div>
          <div className='md:flex md:items-center text-center md:text-left'>
            <div className='md:w-1/3' />
            <div className='md:w-2/3'>
              <button
                onClick={register}
                className='shadow bg-purple-500 hover:bg-purple-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded'
                type='button'
              >
                Đăng ký
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  )
}
