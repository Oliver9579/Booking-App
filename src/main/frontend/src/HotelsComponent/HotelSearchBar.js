import React, {useState} from "react";

const HotelSearchBar = ({onSearch}) => {
    const [location, setLocation] = useState('');
    const [checkInDate, setCheckInDate] = useState('');
    const [checkOutDate, setCheckOutDate] = useState('');
    const [guests, setGuests] = useState(1);

    const handleSearch = () => {
        const searchData = {
            location,
            checkInDate,
            checkOutDate,
            guests
        };

        onSearch(searchData);
    };

    return (
        <form className="form-inline row" onSubmit={(e) => {
            e.preventDefault();
            handleSearch();
        }}>

            <div className="form-group col-2">
                <input className="form-control"
                       type="text"
                       placeholder="Location"
                       value={location}
                       onChange={(e) => setLocation(e.target.value)}
                       style={{width: '100%'}}
                />
            </div>

            <div className="form-group col-3">
                <input className="form-control"
                       type="date"
                       placeholder="Check In"
                       value={checkInDate}
                       onChange={(e) => setCheckInDate(e.target.value)}
                       min={new Date().toISOString().split('T')[0]} // Restrict to today or later
                       style={{width: '100%', padding: '0px', textAlign: "center"}}
                />
            </div>

            <div className="form-group col-3">
                <input className="form-control"
                       type="date"
                       placeholder="Check Out"
                       value={checkOutDate}
                       onChange={(e) => setCheckOutDate(e.target.value)}
                       min={new Date().toISOString().split('T')[0]} // Restrict to today or later
                       style={{width: '100%', padding: '0px', textAlign: "center"}}
                />
            </div>
            <div className="form-group col-2">
                <input className="form-control"
                       type="number"
                       placeholder="Quests"
                       value={guests}
                       onChange={(e) => setGuests(parseInt(e.target.value))}
                       style={{width: '100%'}}
                />
            </div>

            <div className="form-group col-2">
                <button className="btn btn-primary " type="submit" style={{width: '100%'}}><span></span>Search
                </button>
            </div>
        </form>
    );
};

export default HotelSearchBar;