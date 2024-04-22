import React from "react";

const Navbar = () => {

    const isActive = (path) => {
        return window.location.pathname === path;
    };

    return(
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand" href="/Dashboard" style={{maxWidth: '5%', margin: "3px"}}><img src= {require('./img/Booking.png')} alt="Booking" style={{maxWidth: '100%', height: "auto"}}/></a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className={`nav-item ${isActive('/flights') ? 'active' : ''}`}>
                        <a className="nav-link" href="/flights">Flights {isActive('/flights') && <span className="sr-only">(current)</span>}</a>
                    </li>
                    <li className={`nav-item ${isActive('/hotels') ? 'active' : ''}`}>
                        <a className="nav-link" href="/hotels">Hotels {isActive('/hotels') && <span className="sr-only">(current)</span>}</a>
                    </li>
                    <li className={`nav-item ${isActive('/cars') ? 'active' : ''}`}>
                        <a className="nav-link" href="/cars">Cars {isActive('/cars') && <span className="sr-only">(current)</span>}</a>
                    </li>
                    <li className={`nav-item ${isActive('/profile') ? 'active' : ''}`}>
                        <a className="nav-link" href="/profile">Profile {isActive('/profile') && <span className="sr-only">(current)</span>}</a>
                    </li>
                    <li className={`nav-item ${isActive('/login') ? 'active' : ''}`}>
                        <a className="nav-link" href="/login">Log Out {isActive('/login') && <span className="sr-only">(current)</span>}</a>
                    </li>
                </ul>
            </div>
        </nav>
    )
}

export default Navbar;