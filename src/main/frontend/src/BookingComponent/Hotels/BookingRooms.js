import React, {useState} from "react";
import {useLocation, useNavigate} from 'react-router-dom';
import "./BookingRooms.css";
import axios from "axios";
import Room from "./Room";
import HotelCard from "../../HotelsComponent/HotelCard";
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";


const BookingRooms = () => {

    const navigate = useNavigate();

    const [roomCounts, setRoomCounts] = useState({
        SINGLE: 0,
        DOUBLE: 0,
        TRIPLE: 0,
        FAMILY: 0
    });

    const [roomPrices, setRoomPrices] = useState({
        SINGLE: 0,
        DOUBLE: 0,
        TRIPLE: 0,
        FAMILY: 0
    });

    const location = useLocation();

    const {hotel, searchData} = location.state || {};

    const [bookingSuccess, setBookingSuccess] = useState(false);

    const renderRooms = () => {

        const totalPriceSum = Object.values(roomPrices).reduce((acc, price) => acc + price, 0);
        const totalRoomCount = Object.values(roomCounts).reduce((acc, cnt) => acc + cnt, 0);

        return hotel.rooms.map((room, index) => (
            <div className="row" key={index}>
                <Room room={room} searchData={searchData} onRoomCountChange={handleRoomCountChange}/>
                {index === 0 ?
                    <div className="col-2 cols booking-col">
                        {totalPriceSum === 0 ? <div></div> : <div>
                            {totalRoomCount === 1 ? '1 room' : `${totalRoomCount} rooms`} for <br/>
                            <h3 style={{fontWeight: "normal"}}>{totalPriceSum} <FontAwesomeIcon icon={faEuroSign}/></h3>
                        </div>}
                        <button className="btn btn-primary blur-include" onClick={handleBooking}
                                disabled={!Object.values(roomCounts).some(count => count > 0)}>Book
                        </button>
                    </div> :
                    <div className="col-2 cols booking-col"></div>}
            </div>
        ));
    };

    const handleRoomCountChange = (roomType, count, totalPrice) => {
        setRoomCounts(prevCounts => ({
            ...prevCounts,
            [roomType]: count
        }));

        setRoomPrices(prevPrices => ({
            ...prevPrices,
            [roomType]: totalPrice || 0
        }));
    };

    const handleBooking = async () => {
        const totalPriceSum = Object.values(roomPrices).reduce((acc, price) => acc + price, 0);
        const rooms = Object.entries(roomCounts).map(([roomType, roomCount]) => ({
            roomType,
            roomCount: roomCount
        }));
        const requestBody = {
            startDate: searchData.checkInDate + " 14:00:00",
            totalPrice: totalPriceSum,
            endDate: searchData.checkOutDate + " 10:00:00",
            hotelId: hotel.id,
            rooms: rooms
        };

        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            };
            const response = await axios.post(
                'http://localhost:3000/api/bookings/hotels',
                requestBody,
                config
            );
            if (response.status === 200) {
                setBookingSuccess(true);
            }
        } catch (error) {
            console.error('Error while booking:', error);
        }
    };

    if (bookingSuccess) {
        return (
            navigate('/booking/success')
        );
    }

    return (
        <div style={{width: '60%'}}>
            <h2 className="blur-include" style={{paddingLeft: '15%'}}>Hotel</h2>
            <HotelCard hotel={hotel} isAll={true}/>
            <br/>
            <h4 className="blur-include">Available Rooms</h4>
            <div className="row blur-include" style={{backgroundColor: '#0096FF', color: "white"}}>
                <div className="col-10 cols row" style={{padding: 0, textAlign: "center"}}>
                    <div className="col-3 room-details" style={{padding: 0}}>
                        <p style={{margin: '10%', fontWeight: "bold"}}>Room Type</p>
                    </div>
                    <div className="col-3 room-details" style={{padding: 0}}>
                        <p style={{margin: '10%', fontWeight: "bold"}}>Number of Guests</p>
                    </div>
                    <div className="col-3 room-details" style={{padding: 0}}>
                        <p style={{margin: '10%', fontWeight: "bold"}}>Price for {hotel.numberOfNights} nights</p>
                    </div>
                    <div className="col-3 room-details" style={{padding: 0}}>
                        <p style={{margin: '10%', fontWeight: "bold"}}>Select amount</p>
                    </div>
                </div>
                <div className="col-2 cols" style={{borderLeft: 0}}></div>
            </div>
            <div>
                {renderRooms()}
            </div>
        </div>
    )
}
export default BookingRooms;