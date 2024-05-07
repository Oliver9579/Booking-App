DELETE
FROM booking_seats;
DELETE
FROM booking_rooms;
DELETE
FROM reviews;
DELETE
FROM room_dates;
DELETE
FROM car_dates;
DELETE
FROM seats;
DELETE
FROM rooms;
DELETE
FROM verification_tokens;
DELETE
FROM bookings;
DELETE
FROM users;
DELETE
FROM dates;
DELETE
FROM cars;
DELETE
FROM flights;
DELETE
FROM hotels;

INSERT INTO users (id, first_name, last_name, username, email, password, phone_number, enabled)
VALUES (100, 'Oliver', 'Szabo-Temple', 'Oli', 'booking.2024.test@gmail.com',
        '$2a$10$/zi.piVmh7Po7QLi7bDj6.O5W./kkd4yNcI40Jx5SuqKg4LYU.5le', '123456789', 1),
       (101, 'Oliver', 'Szabo-Temple', 'Oli2', 'szabotemple.oliver2001@gmail.com',
        '$2a$10$/zi.piVmh7Po7QLi7bDj6.O5W./kkd4yNcI40Jx5SuqKg4LYU.5le', '12345678910', 1),
       (102, 'PlayerToBeVerified', 'PlayerToBeVerified', 'PlayerToBeVerified', 'unverified@gmail.com',
        '$2a$10$/zi.piVmh7Po7QLi7bDj6.O5W./kkd4yNcI40Jx5SuqKg4LYU.5le', '12345678911', 0),
       (103, 'UnverifiedUser', 'UnverifiedUser', 'UnverifiedUser', 'email@gmail.com',
        '$2a$10$/zi.piVmh7Po7QLi7bDj6.O5W./kkd4yNcI40Jx5SuqKg4LYU.5le', '12345678912', 0),
       (105, 'deletableUser', 'deletableUser', 'deletableUser', 'deletableuser@gmail.com',
        '$2a$10$/zi.piVmh7Po7QLi7bDj6.O5W./kkd4yNcI40Jx5SuqKg4LYU.5le', '12345678913', 1);

INSERT INTO verification_tokens (id, token_value, created_at, user_id)
VALUES (100, '123456789',1231237312, 102);


INSERT INTO flights (id, airline, origin, origin_airport_code, destination, destination_airport_code, departure_date,
                     duration, flight_number, flight_type, img, created_at)
VALUES (1, 'Delta Airlines', 'New York', 'JFK', 'Los Angeles', 'LAX', '2024-06-15 16:30:00', 360, 'D3843',
        'Airbus A310',
        'delta.png', 1231237312),
       (2, 'American Airlines', 'Chicago', 'ORD', 'Miami', 'MIA', '2024-06-20 00:00:00', 180, 'A4902',
        'Embraer ERJ 135',
        'american.png', 1231237312),
       (3, 'American Airlines', 'Miami', 'MIA', 'Chicago', 'ORD', '2024-06-28 20:00:00', 185, 'A2104',
        'Embraer ERJ 145',
        'american.png', 1231237312);

INSERT INTO seats (id, seat_number, seat_type, price, availability, flight_id)
VALUES (1, '1', 'BUSINESS', 420, 1, 1),
       (2, '2', 'ECONOMY', 60, 1, 1),
       (3, '3', 'FIRST_CLASS', 300, 1, 1),
       (4, '4', 'BUSINESS', 420, 1, 1),
       (5, '5', 'ECONOMY', 60, 1, 1),
       (6, '6', 'FIRST_CLASS', 300, 1, 1),

       (7, '7', 'BUSINESS', 210, 1, 2),
       (8, '8', 'ECONOMY', 30, 1, 2),
       (9, '9', 'FIRST_CLASS', 150, 1, 2),
       (10, '10', 'BUSINESS', 210, 1, 2),
       (11, '11', 'ECONOMY', 30, 1, 2),
       (12, '12', 'FIRST_CLASS', 150, 1, 2),

       (14, '14', 'BUSINESS', 216, 1, 3),
       (15, '15', 'ECONOMY', 31, 1, 3),
       (16, '16', 'FIRST_CLASS', 154, 1, 3),
       (17, '17', 'BUSINESS', 216, 1, 3),
       (18, '18', 'ECONOMY', 31, 1, 3),
       (19, '19', 'FIRST_CLASS', 154, 1, 3),

       (20, '20', 'ECONOMY', 60, 0, 1),
       (21, '21', 'ECONOMY', 30, 0, 2),
       (22, '22', 'ECONOMY', 31, 0, 3);

INSERT INTO cars (id, brand, model, car_type, capacity, transmission_type, pick_up_location, drop_off_location,
                  price_per_day, img, created_at)
