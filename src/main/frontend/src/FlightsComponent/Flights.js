import React, {useState, useEffect} from 'react';
import axios from 'axios';
import FlightsList from "./FlightsList";
import FlightSearchBar from "./FlightSearchBar";
import Navbar from "../NavBarComponent/Navbar";

const Flights = () => {
    const [flights, setFlights] = useState([]);
    const [searchData, setSearchData] = useState({});
    const [isAll, setIsAll] = useState(true);
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => {
        fetchFlights();
    }, []);

    const fetchFlights = () => {
        const token = localStorage.getItem("token");
        axios.get('http://localhost:3000/api/flights', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                setFlights(response.data.flights);
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

    const searchFlights = (searchData) => {
        const token = localStorage.getItem("token");
        let url = 'http://localhost:3000/api/flights';
        setSearchData(searchData);
        if (searchData.flightType === 'oneWay') {
            url += '/oneWay';
        } else {
            url += '/return';
        }

        axios.get(url, {
            headers: {
                'Authorization': `Bearer ${token}`,
            },
            params: searchData
        })
            .then((response) => {
                setErrorMessage('')
                setFlights(response.data.flights);
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
                    <FlightSearchBar onSearch={searchFlights}/>
                </div>
                {errorMessage === '' ? (
                    <div>
                        <FlightsList flights={flights} searchData={searchData} isAll={isAll}/>
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

export default Flights;