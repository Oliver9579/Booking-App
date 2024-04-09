import React, {Component} from 'react';
import FlightOneWayCard from './FlightOneWayCard';
import FlightRoundTripCard from './FlightRoundTripCard';

class FlightsList extends Component {
    render() {
        const {flights, searchData, isAll} = this.props;


        if (flights.length === 0) {
            return (
                <div>
                    <strong style={{fontSize: '30px'}}>There is no flight at the specified time or destination!</strong>
                </div>
            );
        }

        if (searchData.flightType === 'return') {
            const flightCards = [];
            for (let i = 0; i < flights.length - 1; i++) {
                flightCards.push(
                    <FlightRoundTripCard
                        key={i}
                        flightToDestination={flights[i]}
                        flightReturn={flights[i + 1]}
                    />
                );
                i++;
            }
            return (
                <div>
                    <h2 className="blur-include">Flight List</h2>
                    {flightCards}
                </div>
            );
        } else {
            return (
                <div>
                    <h2 className="blur-include">Flight List</h2>
                    {flights.map((flight, index) => (
                        <FlightOneWayCard key={index} flight={flight} searchData={searchData} isAll={isAll}/>
                    ))}
                </div>
            );
        }
    }
}

export default FlightsList;
