import React from "react";
import {FormatDate} from "./FormatDate";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";

const BookingRoundTripCard = ({booking}) => {

    const departureTimeToDestination = new Date(booking.flightToDestination.departureDate);
    const landingTimeToDestination = new Date(departureTimeToDestination.getTime() + booking.flightToDestination.duration * 60 * 1000);

    const departureTimeToReturn = new Date(booking.flightReturn.departureDate);
    const landingTimeToReturn = new Date(departureTimeToReturn.getTime() + booking.flightReturn.duration * 60 * 1000);

    return (
        <div style={{width: '900px', margin: '0 auto', paddingBottom: '30px'}}>
            <div className="row blur-include">
                <div className="col-6">
                    <h4>{booking.flightToDestination.originAirportCode} {booking.flightToDestination.origin} -&nbsp;
                        {booking.flightToDestination.destinationAirportCode} {booking.flightToDestination.destination}</h4>
                </div>
                <div className="col-6" style={{textAlign: "right"}}>
                    <h4>{booking.flightReturn.originAirportCode} {booking.flightReturn.origin} -&nbsp;
                        {booking.flightReturn.destinationAirportCode} {booking.flightReturn.destination}</h4>
                </div>
            </div>
            <div className="row blur-include">
                <div className="col-6" style={{paddingBottom: '15px'}}>
                    {FormatDate(departureTimeToDestination)} - {FormatDate(landingTimeToDestination)}
                </div>
                <div className="col-6" style={{paddingBottom: '15px', textAlign: "right"}}>
                    {FormatDate(departureTimeToReturn)} - {FormatDate(landingTimeToReturn)}
                </div>
            </div>
            <div className="booking-card blur-include" style={{height: '120px'}}>
                <div className="row" style={{width: '100%', height: '100%'}}>
                    <div className="col-2" style={{height: '100%', display: "flex"}}>
                        <img src={require(`../../FlightsComponent/img/${booking.flightToDestination.img}`)}
                             alt={`${booking.brand} ${booking.model}`}
                             style={{
                                 width: '80px',
                                 display: "block",
                                 margin: "auto",
                                 borderRadius: '5px',
                                 filter: 'opacity(70%)'
                             }}/>
                    </div>
                    <div className="col-3">
                        <div style={{paddingBottom: '5px'}}>
                            <strong>{booking.flightToDestination.airline}</strong> {booking.flightToDestination.flightNumber}
                        </div>
                        <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>
                            {FormatDate(departureTimeToDestination)} - {FormatDate(landingTimeToDestination)}</div>
                        <div>
                            {new Date(landingTimeToDestination) < new Date() ?
                                <div style={{fontWeight: "350", fontSize: '90%'}}>Completed</div>
                                :
                                <div style={{fontWeight: "350", fontSize: '90%'}}>Active</div>
                            }
                        </div>
                    </div>
                    <div className="col-2" style={{textAlign: 'center', fontSize: '20px'}}>
                        <strong><FontAwesomeIcon icon={faEuroSign}/> {booking.totalPrice}</strong>
                    </div>
                    <div className="col-3" style={{textAlign: "right"}}>
                        <div style={{paddingBottom: '5px'}}>
                            <strong>{booking.flightReturn.airline}</strong> {booking.flightReturn.flightNumber}
                        </div>
                        <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>
                            {FormatDate(departureTimeToReturn)} - {FormatDate(landingTimeToReturn)}</div>
                        <div>
                            {new Date(landingTimeToReturn) < new Date() ?
                                <div style={{fontWeight: "350", fontSize: '90%'}}>Completed</div>
                                :
                                <div style={{fontWeight: "350", fontSize: '90%'}}>Active</div>
                            }
                        </div>
                    </div>
                    <div className="col-2" style={{height: '100%', display: "flex"}}>
                        <img src={require(`../../FlightsComponent/img/${booking.flightReturn.img}`)}
                             alt={`${booking.brand} ${booking.model}`}
                             style={{
                                 width: '80px',
                                 display: "block",
                                 margin: "auto",
                                 borderRadius: '5px',
                                 filter: 'opacity(70%)'
                             }}/>
                    </div>
                    <hr/>
                </div>
            </div>
        </div>
    )

}
export default BookingRoundTripCard