import React from 'react';

const FlightsList = ({ flights }) => {
    if (flights.length === 0) {
        return (
            <div>
                <strong style={{fontSize: '30px'}}>There is no flight at the specified time or destination!</strong>
            </div>
        );
    }
    return (
        <div>
            <h2>Flight List</h2>
            <ul>
                {flights.map((flight) => (
                    <li key={flight.id}>
                        <strong>{flight.airline}</strong> - {flight.origin} to {flight.destination}
                        <br/>
                        Departure Date: {flight.departureDate}
                        <br/>
                        Duration: {flight.duration} minutes
                        <br/>
                        Seats:
                        <ul>
                            {flight.seats.map((seat) => (
                                <li key={seat.id}>
                                    {seat.seatNumber} ({seat.seatType}) - ${seat.price}
                                    {seat.availability ? ' (Available)' : ' (Not Available)'}
                                </li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FlightsList;


// import React, {Component} from 'react';
// import axios from 'axios';
//
// class FlightsList extends Component {
//     state = {flights: [],};
//
//
//     // componentDidMount() {
//     //     // Make an API call to fetch flight data
//     //     const token = localStorage.getItem("token");
//     //     axios.get('http://localhost:3000/api/flights', {
//     //         headers: {
//     //             'Authorization': `Bearer ${token}`
//     //         }
//     //     })
//     //         .then((response) => {
//     //             this.setState({flights: response.data.flights}); // Update state with flight data
//     //         })
//     //         .catch((error) => {
//     //             console.error('Error fetching flights:', error);
//     //         });
//     // }
//
//     render() {
//         const {flights} = this.state;
//
//         return (
//             <div>
//                 <h2>Flight List</h2>
//                 <ul>
//                     {flights.map((flight) => (
//                         <li key={flight.id}>
//                             <strong>{flight.airline}</strong> - {flight.origin} to {flight.destination}
//                             <br/>
//                             Departure Date: {flight.departureDate}
//                             <br/>
//                             Duration: {flight.duration} minutes
//                             <br/>
//                             Seats:
//                             <ul>
//                                 {flight.seats.map((seat) => (
//                                     <li key={seat.id}>
//                                         {seat.seatNumber} ({seat.seatType}) - ${seat.price}
//                                         {seat.availability ? ' (Available)' : ' (Not Available)'}
//                                     </li>
//                                 ))}
//                             </ul>
//                         </li>
//                     ))}
//                 </ul>
//             </div>
//         );
//     }
// }
//
// export default FlightsList;