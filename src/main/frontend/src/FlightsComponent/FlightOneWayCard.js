import React from 'react';
import "./FlightOneWayCard.css"
import {useNavigate} from 'react-router-dom';

const FlightOneWayCard = ({flight, isAll}) => {

    const navigate = useNavigate();

    const handleSelect = () => {
        navigate('/booking/flights/oneWay', {state: {flight}});
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
            <div className="flight-card" style={{maxWidth: '100%'}}>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flight.airline}</div>
                    <div className="p-2 d-inline">Id: {flight.id}</div>
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
        </div>
    );
};

export default FlightOneWayCard;
