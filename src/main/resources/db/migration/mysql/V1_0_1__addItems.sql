INSERT INTO flights (airline, origin, destination, departure_date, duration, created_at)
VALUES
    ('Delta Airlines', 'New York', 'Los Angeles', '2023-11-15 16:30:00', 360, UNIX_TIMESTAMP()),
    ('American Airlines', 'Chicago', 'Miami', '2023-11-20 11:00:00', 180, UNIX_TIMESTAMP()),
    ('United Airlines', 'San Francisco', 'Seattle', '2023-11-25 13:55:00', 120, UNIX_TIMESTAMP()),
    ('British Airways', 'London', 'Paris', '2023-12-01 08:20:00', 90, UNIX_TIMESTAMP());

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


INSERT INTO hotels (name, location, created_at)
VALUES
    ('Hilton', 'New York', UNIX_TIMESTAMP()),
    ('Marriott', 'Chicago', UNIX_TIMESTAMP()),
    ('Sheraton', 'Los Angeles', UNIX_TIMESTAMP()),
    ('Radisson', 'San Francisco', UNIX_TIMESTAMP());


INSERT INTO rooms (room_type, capacity, price_per_night, availability, hotel_id)
VALUES
    ('Single', 1, 100, TRUE, 1),
    ('Double', 2, 150, TRUE, 1),
    ('Suite', 4, 250, TRUE, 1),
    ('Single', 1, 90, TRUE, 2),
    ('Double', 2, 130, TRUE, 2),
    ('Suite', 4, 220, TRUE, 2);

INSERT INTO rental_cars (brand, model, location, pickup_date, return_date, total_price, created_at)
VALUES
    ('Toyota', 'Camry', 'New York', '2023-11-15', '2023-11-20', 300, UNIX_TIMESTAMP()),
    ('Ford', 'Mustang', 'Chicago', '2023-11-20', '2023-11-25', 400, UNIX_TIMESTAMP()),
    ('Chevrolet', 'Impala', 'Los Angeles', '2023-11-25', '2023-11-30', 350, UNIX_TIMESTAMP()),
    ('Honda', 'Accord', 'San Francisco', '2023-12-01', '2023-12-05', 380, UNIX_TIMESTAMP());
