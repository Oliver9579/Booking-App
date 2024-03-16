import React from 'react';
import "./HotelCard.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faStar} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from 'react-router-dom';

const HotelCard = ({hotel, isAll, searchData}) => {

    const navigate = useNavigate();

    const handleSelect = () => {
        navigate('/booking/hotels', {state: {hotel, searchData}});
    };

    const generateStars = () => {
        return Array.from({length: hotel.stars}, (_, index) => (
            <FontAwesomeIcon key={index} icon={faStar}/>
        ));
    };

    return (
        <div>
            <div className="hotel-card row">
                <div className="col-4" style={{padding: '0px'}}>

                    <img src={require(`./img/${hotel.img}`)} alt={hotel.name}
                         style={{maxWidth: '100%', height: 'auto'}}/>
                </div>
                <div className="col-8">
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline"><strong> {hotel.name}</strong></div>
                        <div className="p-2 d-inline">Id: {hotel.id}</div>
                    </div>
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline">{generateStars()}</div>
                    </div>
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline">Location:</div>
                    </div>
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline">{hotel.location}</div>
                    </div>
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline">{hotel.street}</div>
                    </div>
                    {!isAll && (
                        <div style={{textAlign: "right"}}>
                            <button className="btn btn-primary " onClick={handleSelect} type="submit"><span></span>Select
                            </button>
                        </div>
                    )}
                </div>
            </div>
            <br/>
        </div>
    );
}

export default HotelCard;