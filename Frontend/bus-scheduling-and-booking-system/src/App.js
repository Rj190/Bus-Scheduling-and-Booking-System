import { BrowserRouter, Route, Routes,useParams } from 'react-router-dom';
import './App.css';
import LoginForm from './components/Login';
import RegisterUserForm from './components/RegisterUserFrom';
import Wallet from './components/Wallet';
import BusManagement from './components/bus/BusManagement';
import RouteManagement from './components/route/RouteManagement';
import Seats from './components/seat/SelectSeat';
import HomeSearch from './components/home/HomeSearch';
import Journey from './components/Journey/Journey';
import AddJourney from './components/Journey/AddJourney';
import JourneyManagement from './components/Journey/JourneyManagement';
import SeatSelection from './components/seat/SelectSeat';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* <Route path='/wallet' element={<Wallet/>}/>
        <Route path='/' element={<RegisterUserForm/>} />
        <Route path='/login' element={<LoginForm/>} /> */}
        <Route path='/bus' element={<BusManagement/>}/>
        <Route path='/route' element={<RouteManagement/>}/>
        {/* <Route path='/' element={<HomeSearch/>}/> */}
        <Route path="/seats/:id" element={<SeatSelectionWrapper />} />
        <Route path='/jo' element={<JourneyManagement/>}/>
      </Routes>

    </BrowserRouter>


  );


}

function SeatSelectionWrapper() {
  const { id } = useParams();
  return <Seats id={id} />;
}


export default App;
