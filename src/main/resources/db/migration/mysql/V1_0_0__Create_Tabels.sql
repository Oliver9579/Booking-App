CREATE TABLE IF NOT EXISTS users (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        phone_number VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS flights (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         airline VARCHAR(255) NOT NULL,
                         origin VARCHAR(255) NOT NULL,
                         destination VARCHAR(255) NOT NULL,
                         departure_date DATETIME NOT NULL,
                         duration INT NOT NULL,
                         created_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS seats (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       seat_number VARCHAR(10),
                       seat_type VARCHAR(50),
                       price INT NOT NULL,
                       availability BOOLEAN,
                       flight_id INT,
                       FOREIGN KEY (flight_id) REFERENCES flights(id)
);

CREATE TABLE IF NOT EXISTS hotels (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        location VARCHAR(255) NOT NULL,
                        created_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS rooms (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       room_type VARCHAR(50),
                       capacity INT,
                       price_per_night INT NOT NULL,
                       availability BOOLEAN,
                        hotel_id INT,
                       FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);


CREATE TABLE IF NOT EXISTS rental_cars (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             brand VARCHAR(255) NOT NULL,
                             model VARCHAR(255) NOT NULL,
                             location VARCHAR(255) NOT NULL,
                             pickup_date DATE NOT NULL,
                             return_date DATE NOT NULL,
                             total_price INT NOT NULL,
                             created_at BIGINT NOT NULL
    );

CREATE TABLE IF NOT EXISTS bookings (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            booking_type VARCHAR(255) NOT NULL,
                            booking_id INT NOT NULL,
                            total_price INT NOT NULL,
                            booking_date BIGINT NOT NULL,
                            user_id INT NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS reviews (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        review_type VARCHAR(255) NOT NULL,
                        review_id INT NOT NULL,
                        rating INT NOT NULL,
                        comment VARCHAR(2000),
                        review_date BIGINT NOT NULL,
                        user_id INT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);