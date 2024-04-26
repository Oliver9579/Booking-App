import React from "react";
import "./AccountVerified.css"
import {useLocation} from "react-router-dom";
import axios from "axios";

const AccountVerified = () => {

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const token = searchParams.get('token');

    const url = 'http://localhost:3000/verify';
    axios.get(url, {
        headers: {
            'Authorization': `Bearer ${token}`,
        },
        params: {
            token: token
        }
    })
        .catch((error) => {
            alert(error.message);
        });

    return (
        <div className="account-verified">
            <img src={require(`./img/account_verified.png`)} alt="account verified"
                 style={{
                     width: '10%'
                 }}/>
            <p style={{fontSize: '20px'}}>Your account has been verified</p>
        </div>
    );

}
export default AccountVerified