import React, {useState} from "react";
import {useLocation, useNavigate} from 'react-router-dom';
import "./BookingSeats.css";
import axios from "axios";
import Seat from "./Seat";
import FlightOneWayCard from "../../FlightsComponent/FlightOneWayCard";

const BookingSeatsForOneWay = () => {

    const navigate = useNavigate();

    const location = useLocation();

    const {flight, basePrice} = location.state || {};

    const [seats, setSeats] = useState(flight.seats);

    const [bookingSuccess, setBookingSuccess] = useState(false);

    const handleSeatClick = (seatId) => {
        const updatedSeats = seats.map((seat) =>
            seat.id === seatId ? {...seat, clicked: !seat.clicked} : seat
        );
        setSeats(updatedSeats);
    };

    const handleBooking = async () => {
        const totalPrice = (seats.filter((seat) => seat.clicked).reduce((sum, seat) => sum + seat.price, 0) + basePrice);
        const requestBody = {
            startDate: flight.departureDate,
            totalPrice,
            outboundFlightId: flight.id,
            seatsId: seats.filter((seat) => seat.clicked).map((seat) => seat.id),
        };

        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            };
            const response = await axios.post(
                'http://localhost:3000/api/bookings/flights/oneWay',
                requestBody,
                config
            );
            if (response.status === 200) {
                setBookingSuccess(true);
            }
        } catch (error) {
            console.error('Error while booking:', error);
        }
    };


    const renderSeats = () => {
        const seatGroups = [];
        for (let i = 0; i < flight.seats.length; i += 6) {
            seatGroups.push(
                <div className="row blur-include" key={i}>
                    {flight.seats.slice(i, 6 + i).map((seat, index) => (
                        <div key={index} className={'col-2'}
                             style={{paddingBottom: '7%', textAlign: "center"}}>
                            <Seat seat={seat} handleSeatClick={handleSeatClick}/>
                        </div>
                    ))}
                    {i + 3 < flight.seats.length && <div className="col-12"></div>}
                </div>
            );
        }
        return seatGroups;
    };

    if (bookingSuccess) {
        return (
            navigate('/booking/success')
        );
    }

    return (
        <div style={{width: '55%'}}>
            <h2 className="blur-include">Flight</h2>
            <FlightOneWayCard flight={flight} isAll={true}/>
            <br/>
            <div className="container">
                {renderSeats()}
            </div>
            <div>
                <button
                    className="btn btn-primary blur-inculde"
                    onClick={handleBooking}
                    disabled={!seats.some((seat) => seat.clicked)}>Book
                </button>
            </div>
        </div>
    )
}

export default BookingSeatsForOneWay;