import React from 'react';
import {faChampagneGlasses, faCity, faEuroSign, faFan, faPerson, faShower, faTv, faVolumeXmark, faWifi} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "./BookingRooms.css";

const Room = ({room, searchData, onRoomCountChange, facilities}) => {

    function renderRoomDescription(roomType) {
        let atLeastRoomNumber = 0;
        switch (roomType) {
            case "SINGLE":
                atLeastRoomNumber = searchData.guests;
                return (
                    <div>
                        <div className="alert-success" style={{margin: '5% 5% 5%'}}>To fit {searchData.guests} guests,
                            you'll need to select {atLeastRoomNumber} of these
                        </div>
                        <p style={{margin: '5% 5% 5%'}}> 1 single bed </p>
                    </div>
                )
            case "DOUBLE":
                atLeastRoomNumber = Math.ceil(searchData.guests / 2);
                return (
                    <div>
                        <div className="alert-success" style={{margin: '5% 5% 5%'}}>To fit {searchData.guests} guests,
                            you'll need to select {atLeastRoomNumber} of these
                        </div>
                        <p style={{margin: '5% 5% 5%'}}> 1 twin bed </p>
                    </div>
                )
            case "FAMILY":
                atLeastRoomNumber = Math.ceil(searchData.guests / 4);
                return (
                    <div>
                        <div className="alert-success" style={{margin: '5% 5% 5%'}}>To fit {searchData.guests} guests,
                            you'll need to select {atLeastRoomNumber} of these
                        </div>
                        <p style={{margin: '5% 5% 5%'}}> 1 twin bed and <br/> 1 bunk bed </p>
                    </div>
                )
            case "TRIPLE":
                atLeastRoomNumber = Math.ceil(searchData.guests / 3);
                return (
                    <div>
                        <div className="alert-success" style={{margin: '5% 5% 5%'}}>To fit {searchData.guests} guests,
                            you'll need to select {atLeastRoomNumber} of these
                        </div>
                        <p style={{margin: '5% 5% 5%'}}> 1 twin bed and <br/> 1 single bed </p>
                    </div>
                )
            default:
                return <div></div>
        }
    }

    function renderFacilities(roomType, facilities) {
        const roomFacilities = facilities[roomType];

        return (
            <div className="row">
                {roomFacilities.map((facility, index) => (
                    <div className="col-6" key={index} style={{paddingRight: '5px'}}>
                        <p style={{margin: '5% 5% 5%'}}>
                            {facility} {renderFacilityIcon(facility)}
                        </p>
                    </div>
                ))}
            </div>
        );
    }

    function renderFacilityIcon(facility) {
        switch (facility) {
            case "Free WiFi":
                return <FontAwesomeIcon icon={faWifi}/>;
            case "City View":
                return <FontAwesomeIcon icon={faCity}/>;
            case "Air conditioning":
                return <FontAwesomeIcon icon={faFan}/>;
            case "Ensuite Bathroom":
                return <FontAwesomeIcon icon={faShower}/>;
            case "Flat-screen TV":
                return <FontAwesomeIcon icon={faTv}/>;
            case "Soundproofing":
                return <FontAwesomeIcon icon={faVolumeXmark}/>;
            case "Minibar":
                return <FontAwesomeIcon icon={faChampagneGlasses}/>;
            default:
                return null;
        }
    }

    const availableOptions = Array.from({length: room.availableNumber + 1}, (_, i) => i);
    return (
        <div className="col-10 cols row blur-include" style={{padding: 0, textAlign: "center"}}>
            <div className="col-6 room-data" style={{textAlign: "left", padding: 0}}>
                <p style={{margin: '5% 5% 5% 5%'}}> {room.roomType} </p>
                {renderRoomDescription(room.roomType)}
                {renderFacilities(room.roomType, facilities)}
            </div>
            <div className="col-2 room-data" style={{padding: 0}}>
                <p style={{margin: '10%'}}><FontAwesomeIcon icon={faPerson}/> X {room.capacity}</p>
            </div>
            <div className="col-2 room-data">
                <p style={{margin: '10%'}}> {room.fullPrice} <FontAwesomeIcon icon={faEuroSign}/></p>
            </div>
            <div className="col-2 room-data" style={{padding: 0}}>
                <select style={{margin: '10%', width: '48px'}}
                        onChange={(e) => {
                            const [count, totalPrice] = e.target.value.split(':').map(value => parseInt(value));
                            onRoomCountChange(room.roomType, count, totalPrice);
                        }}>
                    {availableOptions.map((option) => {
                        if (option === 0) {
                            return (
                                <option key={option} value={option}>
                                    {option}
                                </option>
                            );
                        }
                        const totalPrice = option * room.fullPrice;
                        return (
                            <option key={option} value={`${option}:${totalPrice}`}>
                                {option}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(EUR {totalPrice})
                            </option>
                        );
                    })}
                </select>
            </div>
        </div>
    )
}
export default Room;