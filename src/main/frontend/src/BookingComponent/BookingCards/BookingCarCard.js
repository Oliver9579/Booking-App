import React, {useState} from "react";
import "./BookingCard.css"
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {FormatDate} from "./FormatDate";
import Review from "../../ReviewComponent/Review";
import "../../ReviewComponent/Review.css"
import AddReview from "../../ReviewComponent/AddReview";

const BookingCarCard = ({booking}) => {

    const [showReviewsModal, setShowReviewsModal] = useState(false);
    const [reviews, setReviews] = useState(booking.car.reviews);

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
            {booking.car.pickUpLocation === booking.car.dropOffLocation ? (
                <h4 className="blur-include">{booking.car.pickUpLocation}</h4>
            ) : (
                <h4 className="blur-include">{booking.car.pickUpLocation}-{booking.car.dropOffLocation}</h4>
            )}
            <div className="blur-include"
                 style={{paddingBottom: '15px'}}>{FormatDate(booking.car.pickUpDate)} - {FormatDate(booking.car.dropOffDate)}</div>
            <div className="booking-card blur-include">
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
                        <button className="btn btn-primary reviews-button"
                                style={{height: '30px', marginLeft: '10%'}}
                                type="submit" onClick={toggleReviewsModal}><span></span>Reviews
                        </button>
                        <div className="d-inline" style={{padding: '2px', fontSize: '17px'}}>({reviews.length})</div>
                    </div>
                </div>
            </div>
            <Review reviews={reviews} toggleReviewsModal={toggleReviewsModal}
                    showReviewsModal={showReviewsModal}></Review>

            <AddReview bookedEntityID={booking.car} reviewedEntityType="car/"
                       onNewReview={handleNewReview}></AddReview>
        </div>
    )

}
export default BookingCarCard