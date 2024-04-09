import React, {useState} from 'react';
import "./FlightOneWayCard.css"
import "../ReviewComponent/Review.css"
import {useNavigate} from 'react-router-dom';
import Review from "../ReviewComponent/Review";

const FlightOneWayCard = ({flight, isAll}) => {

    const navigate = useNavigate();
    const [showReviewsModal, setShowReviewsModal] = useState(false);

    const handleSelect = () => {
        navigate('/booking/flights/oneWay', {state: {flight}});
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

    const departureTime = new Date(flight.departureDate);
    const landingTime = new Date(departureTime.getTime() + flight.duration * 60 * 1000);

    const hours = Math.floor(flight.duration / 60);
    const minutes = flight.duration % 60;
    const formattedDuration = `${hours}h ${minutes}m`;

    const formattedDepartureDate = departureTime.toLocaleDateString();
    const formattedDepartureTime = departureTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
    const formattedLandingDate = landingTime.toLocaleDateString();
    const formattedLandingTime = landingTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

    return (
        <div>
            <div className="flight-card blur-include" style={{maxWidth: '80%'}}>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flight.airline}</div>
                    <div className="p-2 d-inline">Id: {flight.id}</div>
                    <button className="p-2 d-inline btn btn-primary reviewsButton"
                            type="submit" onClick={toggleReviewsModal}><span></span>Reviews
                    </button>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flight.origin} to {flight.destination}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">Departure
                        Time: {formattedDepartureDate} {formattedDepartureTime}</div>
                    <div className="p-2 d-inline">Landing Time: {formattedLandingDate} {formattedLandingTime}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">Duration: {formattedDuration}</div>
                </div>
                {!isAll && (
                    <div style={{textAlign: "right"}}>
                        <button className="btn btn-primary " onClick={handleSelect} type="submit"><span></span>Select
                        </button>
                    </div>
                )}
            </div>
            <br/>
            <Review reviews={flight.reviews} toggleReviewsModal={toggleReviewsModal}
                    showReviewsModal={showReviewsModal}></Review>
        </div>
    );
};

export default FlightOneWayCard;
