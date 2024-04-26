import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Navbar from "../NavBarComponent/Navbar";
import PersonalDetails from "./PersonalDetails";
import Bookings from "../BookingComponent/Bookings";
import "../ReviewComponent/Review.css"

const Profile = () => {

    const [user, setUser] = useState([]);
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => {
        fetchProfile();
    }, []);

    const fetchProfile = () => {
        const token = localStorage.getItem("token");
        axios.get('http://localhost:3000/api/users', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                setUser(response.data);
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
        <div style={{width: '60%'}}>
            <div style={{marginBottom: '5%'}} className="blur-include">
                <Navbar/>
            </div>
            <div>
                {errorMessage === '' ? (
                    <div>
                        <div className="blur-include" style={{paddingBottom: '5%'}}>
                            <PersonalDetails user={user}/>
                        </div>
                        <div style={{paddingBottom: '5%'}}>
                            <Bookings/>
                        </div>
                    </div>
                ) : (
                    <div className="alert alert-danger">
                        <h6>{errorMessage}</h6>
                    </div>
                )}
            </div>
        </div>
    );
}
export default Profile