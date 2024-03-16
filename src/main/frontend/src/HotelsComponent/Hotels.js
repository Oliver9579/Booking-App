import React, {useEffect, useState} from 'react';
import axios from 'axios';
import HotelsSearchBar from "../HotelsComponent/HotelSearchBar";
import HotelsList from "../HotelsComponent/HotelsList";
import Navbar from "../NavBarComponent/Navbar";


const Hotels = () => {
    const [hotels, setHotels] = useState([]);
    const [isAll, setIsAll] = useState(true);
    const [searchData, setSearchData] = useState([]);
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => {
        fetchHotels();
    }, []);

    const fetchHotels = () => {
        const token = localStorage.getItem("token");
        axios.get('http://localhost:3000/api/hotels/all', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                setHotels(response.data);
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

    const searchHotels = (searchData) => {
        const token = localStorage.getItem("token");
        let url = 'http://localhost:3000/api/hotels';
        setSearchData(searchData);

        axios.get(url, {
            headers: {
                'Authorization': `Bearer ${token}`,
            },
            params: searchData
        })
            .then((response) => {
                setErrorMessage('')
                setHotels(response.data.hotels);
                setIsAll(false);
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
            <div style={{marginBottom: '5%'}}>
                <Navbar/>
            </div>
            <div>
                <div className="form-outline mb-4 mw-100">
                    <HotelsSearchBar onSearch={searchHotels}/>
                </div>
                {errorMessage === '' ? (
                    <div>
                        <HotelsList hotels={hotels} isAll={isAll} searchData={searchData}/>
                    </div>
                ) : (
                    <div className="alert alert-danger">
                        <h6>{errorMessage}</h6>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Hotels;