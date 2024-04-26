import React, {useState} from "react";
import {FormatDate} from "./FormatDate";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import Review from "../../ReviewComponent/Review";

const BookingRoundTripCard = ({booking}) => {

    const departureTimeToDestination = new Date(booking.flightToDestination.departureDate);
    const landingTimeToDestination = new Date(departureTimeToDestination.getTime() + booking.flightToDestination.duration * 60 * 1000);

    const departureTimeToReturn = new Date(booking.flightReturn.departureDate);
    const landingTimeToReturn = new Date(departureTimeToReturn.getTime() + booking.flightReturn.duration * 60 * 1000);

    const [showFlightToDestinationReviewsModal, setShowFlightToDestinationReviewsModal] = useState(false);
    const [showFlightReturnReviewsModal, setShowFlightReturnReviewsModal] = useState(false);
    const toggleFlightToDestinationReviewsModal = () => {
        setShowFlightToDestinationReviewsModal(!showFlightToDestinationReviewsModal);
        let blurIncludeDivs = document.querySelectorAll(".blur-include");
        if (!showFlightToDestinationReviewsModal) {
            document.body.style.overflow = 'hidden';
            blurIncludeDivs.forEach((value) => value.classList.add('blur'));
        } else {
            blurIncludeDivs.forEach((value) => value.classList.remove('blur'));
            document.body.style.overflow = 'auto';
        }
    };

    const toggleFlightReturnReviewsModal = () => {
        setShowFlightReturnReviewsModal(!showFlightReturnReviewsModal);
        let blurIncludeDivs = document.querySelectorAll(".blur-include");
        if (!showFlightReturnReviewsModal) {
            document.body.style.overflow = 'hidden';
            blurIncludeDivs.forEach((value) => value.classList.add('blur'));
        } else {
            blurIncludeDivs.forEach((value) => value.classList.remove('blur'));
            document.body.style.overflow = 'auto';
        }
    };

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
            <div className="booking-card blur-include" style={{height: '150px'}}>
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
                                <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>Completed</div>
                                :
                                <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>Active</div>
                            }
                        </div>
                        <button className="btn btn-primary reviews-button"
                                style={{height: '25%'}}
                                type="submit" onClick={toggleFlightToDestinationReviewsModal}><span></span>Reviews
                        </button>
                        <div className="d-inline"
                             style={{padding: '2px', fontSize: '17px'}}>({booking.flightToDestination.reviews.length})
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
                        <button className="btn btn-primary reviews-button"
                                style={{height: '25%'}}
                                type="submit" onClick={toggleFlightReturnReviewsModal}><span></span>Reviews
                        </button>
                        <div className="d-inline"
                             style={{padding: '2px', fontSize: '17px'}}>({booking.flightReturn.reviews.length})
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
            <Review reviews={booking.flightToDestination.reviews}
                    toggleReviewsModal={toggleFlightToDestinationReviewsModal}
                    showReviewsModal={showFlightToDestinationReviewsModal}></Review>

            <Review reviews={booking.flightReturn.reviews} toggleReviewsModal={toggleFlightReturnReviewsModal}
                    showReviewsModal={showFlightReturnReviewsModal}></Review>
        </div>
    )

}
export default BookingRoundTripCard