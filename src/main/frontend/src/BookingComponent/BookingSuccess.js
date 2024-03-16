import React from 'react';
import "./BookingSuccess.css"

const BookingSuccess = () => {
    return (
        <div className="booking-success">
            <p>Booking successful!</p>
            <a href="/dashboard">Go to Dashboard</a>
        </div>
    );
}

export default BookingSuccess;