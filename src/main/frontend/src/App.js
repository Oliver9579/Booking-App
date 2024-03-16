import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import RegistrationForm from './RegistrationComponent/RegistrationForm';
import LoginForm from './LoginComponent/LoginForm';
import Dashboard from "./DashboardComponent/Dashboard";
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Flights from "./FlightsComponent/Flights";
import Hotels from "./HotelsComponent/Hotels";
import BookingSeatsForOneWay from "./BookingComponent/Flights/BookingSeatsForOneWay";
import BookingSeatsForRoundTrip from "./BookingComponent/Flights/BookingSeatsForRoundTrip";
import BookingRooms from "./BookingComponent/Hotels/BookingRooms";
import Cars from "./CarComponent/Cars";
import BookingSuccess from "./BookingComponent/BookingSuccess";


const App = () => {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<RegistrationForm/>}/>
                    <Route path="/login" element={<LoginForm/>}/>
                    <Route path="/dashboard" element={<Dashboard/>}/>
                    <Route path="/flights" element={<Flights/>}/>
                    <Route path="/hotels" element={<Hotels/>}/>
                    <Route path="/cars" element={<Cars/>}/>
                    <Route path="/booking/flights/oneWay" element={<BookingSeatsForOneWay/>}/>
                    <Route path="/booking/flights/roundTrip" element={<BookingSeatsForRoundTrip/>}/>
                    <Route path="/booking/hotels" element={<BookingRooms/>}/>
                    <Route path="/booking/success" element={<BookingSuccess/>}/>
                </Routes>
            </div>
        </Router>
    );
};

export default App;
