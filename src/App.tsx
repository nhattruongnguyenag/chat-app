import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ChatPage from './pages/ChatPage'
import DashboardPage from './pages/DashboardPage'
import LoginPage from './pages/LoginPage'
import NoPage from './pages/NoPage'
import RegisterPage from './pages/RegisterPage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route>
          <Route index element={<LoginPage />} />
          <Route path='dashboard' element={<DashboardPage />} />
          <Route path='chat/:username' element={<ChatPage />} />
          <Route path='register' element={<RegisterPage />} />
          <Route path='*' element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
