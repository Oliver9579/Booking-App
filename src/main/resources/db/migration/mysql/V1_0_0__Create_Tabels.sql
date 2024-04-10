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
                         flight_number VARCHAR(255),
                         flight_type VARCHAR(255),
                         img VARCHAR(255),
                         created_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS hotels (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        location VARCHAR(255) NOT NULL,
                        street VARCHAR(255) NOT NULL,
                        stars INT NOT NULL,
                        img VARCHAR(255),
                        created_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             brand VARCHAR(255) NOT NULL,
                             model VARCHAR(255) NOT NULL,
                             car_type VARCHAR(50),
                             capacity INT NOT NULL,
                             transmission_type VARCHAR(50),
                             pick_up_location VARCHAR(255) NOT NULL,
                             drop_off_location VARCHAR(255) NOT NULL,
                             price_per_day INT NOT NULL,
                             img VARCHAR(255),
                             created_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            booking_date DATETIME NOT NULL,
                            start_date DATETIME NOT NULL,
                            end_date DATETIME,
                            total_price INT NOT NULL,
                            user_id INT NOT NULL,
                            car_id INT,
                            outbound_flight_id INT,
                            return_flight_id INT,
                            hotel_id INT,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (car_id) REFERENCES cars(id),
                            FOREIGN KEY (outbound_flight_id) REFERENCES flights(id),
                            FOREIGN KEY (return_flight_id) REFERENCES flights(id),
                            FOREIGN KEY (hotel_id) REFERENCES hotels(id)
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

CREATE TABLE IF NOT EXISTS rooms (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            room_type VARCHAR(50),
                            capacity INT,
                            price_per_night INT NOT NULL,
                            hotel_id INT,
                            FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);

CREATE TABLE IF NOT EXISTS reviews (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        comment VARCHAR(2000) NOT NULL ,
                        review_date DATETIME NOT NULL ,
                        user_id INT NOT NULL,
                        flight_id INT,
                        hotel_id INT,
                        car_id INT,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (flight_id) REFERENCES flights(id),
                        FOREIGN KEY (hotel_id) REFERENCES hotels(id),
                        FOREIGN KEY (car_id) REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS dates (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        date DATE NOT NULL
);

CREATE TABLE booking_seats (
                        booking_id INT,
                        seat_id INT,
                        FOREIGN KEY (booking_id) REFERENCES bookings(id),
                        FOREIGN KEY (seat_id) REFERENCES seats(id)
);

CREATE TABLE booking_rooms (
                        booking_id INT,
                        room_id INT,
                        FOREIGN KEY (booking_id) REFERENCES bookings(id),
                        FOREIGN KEY (room_id) REFERENCES rooms(id)
);

CREATE TABLE IF NOT EXISTS room_dates (
                        room_id INT,
                        date_id INT,
                        FOREIGN KEY (room_id) REFERENCES rooms(id),
                        FOREIGN KEY (date_id) REFERENCES dates(id)
);

CREATE TABLE IF NOT EXISTS car_dates (
                        car_id INT,
                        date_id INT,
                        FOREIGN KEY (car_id) REFERENCES cars(id),
                        FOREIGN KEY (date_id) REFERENCES dates(id)
);
