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

    const {hotel, searchData, facilities} = location.state || {};

    const [bookingSuccess, setBookingSuccess] = useState(false);

    const [selectedFacilities, setSelectedFacilities] = useState([]);

    const [selectedRoomTypes, setSelectedRoomTypes] = useState([]);

    const renderRooms = (rooms) => {

        const totalPriceSum = Object.values(roomPrices).reduce((acc, price) => acc + price, 0);
        const totalRoomCount = Object.values(roomCounts).reduce((acc, cnt) => acc + cnt, 0);

        return rooms.map((room, index) => (
            <div className="row" key={index}>
                <Room room={room} searchData={searchData} onRoomCountChange={handleRoomCountChange}
                      facilities={facilities}/>
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

    const filterRooms = () => {
        if (filterRoomsByFacilitiesCheckBoxes().length === 0 && filterRoomsByRoomTypeCheckBoxes().length === 0) {
            return hotel.rooms;
        } else if (!(filterRoomsByFacilitiesCheckBoxes().length === 0) && filterRoomsByRoomTypeCheckBoxes().length === 0) {
            return filterRoomsByFacilitiesCheckBoxes();
        } else if (filterRoomsByFacilitiesCheckBoxes().length === 0 && !(filterRoomsByRoomTypeCheckBoxes().length === 0)) {
            return filterRoomsByRoomTypeCheckBoxes();
        } else {
            return filterRoomsByFacilitiesCheckBoxes().filter(value => filterRoomsByRoomTypeCheckBoxes().includes(value));
        }

    };

    const filterRoomsByFacilitiesCheckBoxes = () => {
        const filteredRooms = [];
        const rooms = [];
        if (selectedFacilities.length === 0) {
            return hotel.rooms;
        } else {
            Object.entries(facilities).map(([roomType, allFacilities]) => {
                if (!(selectedFacilities.length === 0) && selectedFacilities.every(selectedFacility => allFacilities.includes(selectedFacility))) {
                    filteredRooms.push(roomType);
                }
            });
        }
        hotel.rooms.map(room => {
            if (filteredRooms.includes(room.roomType)) {
                rooms.push(room);
            }
        });
        return rooms;
    };

    const filterRoomsByRoomTypeCheckBoxes = () => {
        const filteredRooms = [];
        const rooms = [];
        if (selectedRoomTypes.length === 0) {
            return hotel.rooms;
        } else {
            Object.entries(facilities).map(([roomType]) => {
                if (selectedRoomTypes.includes(roomType)) {
                    filteredRooms.push(roomType);
                }
            });
        }
        hotel.rooms.map(room => {
            if (filteredRooms.includes(room.roomType)) {
                rooms.push(room);
            }
        });
        return rooms;
    };


    const handleFacilitiesCheckboxChange = (event) => {
        const {id, checked} = event.target;
        if (checked) {
            setSelectedFacilities([...selectedFacilities, id]);
        } else {
            setSelectedFacilities(selectedFacilities.filter(facility => facility !== id));
        }
    };

    const handleRoomTypesCheckboxChange = (event) => {
        const {id, checked} = event.target;
        if (checked) {
            setSelectedRoomTypes([...selectedRoomTypes, id]);
        } else {
            setSelectedRoomTypes(selectedRoomTypes.filter(roomType => roomType !== id));
        }
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
        <div style={{width: '80%'}}>
            <h2 className="blur-include" style={{paddingLeft: '15%'}}>Hotel</h2>
            <HotelCard hotel={hotel} isAll={true}/>
            <br/>
            <h4 className="blur-include" style={{textAlign: "right", paddingRight: '35%'}}>Available Rooms</h4>
            <div className="container blur-include">
                <div className="row">
                    <div className="col-2 room-filters" style={{padding: '0'}}>
                        <p style={{margin: '10%', fontWeight: "bold", fontSize: '20px'}}>Filter by:</p>
                        <hr/>

                        <div style={{fontWeight: 'bold', marginLeft: '10%'}}>Your budget</div>

                        <hr/>

                        <div style={{fontWeight: 'bold', marginLeft: '10%'}}>Facilities</div>
                        <div className="container">
                            <div className="row">
                                <div className="boxes">
                                    <input type="checkbox" id="Free WiFi"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="Free WiFi">Free Wifi</label>

                                    <input type="checkbox" id="City view"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="City view">City view</label>

                                    <input type="checkbox" id="Air conditioning"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="Air conditioning">Air conditioning</label>

                                    <input type="checkbox" id="Ensuite bathroom"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="Ensuite bathroom">Ensuite bathroom</label>

                                    <input type="checkbox" id="Flat-screen TV"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="Flat-screen TV">Flat-screen TV</label>

                                    <input type="checkbox" id="Soundproofing"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="Soundproofing">Soundproofing</label>

                                    <input type="checkbox" id="Minibar"
                                           onChange={handleFacilitiesCheckboxChange}></input>
                                    <label htmlFor="Minibar">Minibar</label>

                                </div>
                            </div>
                        </div>
                        <hr/>

                        <div style={{fontWeight: 'bold', marginLeft: '10%'}}>Room Type</div>
                        <div className="container">
                            <div className="row">
                                <div className="boxes" style={{margin: '0'}}>
                                    <input type="checkbox" id="SINGLE"
                                           onChange={handleRoomTypesCheckboxChange}></input>
                                    <label htmlFor="SINGLE">Single</label>

                                    <input type="checkbox" id="DOUBLE"
                                           onChange={handleRoomTypesCheckboxChange}></input>
                                    <label htmlFor="DOUBLE">Double</label>

                                    <input type="checkbox" id="TRIPLE"
                                           onChange={handleRoomTypesCheckboxChange}></input>
                                    <label htmlFor="TRIPLE">Triple</label>

                                    <input type="checkbox" id="FAMILY"
                                           onChange={handleRoomTypesCheckboxChange}></input>
                                    <label htmlFor="FAMILY">Family</label>
                                </div>
                            </div>
                        </div>
                        <hr/>
                    </div>
                    <div className="col-10">
                        <div className="row blur-include"
                             style={{backgroundColor: '#0096FF', color: "white", padding: 0}}>
                            <div className="col-10 cols row" style={{padding: 0, textAlign: "center"}}>
                                <div className="col-6 room-details" style={{padding: 0}}>
                                    <p style={{margin: '5%', fontWeight: "bold"}}>Room Type</p>
                                </div>
                                <div className="col-2 room-details" style={{padding: 0}}>
                                    <p style={{margin: '10%', fontWeight: "bold"}}>Number of Guests</p>
                                </div>
                                <div className="col-2 room-details" style={{padding: 0}}>
                                    <p style={{margin: '10%', fontWeight: "bold"}}>Price
                                        for {hotel.numberOfNights} nights</p>
                                </div>
                                <div className="col-2 room-details" style={{padding: 0}}>
                                    <p style={{margin: '10%', fontWeight: "bold"}}>Select amount</p>
                                </div>
                            </div>
                            <div className="col-2 cols" style={{borderLeft: 0}}></div>
                        </div>
                        <div>
                            {renderRooms(filterRooms())}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default BookingRooms;