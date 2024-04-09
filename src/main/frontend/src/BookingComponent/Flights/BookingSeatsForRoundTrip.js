import React, {useState} from "react";
import {useLocation} from 'react-router-dom';
import "./BookingSeats.css";
import axios from "axios";
import Seat from "./Seat";
import FlightOneWayCard from "../../FlightsComponent/FlightOneWayCard";

const BookingSeatsForRoundTrip = () => {

    const location = useLocation();

    const {flightToDestination, flightReturn} = location.state || {};

    const [flightToDestinationSeats, setFlightToDestinationSeats] = useState(flightToDestination.seats);

    const [flightReturnSeats, setFlightReturnSeats] = useState(flightReturn.seats);

    const [bookingSuccess, setBookingSuccess] = useState(false);


    const handleSeatClick = (seatId) => {
        const updatedFlight1Seats = flightToDestinationSeats.map((seat) =>
            seat.id === seatId ? {...seat, clicked: !seat.clicked} : seat
        );
        setFlightToDestinationSeats(updatedFlight1Seats);

        const updatedFlight2Seats = flightReturnSeats.map((seat) =>
            seat.id === seatId ? {...seat, clicked: !seat.clicked} : seat
        );
        setFlightReturnSeats(updatedFlight2Seats);
    };

    const handleBooking = async () => {
        let totalPrice = flightToDestinationSeats.filter((seat) => seat.clicked).reduce((sum, seat) => sum + seat.price, 0);
        totalPrice += flightReturnSeats.filter((seat) => seat.clicked).reduce((sum, seat) => sum + seat.price, 0);
        const requestBody = {
            startDate: flightToDestination.departureDate, // Use the appropriate field from your searchData
            totalPrice: totalPrice,
            outboundFlightId: flightToDestination.id,
            returnFlightId: flightReturn.id,
            endDate: flightReturn.departureDate,
            outboundFlightSeatsId: flightToDestinationSeats.filter((seat) => seat.clicked).map((seat) => seat.id),
            returnFlightSeatsId: flightReturnSeats.filter((seat) => seat.clicked).map((seat) => seat.id)
        };

        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            };
            const response = await axios.post(
                'http://localhost:3000/api/bookings/flights/roundTrip',
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

    const renderFlightToDestinationSeats = () => {
        const seatGroups = [];
        for (let i = 0; i < flightToDestination.seats.length; i += 6) {
            seatGroups.push(
                <div className="row blur-include" key={i}>
                    {flightToDestination.seats.slice(i, 6 + i).map((seat, index) => (
                        <div key={index} className={'col-2'}
                             style={{paddingBottom: '7%', textAlign: "center"}}>
                            <Seat seat={seat} handleSeatClick={handleSeatClick}/>
                        </div>
                    ))}
                    {i + 3 < flightToDestination.seats.length && <div className="col-12"></div>}
                </div>
            );
        }
        return seatGroups;
    };

    const renderFlightReturnSeats = () => {
        const seatGroups = [];
        for (let i = 0; i < flightReturn.seats.length; i += 6) {
            seatGroups.push(
                <div className="row blur-include" key={i}>
                    {flightReturn.seats.slice(i, 6 + i).map((seat, index) => (
                        <div key={index} className={'col-2'}
                             style={{paddingBottom: '7%', textAlign: "center"}}>
                            <Seat seat={seat} handleSeatClick={handleSeatClick}/>
                        </div>
                    ))}
                    {i + 3 < flightReturn.seats.length && <div className="col-12"></div>}
                </div>
            );
        }
        return seatGroups;
    };

    if (bookingSuccess) {
        return (
            <div className="booking-success">
                <p>Booking successful!</p>
                <a href="/dashboard">Go to Dashboard</a>
            </div>
        );
    }

    return (
        <div>
            <h2 className="blur-include">Flight</h2>
            <FlightOneWayCard flight={flightToDestination} isAll={true}/>
            <br/>
            <div className="container">
                {renderFlightToDestinationSeats()}
            </div>
            <br/>
            <br/>
            <FlightOneWayCard flight={flightReturn} isAll={true}/>
            <br/>
            <div className="container">
                {renderFlightReturnSeats()}
            </div>
            <div>
                <button
                    className="btn btn-primary "
                    onClick={handleBooking}
                    disabled={!flightToDestinationSeats.some((seat) => seat.clicked) ||
                        !flightReturnSeats.some((seat) => seat.clicked)}>Book
                </button>
            </div>
        </div>
    )


}

export default BookingSeatsForRoundTrip;