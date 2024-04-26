import React from "react";
import {FormatDate} from "./FormatDate";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";

const BookingOneWayFlightCard = ({booking}) => {

    const departureTime = new Date(booking.flight.departureDate);
    const landingTime = new Date(departureTime.getTime() + booking.flight.duration * 60 * 1000);

    return (
        <div style={{width: '900px', margin: '0 auto', paddingBottom: '30px'}}>
            <h4>{booking.flight.originAirportCode} {booking.flight.origin} -&nbsp;
                {booking.flight.destinationAirportCode} {booking.flight.destination}</h4>
            <div
                style={{paddingBottom: '15px'}}>{FormatDate(departureTime)} - {FormatDate(landingTime)}</div>
            <div className="booking-card">
                <div className="row" style={{width: '100%', height: '100%'}}>
                    <div className="col-2" style={{height: '100%', display: "flex"}}>
                        <img src={require(`../../FlightsComponent/img/${booking.flight.img}`)}
                             alt={`${booking.brand} ${booking.model}`}
                             style={{
                                 width: '100px',
                                 display: "block",
                                 margin: "auto",
                                 borderRadius: '5px',
                                 filter: 'opacity(70%)'
                             }}/>
                    </div>
                    <div className="col-8">
                        <div style={{paddingBottom: '5px'}}>
                            <strong>{booking.flight.airline}</strong> {booking.flight.flightNumber}
                        </div>
                        <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>
                            {FormatDate(departureTime)} - {FormatDate(landingTime)}</div>
                        <div>
                            {new Date(landingTime) < new Date() ?
                                <div style={{fontWeight: "350", fontSize: '90%'}}>Completed</div>
                                :
                                <div style={{fontWeight: "350", fontSize: '90%'}}>Active</div>
                            }
                        </div>
                    </div>
                    <div className="col-2" style={{textAlign: 'right', fontSize: '20px'}}>
                        <strong><FontAwesomeIcon icon={faEuroSign}/> {booking.totalPrice}</strong>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default BookingOneWayFlightCard