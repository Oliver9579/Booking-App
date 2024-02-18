INSERT INTO flights (airline, origin, destination, departure_date, duration, created_at)
VALUES
    ('Delta Airlines', 'New York', 'Los Angeles', '2023-11-15 16:30:00', 360, UNIX_TIMESTAMP()),
    ('American Airlines', 'Chicago', 'Miami', '2023-11-20 11:00:00', 180, UNIX_TIMESTAMP()),
    ('United Airlines', 'San Francisco', 'Seattle', '2023-11-25 13:55:00', 120, UNIX_TIMESTAMP()),
    ('British Airways', 'London', 'Paris', '2023-12-01 08:20:00', 90, UNIX_TIMESTAMP()),
    ('American Airlines', 'Miami', 'Chicago', '2023-11-28 20:00:00', 185, UNIX_TIMESTAMP()),
    ('American Airlines', 'Miami', 'Chicago', '2023-11-28 11:30:00', 185, UNIX_TIMESTAMP());

INSERT INTO seats (seat_number, seat_type, price, availability, flight_id)
VALUES
    ('A1', 'ECONOMY', 100, TRUE, 1),
    ('A2', 'ECONOMY', 100, FALSE, 1),
    ('B1', 'BUSINESS', 200, TRUE, 1),
    ('B2', 'BUSINESS', 200, TRUE, 1),
    ('C1', 'ECONOMY', 120, TRUE, 2),
    ('C2', 'ECONOMY', 120, TRUE, 2),
    ('D1', 'BUSINESS', 220, TRUE, 2),
    ('D2', 'BUSINESS', 220, TRUE, 2);


INSERT INTO hotels (name, location, street, created_at)
VALUES
    ('Hilton', 'New York', '1234 Avenue of the Americas', UNIX_TIMESTAMP()),
    ('Marriott', 'Chicago', '321 North Clark Street', UNIX_TIMESTAMP()),
    ('Sheraton', 'Los Angeles', '711 South Hope Street', UNIX_TIMESTAMP()),
    ('Radisson', 'San Francisco', '1015 Market Street', UNIX_TIMESTAMP());


INSERT INTO rooms (room_type, capacity, price_per_night, unavailable, hotel_id)
VALUES
    ('SINGLE', 1, 100,'2023-11-15,2023-11-20,2023-12-10,2023-12-15', 1),
    ('DOUBLE', 2, 150, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 1),
    ('SUITE', 4, 250, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 1),
    ('FAMILY', 4, 200, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 1),
    ('FAMILY', 4, 200, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 1),
    ('SINGLE', 1, 90, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 2),
    ('DOUBLE', 2, 130, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 2),
    ('SUITE', 4, 220, '2023-11-15,2023-11-20,2023-12-10,2023-12-15', 2);

INSERT INTO rental_cars (brand, model, location, pickup_date, return_date, total_price, created_at)
VALUES
    ('Toyota', 'Camry', 'New York', '2023-11-15', '2023-11-20', 300, UNIX_TIMESTAMP()),
    ('Ford', 'Mustang', 'Chicago', '2023-11-20', '2023-11-25', 400, UNIX_TIMESTAMP()),
    ('Chevrolet', 'Impala', 'Los Angeles', '2023-11-25', '2023-11-30', 350, UNIX_TIMESTAMP()),
    ('Honda', 'Accord', 'San Francisco', '2023-12-01', '2023-12-05', 380, UNIX_TIMESTAMP());
