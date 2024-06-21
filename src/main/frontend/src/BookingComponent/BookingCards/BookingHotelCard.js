import React, {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import {FormatDate} from "./FormatDate";
import Review from "../../ReviewComponent/Review";
import AddReview from "../../ReviewComponent/AddReview";

const BookingHotelCard = ({booking}) => {

    const [showReviewsModal, setShowReviewsModal] = useState(false);
    const [showRooms, setShowRooms] = useState(false);
    const [reviews, setReviews] = useState(booking.hotel.reviews);

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
            <h4 className="blur-include">{booking.hotel.location}</h4>
            <div className="blur-include"
                 style={{paddingBottom: '15px'}}>{FormatDate(booking.startDate)} - {FormatDate(booking.endDate)}</div>
            <div className="booking-card blur-include" style={{height: '130px'}}>
                <div className="row" style={{width: '100%', height: '100%'}}>
                    <div className="col-2" style={{height: '100%', display: "flex"}}>
                        <img src={require(`../../HotelsComponent/img/${booking.hotel.img}`)}
                             alt={`${booking.name}`}
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
                            <strong>{booking.hotel.name}</strong>
                        </div>
                        <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>
                            {FormatDate(booking.startDate)} - {FormatDate(booking.endDate)}&nbsp;
                            <span>&#183;</span>&nbsp;{booking.hotel.location}</div>
                        <div>
                            {new Date(booking.endDate) < new Date() ?
                                <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>Completed</div>
                                :
                                <div style={{fontWeight: "350", fontSize: '90%', paddingBottom: '5px'}}>Active</div>
                            }
                        </div>
                        {showRooms && (
                            <div className="show-rooms">
                                {booking.hotel.rooms.map((room, index) => (
                                    <div style={{fontWeight: "350", fontSize: '90%'}}
                                         key={index}>{`id ${room.id} type
                                (${room.roomType.toLowerCase()})`}</div>
                                ))}
                            </div>
                        )}
                        <div style={{fontWeight: "550", fontSize: '90%', width: '8%', textDecoration: "underline"}}
                             onMouseEnter={() => setShowRooms(true)}
                             onMouseLeave={() => setShowRooms(false)}>
                            {booking.hotel.rooms.length === 1 ? 'Room' : 'Rooms'}
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

            <AddReview bookedEntityID={booking.hotel} reviewedEntityType="hotel/"
                       onNewReview={handleNewReview}></AddReview>
        </div>
    )

}
export default BookingHotelCard