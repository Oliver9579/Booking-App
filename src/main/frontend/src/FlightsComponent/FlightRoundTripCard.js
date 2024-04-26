import React, {useState} from 'react';
import "./FlightOneWayCard.css"
import "../ReviewComponent/Review.css"
import {useNavigate} from "react-router-dom";
import Review from "../ReviewComponent/Review";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEuroSign, faPlane, faPlaneArrival, faPlaneDeparture} from "@fortawesome/free-solid-svg-icons";

const FlightRoundTripCard = ({flightToDestination, flightReturn}) => {

    const navigate = useNavigate();
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

    const handleSelect = () => {
        navigate('/booking/flights/roundTrip', {state: {flightToDestination, flightReturn, basePrice}});
    };

    //firstFlight
    const flightToDestinationDepartureTime = new Date(flightToDestination.departureDate);
    const flightToDestinationLandingTime = new Date(flightToDestinationDepartureTime.getTime() + flightToDestination.duration * 60 * 1000); // Convert duration to milliseconds

    const flightToDestinationHours = Math.floor(flightToDestination.duration / 60);
    const flightToDestinationMinutes = flightToDestination.duration % 60;
    const flightToDestinationFormattedDuration = `${flightToDestinationHours}h ${flightToDestinationMinutes}m`;

    const flightToDestinationFormattedDepartureDate = flightToDestinationDepartureTime.toLocaleDateString();
    const flightToDestinationFormattedDepartureTime = flightToDestinationDepartureTime.toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit'
    });
    const flightToDestinationFormattedLandingTime = flightToDestinationLandingTime.toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit'
    });

    //secondFlight

    const flightReturnDepartureTime = new Date(flightReturn.departureDate);
    const flightReturnLandingTime = new Date(flightReturnDepartureTime.getTime() + flightReturn.duration * 60 * 1000); // Convert duration to milliseconds

    const flightReturnHours = Math.floor(flightReturn.duration / 60);
    const flightReturnMinutes = flightReturn.duration % 60;
    const flightReturnFormattedDuration = `${flightReturnHours}h ${flightReturnMinutes}m`;

    const flightReturnFormattedDepartureDate = flightReturnDepartureTime.toLocaleDateString();
    const flightReturnFormattedDepartureTime = flightReturnDepartureTime.toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit'
    });
    const flightReturnFormattedLandingTime = flightReturnLandingTime.toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit'
    });

    const basePrice = flightToDestination.duration + flightReturn.duration;

    return (
        <div style={{paddingBottom: '60px'}}>
            {/*<div className="flight-card selected blur-include" style={{maxWidth: '100%'}}>Your selected trip</div>*/}

            <div className="flight-card flight-card-header blur-include" style={{maxWidth: '100%'}}>
                <FontAwesomeIcon icon={faPlaneDeparture}/> <p
                style={{
                    display: "inline",
                    fontWeight: "bold"
                }}>Departure</p> {flightToDestinationFormattedDepartureDate}
            </div>

            <div className="flight-card flight-card-body blur-include row"
                 style={{maxWidth: '100%', margin: "auto", padding: '10px'}}>
                <div className="col-4" style={{maxWidth: '100%', maxHeight: '100%', paddingTop: '2%'}}>
                    <button className="btn btn-primary reviews-button"
                            style={{height: '30%', marginLeft: '37%'}}
                            type="submit" onClick={toggleFlightToDestinationReviewsModal}><span></span>Reviews
                    </button>
                    <div className="p-2 d-inline" style={{padding: '2px'}}>({flightToDestination.reviews.length})</div>
                    <div style={{maxWidth: '100%', margin: '0'}}>
                        <div className="row">
                            <div className="col-4" style={{maxWidth: '150%', textAlign: "center"}}>
                                <img src={require(`./img/${flightToDestination.img}`)} alt={flightToDestination.airline}
                                     style={{width: '60px', height: 'auto'}}/>
                            </div>
                            <div className="col-8">
                                <div style={{
                                    fontSize: '12px',
                                    fontWeight: "bold",
                                    paddingTop: '8%'
                                }}>{flightToDestination.airline}</div>
                                <div style={{fontSize: '12px'}}>{flightToDestination.flightNumber}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-3" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div
                        style={{paddingTop: '10%'}}>
                        <div style={{
                            fontWeight: 'bold',
                            fontSize: '30px'
                        }}>{flightToDestinationFormattedDepartureTime}</div>
                        <p className="d-inline"
                           style={{fontWeight: 'bold'}}>{flightToDestination.originAirportCode} </p>
                        <p className="d-inline">{flightToDestination.origin}</p>
                        <div style={{fontSize: '15px', fontWeight: "lighter"}}>{flightToDestination.origin}</div>
                    </div>
                </div>
                <div className="col-2" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div style={{fontSize: '17px', paddingTop: '20%'}}>{flightToDestinationFormattedDuration}</div>
                    <img src={require("./img/arrow.png")} alt="arrow"
                         style={{maxWidth: '100%', height: 'auto', paddingLeft: '10px'}}/>
                    <div style={{fontSize: '12px'}}>{flightToDestination.flightType}</div>
                </div>
                <div className="col-3" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div
                        style={{paddingTop: '10%'}}>
                        <div style={{
                            fontWeight: 'bold',
                            fontSize: '30px'
                        }}>{flightToDestinationFormattedLandingTime}</div>
                        <p className="d-inline"
                           style={{fontWeight: 'bold'}}>{flightToDestination.destinationAirportCode} </p>
                        <p className="d-inline">{flightToDestination.destination}</p>
                        <div style={{fontSize: '15px', fontWeight: "lighter"}}>{flightToDestination.destination}</div>
                    </div>
                </div>
            </div>

            <div className="flight-card flight-card-header blur-include" style={{maxWidth: '100%'}}>
                <FontAwesomeIcon icon={faPlaneArrival}/> <p
                style={{
                    display: "inline",
                    fontWeight: "bold"
                }}>Return</p> {flightReturnFormattedDepartureDate}
            </div>

            <div className="flight-card flight-card-body blur-include row"
                 style={{maxWidth: '100%', margin: "auto", padding: '10px'}}>
                <div className="col-4" style={{maxWidth: '100%', maxHeight: '100%', paddingTop: '2%'}}>
                    <button className="btn btn-primary reviews-button"
                            style={{height: '30%', marginLeft: '37%'}}
                            type="submit" onClick={toggleFlightReturnReviewsModal}><span></span>Reviews
                    </button>
                    <div className="p-2 d-inline" style={{padding: '2px'}}>({flightReturn.reviews.length})</div>
                    <div style={{maxWidth: '100%', margin: '0'}}>
                        <div className="row">
                            <div className="col-4" style={{maxWidth: '150%', textAlign: "center"}}>
                                <img src={require(`./img/${flightToDestination.img}`)} alt={flightReturn.airline}
                                     style={{width: '60px', height: 'auto'}}/>
                            </div>
                            <div className="col-8">
                                <div style={{
                                    fontSize: '12px',
                                    fontWeight: "bold",
                                    paddingTop: '8%'
                                }}>{flightReturn.airline}</div>
                                <div style={{fontSize: '12px'}}>{flightReturn.flightNumber}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-3" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div
                        style={{paddingTop: '10%'}}>
                        <div style={{fontWeight: 'bold', fontSize: '30px'}}>{flightReturnFormattedDepartureTime}</div>
                        <p className="d-inline" style={{fontWeight: 'bold'}}>{flightReturn.originAirportCode} </p>
                        <p className="d-inline">{flightReturn.origin}</p>
                        <div style={{fontSize: '15px', fontWeight: "lighter"}}>{flightReturn.origin}</div>
                    </div>
                </div>
                <div className="col-2" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div style={{fontSize: '17px', paddingTop: '20%'}}>{flightReturnFormattedDuration}</div>
                    <img src={require("./img/arrow.png")} alt="arrow"
                         style={{maxWidth: '100%', height: 'auto', paddingLeft: '10px'}}/>
                    <div style={{fontSize: '12px'}}>{flightReturn.flightType}</div>
                </div>
                <div className="col-3" style={{maxWidth: '100%', maxHeight: '100%', textAlign: "center"}}>
                    <div
                        style={{paddingTop: '10%'}}>
                        <div style={{fontWeight: 'bold', fontSize: '30px'}}>{flightReturnFormattedLandingTime}</div>
                        <p className="d-inline" style={{fontWeight: 'bold'}}>{flightReturn.destinationAirportCode} </p>
                        <p className="d-inline">{flightReturn.destination}</p>
                        <div style={{fontSize: '15px', fontWeight: "lighter"}}>{flightReturn.destination}</div>
                    </div>
                </div>
            </div>
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
                        <span></span>Book
                    </button>
                </div>
            </div>

            <Review reviews={flightToDestination.reviews} toggleReviewsModal={toggleFlightToDestinationReviewsModal}
                    showReviewsModal={showFlightToDestinationReviewsModal}></Review>

            <Review reviews={flightReturn.reviews} toggleReviewsModal={toggleFlightReturnReviewsModal}
                    showReviewsModal={showFlightReturnReviewsModal}></Review>
        </div>
    );
};

export default FlightRoundTripCard;