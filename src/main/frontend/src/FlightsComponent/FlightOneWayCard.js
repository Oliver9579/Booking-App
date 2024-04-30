import React, {useState} from 'react';
import "./FlightOneWayCard.css"
import "../ReviewComponent/Review.css"
import {useNavigate} from 'react-router-dom';
import Review from "../ReviewComponent/Review";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign, faPlane, faPlaneDeparture} from "@fortawesome/free-solid-svg-icons";

const FlightOneWayCard = ({flight, isAll}) => {

    const navigate = useNavigate();
    const [showReviewsModal, setShowReviewsModal] = useState(false);

    const handleSelect = () => {
        navigate('/booking/flights/oneWay', {state: {flight, basePrice}});
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
    const formattedDuration = `${hours}h ${minutes}min`;

    const formattedDepartureDate = departureTime.toLocaleDateString();
    const formattedDepartureTime = departureTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
    const formattedLandingTime = landingTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

    const basePrice = flight.duration;

    return (
        <div>

            <div className="flight-card flight-card-header blur-include" style={{maxWidth: '100%'}}>
                <FontAwesomeIcon icon={faPlaneDeparture}/> <p
                style={{display: "inline", fontWeight: "bold"}}>Departure</p> {formattedDepartureDate}
            </div>

            <div className="flight-card flight-card-body blur-include row"
                 style={{maxWidth: '100%', margin: "auto", padding: '10px'}}>
                <div className="col-4" style={{maxWidth: '100%', maxHeight: '100%', paddingTop: '2%'}}>
                    <button className="btn btn-primary reviews-button"
                            style={{height: '30%', marginLeft: '37%'}}
                            type="submit" onClick={toggleReviewsModal}><span></span>Reviews
                    </button>
                    <div className="p-2 d-inline" style={{padding: '2px'}}>({flight.reviews.length})</div>
                    <div style={{maxWidth: '100%', margin: '0'}}>
                        <div className="row">
                            <div className="col-4" style={{maxWidth: '150%', textAlign:"center"}}>
                                <img src={require(`./img/${flight.img}`)} alt={flight.airline}
                                     style={{width: '60px', height: 'auto'}}/>
                            </div>
                            <div className="col-8">
                                <div style={{
                                    fontSize: '12px',
                                    fontWeight: "bold",
                                    paddingTop: '8%'
                                }}>{flight.airline}</div>
                                <div style={{fontSize: '12px'}}>{flight.flightNumber}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-3" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div
                        style={{paddingTop: '10%'}}>
                        <div style={{fontWeight: 'bold', fontSize: '30px'}}>{formattedDepartureTime}</div>
                        <p className="d-inline" style={{fontWeight: 'bold'}}>{flight.originAirportCode} </p>
                        <p className="d-inline">{flight.origin}</p>
                        <div style={{fontSize: '15px', fontWeight: "lighter"}}>{flight.origin}</div>
                    </div>
                </div>
                <div className="col-2" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div style={{fontSize: '17px', paddingTop: '20%'}}>{formattedDuration}</div>
                    <img src={require("./img/arrow.png")} alt="arrow"
                         style={{maxWidth: '100%', height: 'auto', paddingLeft: '10px'}}/>
                    <div style={{fontSize: '12px'}}>{flight.flightType}</div>
                </div>
                <div className="col-3" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div
                        style={{paddingTop: '10%'}}>
                        <div style={{fontWeight: 'bold', fontSize: '30px'}}>{formattedLandingTime}</div>
                        <p className="d-inline" style={{fontWeight: 'bold'}}>{flight.destinationAirportCode} </p>
                        <p className="d-inline">{flight.destination}</p>
                        <div style={{fontSize: '15px', fontWeight: "lighter"}}>{flight.destination}</div>
                    </div>
                </div>
            </div>

            {!isAll && (
                <div className="flight-card flight-card-footer blur-include text-right row"
                     style={{maxWidth: '100%', padding: '15px'}}>
                    <div className="col-8" style={{fontSize: '18px', paddingTop: '10px', paddingRight: 0}}><FontAwesomeIcon
                        icon={faPlane}/> Standard ticket
                    </div>
                    <div className=" col-2" style={{paddingLeft: 0}}>
                        <div style={{fontSize: '20px', fontWeight: 'bold'}}><FontAwesomeIcon
                            icon={faEuroSign}/> {basePrice}</div>
                        <div className="" style={{fontSize: '10px', fontWeight: 'lighter'}}>price per person</div>
                    </div>
                    <div className="col-2" style={{padding: '0'}}>
                        <button className="btn btn-primary book-button" onClick={handleSelect} type="submit">
                            <span></span>Select
                        </button>
                    </div>
                </div>
            )}
            <br/>
            <Review reviews={flight.reviews} toggleReviewsModal={toggleReviewsModal}
                    showReviewsModal={showReviewsModal}></Review>
        </div>
    );
};

export default FlightOneWayCard;
