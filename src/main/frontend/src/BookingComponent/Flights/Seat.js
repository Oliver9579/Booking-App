import React, {useState} from 'react';
import {faEuroSign} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Seat = ({seat, handleSeatClick}) => {
    const [isClicked, setIsClicked] = useState(false);

    const handleClick = () => {
        if (seat.availability) {
            handleSeatClick(seat.id);
            setIsClicked(!isClicked);
        }
    };

    return (
        <div
            className={`${seat.availability ? 'availableSeat' : 'unavailableSeat'} ${isClicked ? 'clicked' : ''}`}
            style={{
                cursor: seat.availability ? "pointer" : "not-allowed",
                border: "2px solid black",
                borderRadius: "5px",
                backgroundColor: isClicked ? "lightgreen" : "",
                width: "auto"
            }}
            onClick={handleClick}
        >
            <div>id: {seat.id}</div>
            <div>number: {seat.seatNumber}</div>
            <div>{seat.seatType}</div>
            <div>{seat.price} <FontAwesomeIcon icon={faEuroSign}/></div>
        </div>
    );
};

export default Seat;