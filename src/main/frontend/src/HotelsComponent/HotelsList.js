import React, {Component} from 'react';
import HotelCard from './HotelCard';

class HotelList extends Component {
    render() {
        const {hotels, isAll, searchData} = this.props;


        if (hotels.length === 0) {
            return (
                <div>
                    <strong style={{fontSize: '30px'}}>There is no hotel at the specified location and time!</strong>
                </div>
            );
        }
        return (
            <div>
                <h2 className="blur-include">Hotels</h2>
                {hotels.map((hotel, index) => (
                    <HotelCard key={index} hotel={hotel} isAll={isAll} searchData={searchData}/>
                ))}
            </div>
        );
    }
}

export default HotelList;