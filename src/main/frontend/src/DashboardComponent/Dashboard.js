import React from 'react';


const Dashboard = () => {
    return (
        <div className="card-deck " style={{width: '60%', marginTop: '5%'}}>
            <div className="card">
                <img className="card-img-top" src="https://media.istockphoto.com/id/179278751/photo/jet-aeroplane-landing-from-bright-sunset-sky-blue-orange-vertical.jpg?s=612x612&w=0&k=20&c=X9b55ZfeCLUt_5uRzUuIunY4VDyC_hRLf6tMg9V70Ws=" alt="flight"></img>
                    <div className="card-body">
                        <h5 className="card-title">Flights</h5>
                        <p className="card-text">Compare and book flights with ease.
                            Discover your next dream destination</p>
                        <a href="/flights" className="btn btn-primary">Booking</a>
                    </div>
            </div>
            <div className="card">
                <img className="card-img-top" src="https://media.istockphoto.com/id/1592377744/photo/hotel-sign-on-building-facade-sunset-background.jpg?s=612x612&w=0&k=20&c=Jw41v-NFqBc8AcWdLzDekTo303gNnOed91lvdn8FcBQ=" alt="hotel"></img>
                    <div className="card-body">
                        <h5 className="card-title">Hotels</h5>
                        <p className="card-text">
                            Where to next?<br/><br/>
                            Find exclusive Genius rewards in every corner of the world!
                        </p>
                        <a href="/hotels" className="btn btn-primary">Booking</a>
                    </div>
            </div>
            <div className="card">
                <img className="card-img-top" src="https://www.shutterstock.com/image-photo/vertical-photo-headlight-modern-prestigious-600nw-1947016213.jpg" alt="car"></img>
                    <div className="card-body">
                        <h5 className="card-title">Cars</h5>
                        <p className="card-text">Car rentals for any kind of trip!</p>
                        <a href="/cars" className="btn btn-primary">Booking</a>
                    </div>
            </div>
            <div className="card">
                <img className="card-img-top" src="https://media.istockphoto.com/id/1191487541/vector/personal-info-data-icon-identification-card-icon-personal-info-data-icon-user-or-profile.jpg?s=612x612&w=0&k=20&c=Sx-EKSpX-KUcAT4bGqWvtv3xW5M1-HX84QxF8IJsY0A=" alt="profile"></img>
                <div className="card-body">
                    <h5 className="card-title">Profile</h5>
                    <p className="card-text">Manage your Booking experience</p>
                    <a href="/profile" className="btn btn-primary">See Profile</a>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;