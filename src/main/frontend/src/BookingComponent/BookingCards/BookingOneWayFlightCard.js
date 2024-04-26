import React, {useState} from "react";
import {FormatDate} from "./FormatDate";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import Review from "../../ReviewComponent/Review";
import AddReview from "../../ReviewComponent/AddReview";

const BookingOneWayFlightCard = ({booking}) => {

    const departureTime = new Date(booking.flight.departureDate);
    const landingTime = new Date(departureTime.getTime() + booking.flight.duration * 60 * 1000);

    const [showReviewsModal, setShowReviewsModal] = useState(false);
    const [showSeats, setShowSeats] = useState(false);
    const [reviews, setReviews] = useState(booking.flight.reviews);

    const handleNewReview = (newReview) => {
        setReviews(prevReviews => [...prevReviews, newReview]);
    };

    const toggleReviewsModal = () => {
        setShowReviewsModal(!showReviewsModal);
        let blurIncludeDivs = document.querySelectorAll(".blur-include");
        if (!showReviewsModal) {
            document.body.style.overflow = 'hidden';
            blurIncludeDivs.forEach((value) => value.classList.add('blur'));
        } else {
            blurIncludeDivs.forEach((value) => value.classList.remove('blur'));
            document.body.style.overflow = 'auto';
        }
    };

    return (
        <div style={{width: '900px', margin: '0 auto', paddingBottom: '30px'}}>
            <h4 className="blur-include">{booking.flight.originAirportCode} {booking.flight.origin} -&nbsp;
                {booking.flight.destinationAirportCode} {booking.flight.destination}</h4>
            <div className="blur-include"
                 style={{paddingBottom: '15px'}}>{FormatDate(departureTime)} - {FormatDate(landingTime)}</div>
            <div className="booking-card blur-include" style={{height: '130px'}}>
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
                                <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>Completed</div>
                                :
                                <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>Active</div>
                            }
                        </div>
                        {showSeats && (
                            <div className="show-seats">
                                {booking.flight.seats.map((seat, index) => (
                                    <div style={{fontWeight: "350", fontSize: '90%'}}
                                         key={index}>{`Number ${seat.seatNumber}
                                (${seat.seatType.toLowerCase()})`}</div>
                                ))}
                            </div>
                        )}
                        <div style={{fontWeight: "550", fontSize: '90%', width: '8%', textDecoration: "underline"}}
                             onMouseEnter={() => setShowSeats(true)}
                             onMouseLeave={() => setShowSeats(false)}>
                            {booking.flight.seats.length === 1 ? 'Seat' : 'Seats'}
                        </div>
                    </div>
                    <div className="col-2" style={{textAlign: 'right', fontSize: '20px'}}>
                        <strong><FontAwesomeIcon icon={faEuroSign}/> {booking.totalPrice}</strong>
                        <button className="btn btn-primary reviews-button"
                                style={{height: '30px', marginLeft: '10%'}}
                                type="submit" onClick={toggleReviewsModal}><span></span>Reviews
                        </button>
                        <div className="d-inline"
                             style={{padding: '2px', fontSize: '17px'}}>({reviews.length})
                        </div>
                    </div>
                </div>
            </div>
            <Review reviews={reviews} toggleReviewsModal={toggleReviewsModal}
                    showReviewsModal={showReviewsModal}></Review>

            <AddReview bookedEntityID={booking.flight} reviewedEntityType="flight/"
                       onNewReview={handleNewReview}></AddReview>
        </div>
    )
}
export default BookingOneWayFlightCard