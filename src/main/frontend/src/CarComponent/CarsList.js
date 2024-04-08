import React, {Component} from 'react';
import CarCard from "../CarComponent/CarCard";

class CarList extends Component {
    render() {
        const {cars, isAll, searchData} = this.props;

        return (
            <div>
                <h2 className="blur-include">Cars</h2>
                {cars.map((car, index) => (
                    <CarCard key={index} car={car} isAll={isAll} searchData={searchData}/>
                ))}
            </div>
        );
    }
}

export default CarList;