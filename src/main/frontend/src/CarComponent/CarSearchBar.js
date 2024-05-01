import React, {useState} from "react";
import "./CarSearchBar.css"

const CarSearchBar = ({onSearch, cars}) => {
    const [dropOffType, setDropOffType] = useState('same');
    const [pickUpLocation, setPickUpLocation] = useState('');
    const [dropOffLocation, setDropOffLocation] = useState('');
    const [pickUpDate, setPickUpDate] = useState('');
    const [dropOffDate, setDropOffDate] = useState('');
    const [carType, setCarType] = useState('');
    const [capacity, setCapacity] = useState('');
    const [transmissionType, setTransmissionType] = useState('');
    const [searched, setSearched] = useState(false);
    const handleDropOffTypeChange = (e) => {
        setDropOffType(e.target.value);
    };

    const handleCarTypeChange = (e) => {
        setCarType(e.target.value);
    };

    const handleCapacityChange = (e) => {
        setCapacity(e.target.value);
    };

    const handleTransmissionTypeChange = (e) => {
        setTransmissionType(e.target.value);
    };

    const handleResetFilters = () => {
        setCarType('');
        setCapacity('');
        setTransmissionType('');
    };

    const handleSearch = () => {
        setSearched(true);
        const searchData = {
            dropOffType,
            pickUpLocation,
            dropOffLocation,
            pickUpDate,
            dropOffDate,
            carType,
            capacity,
            transmissionType

        };

        onSearch(searchData);
    };

    return (
        <form onSubmit={(e) => {
            e.preventDefault();
            handleSearch();
        }}>
            <div className="form-group">
                <select value={dropOffType} onChange={handleDropOffTypeChange} style={{
                    width: '100%',
                    border: '1px solid #ccc',
                    margin: '0 auto',
                    padding: '8px',
                    borderRadius: '5px',
                    maxWidth: '20%'
                }}>
                    <option value="same">Same drop-off</option>
                    <option value="different">Different drop-off</option>
                </select>
            </div>
            <div className="form-inline row" style={{paddingBottom: '2%'}}>
                <div className="col-3">
                    <input className="form-control"
                           type="text"
                           placeholder="Pick Up location"
                           value={pickUpLocation}
                           onChange={(e) => setPickUpLocation(e.target.value)}
                           style={{width: '100%'}}
                           required
                    />
                </div>
                {dropOffType === 'different' && (
                    <div className="form-group col-3">
                        <input className="form-control"
                               type="text"
                               placeholder="Drop Off location"
                               value={dropOffLocation}
                               onChange={(e) => setDropOffLocation(e.target.value)}
                               style={{width: '100%'}}
                               required
                        />
                    </div>
                )}

                <div className="form-group col-2">
                    <input className="form-control"
                           type="date"
                           placeholder="Pick Up date"
                           value={pickUpDate}
                           onChange={(e) => setPickUpDate(e.target.value)}
                           min={new Date().toISOString().split('T')[0]}
                           style={{width: '100%', padding: '0px', textAlign: "center"}}
                           required
                    />
                </div>

                <div className="form-group col-2">
                    <input className="form-control"
                           type="date"
                           placeholder="Drop Off date"
                           value={dropOffDate}
                           onChange={(e) => setDropOffDate(e.target.value)}
                           min={pickUpDate}
                           style={{width: '100%', padding: '0px', textAlign: "center"}}
                           required
                    />
                </div>
                <div className="form-group col-2">
                    <button className="btn btn-primary " type="submit" style={{width: '100%'}}><span></span>Search
                    </button>
                </div>
            </div>
            {searched && (
                <div className="row">
                    <div className="col-2">
                        <label>Car type</label>
                        <select value={carType} onChange={handleCarTypeChange} style={{
                            width: '100%',
                            border: '1px solid #ccc',
                            margin: '0 auto',
                            padding: '8px',
                            borderRadius: '5px',
                            maxWidth: '100%'
                        }}>
                            <option value=""></option>
                            {Array.from(new Set(cars.map(car => car.carType))).map(carType => (
                                <option key={carType} value={carType}>
                                    {carType}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="col-2">
                        <label>Capacity</label>
                        <select value={capacity} onChange={handleCapacityChange} style={{
                            width: '100%',
                            border: '1px solid #ccc',
                            margin: '0 auto',
                            padding: '8px',
                            borderRadius: '5px',
                            maxWidth: '100%'
                        }}>
                            <option value=""></option>
                            {Array.from(new Set(cars.map(car => car.capacity))).map(capacity => (
                                <option key={capacity} value={capacity}>
                                    {capacity}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="col-2">
                        <label>Transmission</label>
                        <select value={transmissionType} onChange={handleTransmissionTypeChange} style={{
                            width: '100%',
                            border: '1px solid #ccc',
                            margin: '0 auto',
                            padding: '8px',
                            borderRadius: '5px',
                            maxWidth: '100%'
                        }}>
                            <option value=""></option>
                            {Array.from(new Set(cars.map(car => car.transmissionType))).map(transmissionType => (
                                <option key={transmissionType} value={transmissionType}>
                                    {transmissionType}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="col-2" style={{paddingTop: '33px'}}>
                        <button className="btn btn-primary reset" type="submit" style={{width: '100%'}}
                                onClick={handleResetFilters}><span></span>Reset
                        </button>
                    </div>
                </div>
            )
            }
        </form>
    )
        ;
}
export default CarSearchBar;