import React from 'react';
import "./FlightOneWayCard.css"

const FlightRoundTripCard = ({flightToDestination, flightReturn}) => {
    //firstFlight
    const flightToDestinationDepartureTime = new Date(flightToDestination.departureDate);
    const flightToDestinationLandingTime = new Date(flightToDestinationDepartureTime.getTime() + flightToDestination.duration * 60 * 1000); // Convert duration to milliseconds

    const flightToDestinationHours = Math.floor(flightToDestination.duration / 60);
    const flightToDestinationMinutes = flightToDestination.duration % 60;
    const flightToDestinationFormattedDuration = `${flightToDestinationHours}h ${flightToDestinationMinutes}m`;

    const flightToDestinationFormattedDepartureDate = flightToDestinationDepartureTime.toLocaleDateString();
    const flightToDestinationFormattedDepartureTime = flightToDestinationDepartureTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
    const flightToDestinationFormattedLandingDate = flightToDestinationLandingTime.toLocaleDateString();
    const flightToDestinationFormattedLandingTime = flightToDestinationLandingTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

    //secondFlight

    const flightReturnDepartureTime = new Date(flightReturn.departureDate);
    const flightReturnLandingTime = new Date(flightReturnDepartureTime.getTime() + flightReturn.duration * 60 * 1000); // Convert duration to milliseconds

    const flightReturnHours = Math.floor(flightReturn.duration / 60);
    const flightReturnMinutes = flightReturn.duration % 60;
    const flightReturnFormattedDuration = `${flightReturnHours}h ${flightReturnMinutes}m`;

    const flightReturnFormattedDepartureDate = flightReturnDepartureTime.toLocaleDateString();
    const flightReturnFormattedDepartureTime = flightReturnDepartureTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
    const flightReturnFormattedLandingDate = flightReturnLandingTime.toLocaleDateString();
    const flightReturnFormattedLandingTime = flightReturnLandingTime.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

    return (
        <div>
            <div className="flight-card">
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flightToDestination.airline}</div>
                    <div className="p-2 d-inline">Id: {flightToDestination.id}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flightToDestination.origin} to {flightToDestination.destination}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">Departure
                        Time: {flightToDestinationFormattedDepartureDate} {flightToDestinationFormattedDepartureTime}</div>
                    <div className="p-2 d-inline">Landing Time: {flightToDestinationFormattedLandingDate} {flightToDestinationFormattedLandingTime}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">Duration: {flightToDestinationFormattedDuration}</div>
                </div>

                <div className="divider">
                    <hr></hr>
                </div>

                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flightReturn.airline}</div>
                    <div className="p-2 d-inline">Id: {flightReturn.id}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">{flightReturn.origin} to {flightReturn.destination}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">Departure
                        Time: {flightReturnFormattedDepartureDate} {flightReturnFormattedDepartureTime}</div>
                    <div className="p-2 d-inline">Landing Time: {flightReturnFormattedLandingDate} {flightReturnFormattedLandingTime}</div>
                </div>
                <div style={{textAlign: "center"}}>
                    <div className="p-2 d-inline">Duration: {flightReturnFormattedDuration}</div>
                </div>


                <div style={{textAlign: "right"}}>
                    <button className="btn btn-primary " type="submit"><span></span>Select</button>
                </div>
            </div>
            <br/>
        </div>
    );
};

export default FlightRoundTripCard;