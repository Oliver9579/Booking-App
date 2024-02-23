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


INSERT INTO rooms (room_type, capacity, price_per_night, hotel_id)
VALUES
    ('SINGLE', 1, 100, 1),
    ('DOUBLE', 2, 150, 1),
    ('FAMILY', 4, 200, 1),
    ('FAMILY', 4, 200, 1),
    ('TRIPLE', 4, 170, 1),
    ('TRIPLE', 4, 170, 1),
    ('SINGLE', 1, 90, 2),
    ('DOUBLE', 2, 130, 2);

INSERT INTO cars (brand, model, car_type, capacity, transmission_type, pick_up_location, drop_off_location, pick_up_date, drop_off_date, price_per_day, created_at)
VALUES
    ('Toyota', 'Camry', 'MEDIUM', 5, 'AUTOMATIC', 'New York', 'New York', '2023-11-15', '2023-11-20', 50, UNIX_TIMESTAMP()),
    ('Ford', 'Mustang', 'MEDIUM', 5, 'MANUAL', 'Chicago', 'Miami', '2023-11-20', '2023-11-25', 100, UNIX_TIMESTAMP()),
    ('Fiat', '500', 'SMALL', 4, 'AUTOMATIC', 'Los Angeles', 'Las Vegas', '2023-11-25', '2023-11-30', 75, UNIX_TIMESTAMP()),
    ('Volkswagen', 'Touran', 'LARGE', 7, 'MANUAL', 'San Francisco', 'San Francisco', '2023-12-01', '2023-12-05', 85, UNIX_TIMESTAMP());

INSERT INTO dates (date)
SELECT DATE_ADD('2024-06-01', INTERVAL (t4 + t2*10 + t1*100) DAY) AS date
FROM
    (SELECT 0 AS t1 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS tens,
    (SELECT 0 AS t2 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS hundreds,
    (SELECT 0 AS t4 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS thousands
WHERE
        DATE_ADD('2024-06-01', INTERVAL (t4 + t2*10 + t1*100) DAY) <= '2025-06-30'
ORDER BY date;
