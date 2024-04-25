import React, {Component} from "react";
import BookingHotelCard from "./BookingCards/BookingHotelCard";
import BookingCarCard from "./BookingCards/BookingCarCard";
import BookingOneWayFlightCard from "./BookingCards/BookingOneWayFlightCard";
import BookingRoundTripCard from "./BookingCards/BookingRoundTripCard";

class BookingList extends Component {

    render() {
        const {bookings} = this.props;

        return (
            <div >
                {bookings.map((booking, index) => {
                    if (booking.car) {
                        return <BookingCarCard key={index} booking={booking}/>;
                    } else if (booking.flight) {
                        return <BookingOneWayFlightCard key={index} booking={booking}/>;
                    } else if (booking.hotel) {
                        return <BookingHotelCard key={index} booking={booking}/>;
                    } else {
                        return <BookingRoundTripCard key={index} booking={booking}/>;
                    }
                })}
            </div>
        );
    }
}

export default BookingList