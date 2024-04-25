import React from "react";
import "./BookingCard.css"
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {FormatDate} from "./FormatDate";

const BookingCarCard = ({booking}) => {

    return (
        <div style={{width: '900px', margin: '0 auto', paddingBottom: '30px'}}>
            {booking.car.pickUpLocation === booking.car.dropOffLocation ? (
                <h4>{booking.car.pickUpLocation}</h4>
            ) : (
                <h4>{booking.car.pickUpLocation}-{booking.car.dropOffLocation}</h4>
            )}
            <div
                style={{paddingBottom: '15px'}}>{FormatDate(booking.car.pickUpDate)} - {FormatDate(booking.car.dropOffDate)}</div>
            <div className="booking-card">
                <div className="row" style={{width: '100%', height: '100%'}}>
                    <div className="col-2" style={{height: '100%', display: "flex"}}>
                        <img src={require(`../../CarComponent/img/${booking.car.img}`)}
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
                            <strong>{booking.car.brand} {booking.car.model}</strong>
                        </div>
                        <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>
                            {FormatDate(booking.car.pickUpDate)} <span>&#183;</span>&nbsp;
                            {booking.car.pickUpLocation} - {FormatDate(booking.car.dropOffDate)}&nbsp;
                            <span>&#183;</span>&nbsp;{booking.car.dropOffLocation}</div>
                        <div>
                            {new Date(booking.car.dropOffDate) < new Date() ?
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
export default BookingCarCard