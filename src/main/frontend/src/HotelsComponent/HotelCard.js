import React, {useState} from 'react';
import "./HotelCard.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faStar} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from 'react-router-dom';
import Review from "../ReviewComponent/Review";
import "../ReviewComponent/Review.css"

const HotelCard = ({hotel, isAll, searchData}) => {

    const navigate = useNavigate();
    const [showReviewsModal, setShowReviewsModal] = useState(false);

    const toggleReviewsModal = () => {
        setShowReviewsModal(!showReviewsModal);
        let blurIncludeDivs = document.querySelectorAll(".blur-include");
        if (!showReviewsModal) {
            document.body.style.overflow = 'hidden';
            blurIncludeDivs.forEach((value) => value.classList.add('blur'));
        } else {
            blurIncludeDivs.forEach((value) => value.classList.remove('blur'));
            document.body.style.overflow = 'auto';
        }
    };

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
            <div className="hotel-card row blur-include">
                <div className="col-4" style={{padding: '0px'}}>

                    <img src={require(`./img/${hotel.img}`)} alt={hotel.name}
                         style={{maxWidth: '100%', height: 'auto'}}/>
                </div>
                <div className="col-8">
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline"><strong> {hotel.name}</strong></div>
                        <div className="p-2 d-inline">Id: {hotel.id}</div>
                        <button className="p-2 d-inline btn btn-primary reviewsButton"
                                type="submit" onClick={toggleReviewsModal}><span></span>Reviews
                        </button>
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
            <Review reviews={hotel.reviews} toggleReviewsModal={toggleReviewsModal}
                    showReviewsModal={showReviewsModal}></Review>
        </div>
    );
}

export default HotelCard;