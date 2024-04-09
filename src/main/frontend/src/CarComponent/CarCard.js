import React, {useState} from 'react';
import "./CarCard.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPerson, faSnowflake, faEuroSign} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import Review from "../ReviewComponent/Review";

const CarCard = ({car, isAll, searchData}) => {

    const navigate = useNavigate();
    const [showReviewsModal, setShowReviewsModal] = useState(false);

    const [showTransmissionDescription, setShowTransmissionDescription] = useState(false);
    const [showPassengers, setShowPassengers] = useState(false);
    const [showAirConditioning, setShowAirConditioning] = useState(false);

    const [bookingSuccess, setBookingSuccess] = useState(false);

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

    const handleBooking = async () => {
        const requestBody = {
            startDate: searchData.pickUpDate,
            totalPrice: car.fullPrice,
            endDate: searchData.dropOffDate,
            carId: car.id,
        };

        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            };
            const response = await axios.post(
                'http://localhost:3000/api/bookings/cars',
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
        navigate('/booking/success');
    }


    return (
        <div>
            <div className={`car-card row blur-include`}>
                <div className="col-4" style={{padding: '0px'}}>
                    <img src={require(`./img/${car.img}`)} alt={`${car.brand} ${car.model}`}
                         style={{
                             width: '80%',
                             display: "block",
                             marginLeft: "auto",
                             marginRight: "auto",
                             paddingTop: '10%',
                             paddingBottom: '10%'
                         }}/>
                </div>
                <div className="col-6">
                    <div style={{textAlign: "left"}}>
                        <div className="p-2 d-inline"><strong
                            style={{fontSize: '150%'}}> {car.brand} {car.model}</strong></div>
                        <div className="p-2 d-inline">Id: {car.id}</div>
                        <button className="p-2 d-inline btn btn-primary reviewsButton"
                                type="submit" onClick={toggleReviewsModal}><span></span>Reviews
                        </button>
                    </div>
                    <div style={{textAlign: "left", padding: '8px'}}>
                        {showTransmissionDescription && (
                            <div className="transmission-type-describe">
                                <p>{car.transmissionType === 'AUTOMATIC' ? 'Automatic' : 'Manual'} transmission</p>
                            </div>
                        )}
                        <div className="d-inline col-1 describable"
                             onMouseEnter={() => setShowTransmissionDescription(true)}
                             onMouseLeave={() => setShowTransmissionDescription(false)}>
                            <img className="d-inline" style={{width: "18px", height: "auto"}}
                                 src={require("./img/transmission.png")}
                                 alt="transmissin"/>
                            <p className="d-inline"
                               style={{maxWidth: '5%', fontWeight: "bold"}}> {car.transmissionType.substring(0, 1)} </p>
                        </div>

                        {showPassengers && (
                            <div className="passengers-describe">
                                <p>{car.capacity} adult passengers</p>
                            </div>
                        )}
                        <div className="d-inline col-1 describable"
                             onMouseEnter={() => setShowPassengers(true)} onMouseLeave={() => setShowPassengers(false)}>
                            <div className="d-inline" style={{maxWidth: '5%'}}><FontAwesomeIcon icon={faPerson}/></div>
                            <p className="d-inline"
                               style={{maxWidth: '5%', fontWeight: "bold"}}> {car.capacity} </p>
                        </div>

                        {showAirConditioning && (
                            <div className="air-conditioning-describe">
                                <p>Air conditioning available</p>
                            </div>
                        )}
                        <div className="d-inline col-1 describable"
                             onMouseEnter={() => setShowAirConditioning(true)}
                             onMouseLeave={() => setShowAirConditioning(false)}>
                            <div className="d-inline" style={{maxWidth: '5%'}}><FontAwesomeIcon icon={faSnowflake}/>
                            </div>
                            <p className="d-inline"
                               style={{maxWidth: '5%', fontWeight: "bold"}}> A/C </p>
                        </div>

                        <div style={{width: '100%', textAlign: "center", paddingTop: '20px'}}>
                            <div className="row">
                                <div className="p-2 col-6" style={{padding: 0}}><strong>pick up</strong></div>
                                <div className="p-2 col-6" style={{padding: 0}}><strong>drop off</strong></div>
                                <div className="p-2 col-6" style={{padding: 0}}>{car.pickUpLocation} Airport</div>
                                <div className="p-2 col-6" style={{padding: 0}}>{car.dropOffLocation} Airport</div>
                                {car.pickUpDate && (
                                    <div className="p-2 col-6" style={{padding: 0}}>{car.pickUpDate}</div>
                                )}
                                {car.dropOffDate && (
                                    <div className="p-2 col-6" style={{padding: 0}}>{car.dropOffDate}</div>
                                )}
                            </div>
                        </div>
                        {!isAll ? (
                            <div style={{textAlign: "center"}}><strong>Full price: {car.fullPrice}</strong>
                                <FontAwesomeIcon icon={faEuroSign}/></div>
                        ) : (
                            <div style={{textAlign: "center"}}><strong>Price per day: {car.pricePerDay}</strong>
                                <FontAwesomeIcon icon={faEuroSign}/></div>
                        )}
                    </div>

                </div>
                {!isAll && (
                    <div className="col-2 d-flex align-items-end justify-content-end">
                        <button className="btn btn-primary" type="submit" onClick={handleBooking}><span></span>Book
                        </button>
                    </div>
                )}
            </div>
            <br/>
            <Review reviews={car.reviews} toggleReviewsModal={toggleReviewsModal}
                    showReviewsModal={showReviewsModal}></Review>
        </div>
    );

}
export default CarCard;