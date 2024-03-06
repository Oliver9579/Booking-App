import React, {Component} from 'react';
import HotelCard from './HotelCard';

class HotelList extends Component {
    render() {
        const {hotels, isAll} = this.props;


        if (hotels.length === 0) {
            return (
                <div>
                    <strong style={{fontSize: '30px'}}>There is no hotel at the specified location</strong>
                </div>
            );
        }
        return (
            <div>
                <h2>Hotels</h2>
                {hotels.map((hotel, index) => (
                    <HotelCard key={index} hotel={hotel} isAll={isAll}/>
                ))}
            </div>
        );
    }
}

export default HotelList;