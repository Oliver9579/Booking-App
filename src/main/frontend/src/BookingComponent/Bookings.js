import React, {useEffect, useState} from "react";
import axios from "axios";
import BookingList from "./BookingList";

const Bookings = () => {

    const [bookings, setBookings] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        fetchBookings();
    }, []);

    const fetchBookings = () => {
        const token = localStorage.getItem("token");
        axios.get('http://localhost:3000/api/bookings', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                setBookings(response.data.bookings);
                setErrorMessage('')
            })
            .catch((error) => {
                if (error.response) {
                    setErrorMessage(error.response.data.message);
                } else {
                    alert(error.message);
                }
            });

    };

    return (
        <div>
            {errorMessage === '' ? (
                <div>
                    <h1 className="blur-include" style={{paddingBottom: '30px'}}>Bookings & Trips</h1>
                    <BookingList bookings={bookings}/>
                </div>
            ) : (
                <div className="alert alert-danger">
                    <h6>{errorMessage}</h6>
                </div>
            )}
        </div>
    )
}
export default Bookings