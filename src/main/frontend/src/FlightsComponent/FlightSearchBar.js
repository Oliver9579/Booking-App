import React, {useState} from 'react';
import "./FlightSearchBar.css"

const FlightSearchBar = ({onSearch}) => {
    const [flightType, setFlightType] = useState('oneWay'); // Default to one-way
    const [origin, setOrigin] = useState('');
    const [destination, setDestination] = useState('');
    const [departureDate, setDepartureDate] = useState('');
    const [returnDate, setReturnDate] = useState('');

    const handleFlightTypeChange = (e) => {
        setFlightType(e.target.value);
    };

    const handleSearch = () => {
        const searchData = {
            flightType,
            origin,
            destination,
            departureDate,
            returnDate
        };

        onSearch(searchData);
    };

    return (
        <form className="form-inline row" onSubmit={(e) => {
            e.preventDefault();
            handleSearch();
        }}>
            <div className="form-group col-2">
                <select value={flightType} onChange={handleFlightTypeChange} style={{
                    width: '100%', border: '1px solid #ccc', margin: '0 auto', padding: '8px', borderRadius: '5px'
                }}>
                    <option value="oneWay">One-Way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <div className="form-group col-2">
                <input className="form-control"
                       type="text"
                       placeholder="Origin"
                       value={origin}
                       onChange={(e) => setOrigin(e.target.value)}
                       style={{width: '100%'}}
                       required
                />
            </div>

            <div className="form-group col-2">
                <input className="form-control"
                       type="text"
                       placeholder="Destination"
                       value={destination}
                       onChange={(e) => setDestination(e.target.value)}
                       style={{width: '100%'}}
                       required
                />
            </div>

            <div className="form-group col-2">
                <input className="form-control"
                       type="date"
                       placeholder="Departure Date"
                       value={departureDate}
                       onChange={(e) => setDepartureDate(e.target.value)}
                       min={new Date().toISOString().split('T')[0]}
                       style={{width: '100%', padding: '0px', textAlign: "center"}}
                       required
                />
            </div>
            {flightType === 'return' && (
                <div className="form-group col-2">
                    <input className="form-control"
                           type="date"
                           placeholder="Return Date"
                           value={returnDate}
                           onChange={(e) => setReturnDate(e.target.value)}
                           min={departureDate}
                           style={{width: '100%', padding: '0px', textAlign: "center"}}
                           required
                    />
                </div>
            )}
            <div className="form-group col-2">
                <button className="btn btn-primary " type="submit" style={{width: '100%'}}><span></span>Search
                </button>
            </div>
        </form>
    );
};

export default FlightSearchBar;
