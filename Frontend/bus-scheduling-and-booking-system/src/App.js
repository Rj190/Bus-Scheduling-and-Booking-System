import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import LoginForm from './components/Login';
import RegisterUserForm from './components/RegisterUserFrom';
import Wallet from './components/Wallet';
import BusManagement from './components/bus/BusManagement';
import RouteManagement from './components/route/RouteManagement';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* <Route path='/wallet' element={<Wallet/>}/>
        <Route path='/' element={<RegisterUserForm/>} />
        <Route path='/login' element={<LoginForm/>} /> */}
        {/* <Route path='/' element={<BusManagement/>}/> */}
        <Route path='/' element={<RouteManagement/>}/>
      </Routes>

    </BrowserRouter>


  );
}

export default App;
