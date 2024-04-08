import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Navbar from "../NavBarComponent/Navbar";
import CarsSearchBar from "../CarComponent/CarSearchBar";
import CarsList from "./CarsList";

const Cars = () => {
    const [cars, setCars] = useState([]);
    const [isAll, setIsAll] = useState(true);
    const [searchData, setSearchData] = useState([]);
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = () => {
        const token = localStorage.getItem("token");
        axios.get('http://localhost:3000/api/cars', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                setCars(response.data);
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

    const searchCars = (searchData) => {
        const token = localStorage.getItem("token");
        let url = 'http://localhost:3000/api/cars/dropOff';
        searchData.pickUpDate = searchData.pickUpDate + " 10:00:00";
        searchData.dropOffDate = searchData.dropOffDate + " 10:00:00";
        setSearchData(searchData);
        if (searchData.dropOffType === 'same') {
            url += '/same';
        } else {
            url += '/different';
        }

        axios.get(url, {
            headers: {
                'Authorization': `Bearer ${token}`,
            },
            params: searchData
        })
            .then((response) => {
                setErrorMessage('')
                setCars(response.data.cars);
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
            <div style={{marginBottom: '5%'}} className="blur-include">
                <Navbar/>
            </div>
            <div>
                <div className="form-outline mb-4 mw-100 blur-include">
                    <CarsSearchBar onSearch={searchCars} cars={cars}/>
                </div>
                {errorMessage === '' ? (
                    <div>
                        <CarsList cars={cars} isAll={isAll} searchData={searchData}/>
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
export default Cars;