VALUES (1, 'Toyota', 'Camry', 'MEDIUM', 5, 'AUTOMATIC', 'New York', 'New York', 50, 'camry.png', 1231237312),
       (2, 'Ford', 'Mustang', 'MEDIUM', 5, 'MANUAL', 'Chicago', 'Miami', 100, 'mustang.png', 1231237312),
       (3, 'Fiat', '500', 'SMALL', 4, 'AUTOMATIC', 'Los Angeles', 'Las Vegas', 75, 'fiat500.png', 1231237312),
       (4, 'Volkswagen', 'Touran', 'LARGE', 7, 'MANUAL', 'San Francisco', 'San Francisco', 85, 'touran.png',
        1231237312);

INSERT INTO hotels (id, name, location, street, stars, img, created_at)
VALUES (1, 'Hilton', 'New York', '1234 Avenue of the Americas', 5, 'hilton.png', 1231237312),
       (2, 'Marriott', 'Chicago', '321 North Clark Street', 5, 'mariott.png', 1231237312),
       (3, 'Sheraton', 'Los Angeles', '711 South Hope Street', 4, 'sheraton.png', 1231237312),
       (4, 'Radisson', 'San Francisco', '1015 Market Street', 3, 'radisson.png', 1231237312);

INSERT INTO rooms (id, room_type, capacity, price_per_night, hotel_id)
VALUES (1, 'SINGLE', 1, 100, 1),
       (2, 'DOUBLE', 2, 150, 1),
       (3, 'TRIPLE', 3, 200, 1),
       (4, 'FAMILY', 4, 250, 1),

       (5, 'SINGLE', 1, 100, 2),
       (6, 'DOUBLE', 2, 150, 2),
       (7, 'TRIPLE', 3, 200, 2),
       (8, 'FAMILY', 4, 250, 2);

INSERT INTO dates (id, date)
VALUES (1, DATE '2024-06-01'),
       (2, DATE '2024-06-02'),
       (3, DATE '2024-06-03'),
       (4, DATE '2024-06-04'),
       (5, DATE '2024-06-05'),
       (6, DATE '2024-06-06'),
       (7, DATE '2024-06-07'),
       (8, DATE '2024-06-08'),
       (9, DATE '2024-06-09'),
       (10, DATE '2024-06-10'),
       (11, DATE '2024-06-11'),
       (12, DATE '2024-06-12'),
       (13, DATE '2024-06-13'),
       (14, DATE '2024-06-14'),
       (15, DATE '2024-06-15'),
       (16, DATE '2024-06-16'),
       (17, DATE '2024-06-17'),
       (18, DATE '2024-06-18'),
       (19, DATE '2024-06-19'),
       (20, DATE '2024-06-20'),
       (21, DATE '2024-06-21'),
       (22, DATE '2024-06-22'),
       (23, DATE '2024-06-23'),
       (24, DATE '2024-06-24'),
       (25, DATE '2024-06-25'),
       (26, DATE '2024-06-26'),
       (27, DATE '2024-06-27'),
       (28, DATE '2024-06-28'),
       (29, DATE '2024-06-29');



INSERT INTO reviews (id, comment, review_date, user_id, flight_id, hotel_id, car_id)
VALUES (100, 'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard
        dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type
        specimen book.',
        '2024-05-06 14:01:51', 100, NULL, NULL, 1),
       (101, 'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard
        dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type
        specimen book.',
        '2024-05-06 14:01:51', 100, NULL, 1, null),
       (102, 'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard
        dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type
        specimen book.',
        '2024-05-06 14:01:51', 100, 1, NULL, null);


INSERT INTO bookings (id, booking_date, start_date, end_date, total_price, user_id, car_id, outbound_flight_id,
                      return_flight_id, hotel_id)
VALUES (100, '2024-05-06 14:04:46', '2024-06-15 16:30:00', NULL, 420, 100, NULL, 1, NULL, null),
       (101, '2024-05-06 14:04:46', '2024-06-20 00:00:00', '2024-06-28 20:00:00', 426, 100, NULL, 2, 3, null),
       (102, '2024-05-06 14:04:46', '2024-04-14 14:00:00', '2024-06-16 10:00:00', 500, 100, null, null, NULL, 1),
       (103, '2024-05-06 14:04:46', '2024-04-14 10:00:00', '2024-06-16 10:00:00', 150, 100, 1, null, NULL, null);

INSERT INTO booking_rooms (booking_id, room_id)
VALUES (102, 1),
       (102, 2);

INSERT INTO room_dates (room_id, date_id)
VALUES (1, 14),
       (1, 15),
       (1, 16),
       (2, 14),
       (3, 15),
       (3, 16);

INSERT INTO booking_seats (booking_id, seat_id)
VALUES (100, 20),
       (101, 21),
       (102, 22);

INSERT INTO car_dates (car_id, date_id)
VALUES (1, 14),
       (1, 15),
       (1, 16),
       (2, 14),
       (2, 15),
       (2, 16);
