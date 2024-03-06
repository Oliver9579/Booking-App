import React, {useEffect, useState} from 'react';
import axios from 'axios';
import HotelsSearchBar from "../HotelsComponent/HotelSearchBar";
import HotelsList from "../HotelsComponent/HotelsList";


const Hotels = () => {
    const [hotels, setHotels] = useState([]);
    const [isAll, setIsAll] = useState(true);

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
            })
            .catch((error) => {
                console.error('Error fetching hotels:', error);
            });
    };

    const searchHotels = (searchData) => {
        const token = localStorage.getItem("token");
        let url = 'http://localhost:3000/api/hotels';

        axios.get(url, {
            headers: {
                'Authorization': `Bearer ${token}`,
            },
            params: searchData
        })
            .then((response) => {
                setHotels(response.data.hotels);
                setIsAll(false);
            })
            .catch((error) => {
                if (error.response && error.response.status === 404) {
                    setHotels([]);
                } else {
                    alert(error.response.data.message);
                }
            });
    };

    return (
        <div style={{width: '60%', marginTop: '5%'}}>
            <div className="form-outline mb-4 mw-100">
                <HotelsSearchBar onSearch={searchHotels}/>
            </div>
            <div>
                <HotelsList hotels={hotels} isAll={isAll}/>
            </div>
        </div>
    );
};

export default Hotels;