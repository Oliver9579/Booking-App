import React, {useState, useEffect} from 'react';
import axios from 'axios';
import FlightsList from "./FlightsList";
import FlightSearchBar from "./FlightSearchBar";
import Navbar from "../NavBarComponent/Navbar";

const Flights = () => {
    const [flights, setFlights] = useState([]);
    const [flightType, setFlightType] = useState("");

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
                setFlights(response.data.flights); // Update flights state with fetched data
            })
            .catch((error) => {
                console.error('Error fetching flights:', error);
            });
    };

    const searchFlights = (searchData) => {
        const token = localStorage.getItem("token");
        let url = 'http://localhost:3000/api/flights';
        setFlightType(searchData.flightType);
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
                setFlights(response.data.flights);
            })
            .catch((error) => {
                if (error.response && error.response.status === 404) {
                    setFlights([]);
                } else {
                    alert(error.response.data.message);
                }
            });
    };

    return (
        <div style={{width: '60%'}}>
            <div style={{marginBottom: '5%'}}>
                <Navbar />
            </div>
            <div>
                <div className="form-outline mb-4 mw-100">
                    <FlightSearchBar onSearch={searchFlights}/>
                </div>
                <div>
                    <FlightsList flights={flights} flightType={flightType}/>
                </div>
            </div>
        </div>
    );
};

export default Flights;