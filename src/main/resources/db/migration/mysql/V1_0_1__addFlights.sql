INSERT INTO flights (airline, origin, destination, departure_date, duration, created_at)
VALUES ('Delta Airlines', 'New York', 'Los Angeles', '2024-06-15 16:30:00', 360, UNIX_TIMESTAMP()),
       ('American Airlines', 'Chicago', 'Miami', '2024-06-20 00:00:00', 180, UNIX_TIMESTAMP()),
       ('United Airlines', 'San Francisco', 'Seattle', '2024-06-25 13:55:00', 120, UNIX_TIMESTAMP()),
       ('British Airways', 'London', 'Paris', '2024-07-01 08:20:00', 90, UNIX_TIMESTAMP()),
       ('American Airlines', 'Miami', 'Chicago', '2024-06-28 20:00:00', 185, UNIX_TIMESTAMP()),
       ('American Airlines', 'Miami', 'Chicago', '2024-06-28 11:30:00', 185, UNIX_TIMESTAMP()),
       ('Delta Airlines', 'Los Angeles', 'New York', '2024-06-16 14:00:00', 360, UNIX_TIMESTAMP()),
       ('American Airlines', 'Miami', 'New York', '2024-06-21 12:30:00', 160, UNIX_TIMESTAMP()),
       ('United Airlines', 'Seattle', 'San Francisco', '2024-06-26 09:45:00', 120, UNIX_TIMESTAMP()),
       ('British Airways', 'Paris', 'London', '2024-07-02 10:45:00', 90, UNIX_TIMESTAMP()),
       ('American Airlines', 'Chicago', 'Miami', '2024-06-29 08:00:00', 185, UNIX_TIMESTAMP()),
       ('American Airlines', 'Chicago', 'Miami', '2024-06-29 19:30:00', 185, UNIX_TIMESTAMP()),
       ('Delta Airlines', 'Los Angeles', 'Chicago', '2024-06-16 10:00:00', 240, UNIX_TIMESTAMP()),
       ('American Airlines', 'Miami', 'Dallas', '2024-06-21 15:45:00', 180, UNIX_TIMESTAMP()),
       ('United Airlines', 'Seattle', 'Denver', '2024-06-26 17:20:00', 150, UNIX_TIMESTAMP()),
       ('British Airways', 'Paris', 'Rome', '2024-07-02 13:30:00', 120, UNIX_TIMESTAMP()),
       ('American Airlines', 'Chicago', 'Houston', '2024-06-29 08:30:00', 200, UNIX_TIMESTAMP()),
       ('Delta Airlines', 'Chicago', 'Los Angeles', '2024-06-17 14:00:00', 240, UNIX_TIMESTAMP()),
       ('American Airlines', 'New York', 'Miami', '2024-06-22 12:30:00', 160, UNIX_TIMESTAMP()),
       ('United Airlines', 'San Francisco', 'Seattle', '2024-06-27 09:45:00', 120, UNIX_TIMESTAMP()),
       ('British Airways', 'Rome', 'Paris', '2024-07-03 10:45:00', 90, UNIX_TIMESTAMP()),
       ('American Airlines', 'Houston', 'Chicago', '2024-06-30 08:00:00', 185, UNIX_TIMESTAMP()),
       ('Lufthansa', 'Frankfurt', 'Berlin', '2024-06-15 09:00:00', 90, UNIX_TIMESTAMP()),
       ('KLM', 'Amsterdam', 'London', '2024-06-17 13:45:00', 75, UNIX_TIMESTAMP()),
       ('Cathay Pacific', 'Hong Kong', 'Tokyo', '2024-06-18 15:00:00', 240, UNIX_TIMESTAMP()),
       ('Singapore Airlines', 'Singapore', 'Bangkok', '2024-06-19 17:30:00', 140, UNIX_TIMESTAMP()),
       ('Emirates', 'Dubai', 'Delhi', '2024-06-20 20:00:00', 210, UNIX_TIMESTAMP()),
       ('Qantas', 'Sydney', 'Melbourne', '2024-07-10 08:00:00', 90, UNIX_TIMESTAMP()),
       ('Lufthansa', 'Frankfurt', 'Berlin', '2024-06-15 09:00:00', 90, UNIX_TIMESTAMP()),
       ('KLM', 'Amsterdam', 'London', '2024-06-17 13:45:00', 75, UNIX_TIMESTAMP()),
       ('Cathay Pacific', 'Hong Kong', 'Tokyo', '2024-06-18 15:00:00', 240, UNIX_TIMESTAMP()),
       ('Singapore Airlines', 'Singapore', 'Bangkok', '2024-06-19 17:30:00', 140, UNIX_TIMESTAMP()),
       ('Emirates', 'Dubai', 'Delhi', '2024-06-20 20:00:00', 210, UNIX_TIMESTAMP()),
       ('Qantas', 'Sydney', 'Melbourne', '2024-07-10 08:00:00', 90, UNIX_TIMESTAMP()),
       ('Turkish Airlines', 'Istanbul', 'Ankara', '2024-06-21 11:00:00', 80, UNIX_TIMESTAMP()),
       ('Aeroflot', 'Moscow', 'Saint Petersburg', '2024-06-22 14:30:00', 90, UNIX_TIMESTAMP()),
       ('Swiss International Air Lines', 'Zurich', 'Geneva', '2024-06-23 16:00:00', 65, UNIX_TIMESTAMP()),
       ('Qatar Airways', 'Doha', 'Bangkok', '2024-06-24 18:30:00', 420, UNIX_TIMESTAMP()),
       ('Etihad Airways', 'Abu Dhabi', 'Manila', '2024-06-25 21:00:00', 510, UNIX_TIMESTAMP()),
       ('All Nippon Airways', 'Tokyo', 'Osaka', '2024-06-26 09:30:00', 70, UNIX_TIMESTAMP()),
       ('British Airways', 'London', 'Edinburgh', '2024-06-27 12:00:00', 90, UNIX_TIMESTAMP()),
       ('Finnair', 'Helsinki', 'Rovaniemi', '2024-06-28 13:30:00', 90, UNIX_TIMESTAMP()),
       ('Iberia', 'Madrid', 'Barcelona', '2024-06-29 15:00:00', 85, UNIX_TIMESTAMP()),
       ('Alitalia', 'Rome', 'Milan', '2024-06-30 16:30:00', 70, UNIX_TIMESTAMP()),
       ('Austrian Airlines', 'Vienna', 'Salzburg', '2024-06-30 18:00:00', 65, UNIX_TIMESTAMP()),
       ('Brussels Airlines', 'Brussels', 'Berlin', '2024-07-01 19:30:00', 100, UNIX_TIMESTAMP()),
       ('SAS', 'Stockholm', 'Copenhagen', '2024-07-02 08:00:00', 80, UNIX_TIMESTAMP()),
       ('LOT Polish Airlines', 'Warsaw', 'Krakow', '2024-07-03 09:30:00', 70, UNIX_TIMESTAMP()),
       ('Norwegian Air Shuttle', 'Oslo', 'Trondheim', '2024-07-05 12:30:00', 75, UNIX_TIMESTAMP()),
       ('Aegean Airlines', 'Athens', 'Thessaloniki', '2024-07-07 15:30:00', 80, UNIX_TIMESTAMP()),
       ('Garuda Indonesia', 'Jakarta', 'Bali', '2024-07-08 17:00:00', 110, UNIX_TIMESTAMP()),
       ('Korean Air', 'Seoul', 'Jeju', '2024-07-09 18:30:00', 80, UNIX_TIMESTAMP()),
       ('China Eastern Airlines', 'Shanghai', 'Beijing', '2024-07-10 20:00:00', 150, UNIX_TIMESTAMP()),
       ('Malaysia Airlines', 'Kuala Lumpur', 'Penang', '2024-07-13 10:30:00', 60, UNIX_TIMESTAMP()),
       ('Vietnam Airlines', 'Hanoi', 'Ho Chi Minh City', '2024-07-14 12:00:00', 120, UNIX_TIMESTAMP()),
       ('Philippine Airlines', 'Manila', 'Cebu', '2024-07-15 13:30:00', 110, UNIX_TIMESTAMP()),
       ('Thai Airways', 'Bangkok', 'Chiang Mai', '2024-07-16 15:00:00', 90, UNIX_TIMESTAMP()),
       ('Japan Airlines', 'Tokyo', 'Fukuoka', '2024-07-17 16:30:00', 130, UNIX_TIMESTAMP()),
       ('Asiana Airlines', 'Seoul', 'Busan', '2024-07-18 18:00:00', 85, UNIX_TIMESTAMP()),
       ('EVA Air', 'Taipei', 'Kaohsiung', '2024-07-19 19:30:00', 70, UNIX_TIMESTAMP()),
       ('S7 Airlines', 'Moscow', 'Novosibirsk', '2024-07-21 09:30:00', 240, UNIX_TIMESTAMP()),
       ('Pegasus Airlines', 'Istanbul', 'Izmir', '2024-07-22 11:00:00', 90, UNIX_TIMESTAMP()),
       ('Aeroflot', 'Moscow', 'Sochi', '2024-07-23 12:30:00', 150, UNIX_TIMESTAMP()),
       ('Uzbekistan Airways', 'Tashkent', 'Samarkand', '2024-07-24 14:00:00', 70, UNIX_TIMESTAMP()),
       ('Oman Air', 'Muscat', 'Salalah', '2024-07-26 17:00:00', 100, UNIX_TIMESTAMP()),
       ('Gulf Air', 'Bahrain', 'Doha', '2024-07-27 18:30:00', 70, UNIX_TIMESTAMP()),
       ('Royal Jordanian', 'Amman', 'Aqaba', '2024-07-28 20:00:00', 60, UNIX_TIMESTAMP()),
       ('Middle East Airlines', 'Beirut', 'Cairo', '2024-07-29 21:30:00', 120, UNIX_TIMESTAMP()),
       ('Saudi Arabian Airlines', 'Riyadh', 'Jeddah', '2024-07-30 23:00:00', 120, UNIX_TIMESTAMP()),
       ('El Al Israel Airlines', 'Tel Aviv', 'Eilat', '2024-08-01 10:30:00', 70, UNIX_TIMESTAMP()),
       ('Azerbaijan Airlines', 'Baku', 'Ganja', '2024-08-02 12:00:00', 80, UNIX_TIMESTAMP()),
       ('Georgian Airways', 'Tbilisi', 'Batumi', '2024-08-03 13:30:00', 70, UNIX_TIMESTAMP()),
       ('Armenia Aircompany', 'Yerevan', 'Shirak', '2024-08-04 15:00:00', 60, UNIX_TIMESTAMP()),
       ('Belavia', 'Minsk', 'Gomel', '2024-08-05 16:30:00', 70, UNIX_TIMESTAMP()),
       ('Ukraine International Airlines', 'Kyiv', 'Lviv', '2024-08-06 18:00:00', 90, UNIX_TIMESTAMP()),
       ('Air France', 'Paris', 'Nice', '2024-06-16 10:30:00', 110, UNIX_TIMESTAMP()),
       ('Air France', 'Paris', 'Nice', '2024-06-16 10:30:00', 110, UNIX_TIMESTAMP()),
       ('Air Europa', 'Madrid', 'Lisbon', '2024-07-04 11:00:00', 90, UNIX_TIMESTAMP()),
       ('Air Baltic', 'Riga', 'Tallinn', '2024-07-06 14:00:00', 60, UNIX_TIMESTAMP()),
       ('Air India', 'Delhi', 'Mumbai', '2024-07-12 23:00:00', 150, UNIX_TIMESTAMP()),
       ('Air Astana', 'Nur-Sultan', 'Almaty', '2024-07-20 08:00:00', 100, UNIX_TIMESTAMP()),
       ('Air Moldova', 'Chisinau', 'Bucharest', '2024-08-07 19:30:00', 80, UNIX_TIMESTAMP());
;

DELIMITER ;;
DROP FUNCTION IF EXISTS generateSeats ;;
CREATE PROCEDURE generateSeats()
BEGIN
    DECLARE seatNumber INT default 1;
    DECLARE seatType varchar(20);
    DECLARE seatPrice INT;
    DECLARE availability bool default true;
    DECLARE flightId INT default 1;
    DECLARE seatCounter INT default 1;
    DECLARE flightCounter INT default 1;
    WHILE flightCounter <= 82
        DO
            SET @flightDuration = (select duration from flights where id = flightCounter);
            WHILE seatCounter <= 60
                DO
                    if seatCounter < 7 then
                        SET @seatType = 'BUSINESS';
                        SET @seatPrice = (@flightDuration / 60) * 100;
                        SET @seatNumber = seatCounter;
                        SET @availability = true;
                        SET @flightId = flightCounter;
                        INSERT INTO seats (seat_number, seat_type, price, availability, flight_id)
                        VALUES (@seatNumber, @seatType, @seatPrice, @availability, @flightId);
                        SET seatCounter = seatCounter + 1;
                    elseif seatCounter < 13 then
                        SET @seatType = 'FIRST_CLASS';
                        SET @seatPrice = (@flightDuration / 60) * 80;
                        SET @seatNumber = seatCounter;
                        SET @availability = true;
                        SET @flightId = flightCounter;
                        INSERT INTO seats (seat_number, seat_type, price, availability, flight_id)
                        VALUES (@seatNumber, @seatType, @seatPrice, @availability, @flightId);
                        SET seatCounter = seatCounter + 1;
                    ELSE
                        SET @seatType = 'ECONOMY';
                        SET @seatPrice = (@flightDuration / 60) * 50;
                        SET @seatNumber = seatCounter;
                        SET @availability = true;
                        SET @flightId = flightCounter;
                        INSERT INTO seats (seat_number, seat_type, price, availability, flight_id)
                        VALUES (@seatNumber, @seatType, @seatPrice, @availability, @flightId);
                        SET seatCounter = seatCounter + 1;
                    end if;
                END WHILE;
            SET seatCounter = 1;
            SET flightCounter = flightCounter + 1;
        END WHILE;
END ;;
DELIMITER ;

CALL generateSeats();

CREATE TEMPORARY TABLE flight_types_list
(
    flight_type VARCHAR(50)
);

INSERT INTO flight_types_list (flight_type)
VALUES ('Airbus A220'),
       ('Airbus A300'),
       ('Airbus A310'),
       ('Airbus A318'),
       ('Airbus A319'),
       ('Airbus A320'),
       ('Airbus A321'),
       ('Airbus A330'),
       ('Airbus A340'),
       ('Airbus A350'),
       ('Airbus A380'),
       ('Boeing 717'),
       ('Boeing 757'),
       ('Boeing 767'),
       ('Boeing 777'),
       ('Boeing 787'),
       ('Bombardier CRJ-100'),
       ('Bombardier CRJ-200'),
       ('Bombardier CRJ-700'),
       ('Bombardier CRJ-900'),
       ('Bombardier Dash 8'),
       ('Embraer ERJ 135'),
       ('Embraer ERJ 140'),
       ('Embraer ERJ 145'),
       ('Embraer 170'),
       ('Embraer 175'),
       ('Embraer 190'),
       ('Embraer 195');
;


UPDATE flights
    JOIN (
        SELECT id,
               (SELECT flight_type FROM flight_types_list ORDER BY RAND() LIMIT 1) AS random_flight_type
        FROM flights
    ) AS random_flights
    ON flights.id = random_flights.id
SET flights.flight_type = random_flights.random_flight_type;

DROP TEMPORARY TABLE IF EXISTS flight_types_list;

UPDATE flights
SET flight_number = CONCAT(SUBSTRING(airline, 1, 1), LPAD(FLOOR(RAND() * 10000), 4, '0'));

UPDATE flights
SET img = CASE
              WHEN SUBSTRING(airline, 1, 3) = 'Air'
                  THEN CONCAT(CONCAT('air', SUBSTRING(SUBSTRING_INDEX(airline, ' ', -1), 1, 1)), '.png')
              ELSE CONCAT(LOWER(SUBSTRING_INDEX(airline, ' ', 1)), '.png')
    END;

CREATE TEMPORARY TABLE IF NOT EXISTS airport_codes
(
    city         VARCHAR(100),
    airport_code VARCHAR(10)
);

INSERT INTO airport_codes (city, airport_code)
VALUES ('Los Angeles', 'LAX'),
       ('Miami', 'MIA'),
       ('Seattle', 'SEA'),
       ('Paris', 'CDG'),
       ('Chicago', 'ORD'),
       ('New York', 'JFK'),
       ('San Francisco', 'SFO'),
       ('London', 'LHR'),
       ('Dallas', 'DFW'),
       ('Denver', 'DEN'),
       ('Rome', 'FCO'),
       ('Houston', 'IAH'),
       ('Berlin', 'BER'),
       ('Tokyo', 'HND'),
       ('Tokyo', 'NRT'),
       ('Bangkok', 'BKK'),
       ('Delhi', 'DEL'),
       ('Melbourne', 'MEL'),
       ('Ankara', 'ESB'),
       ('Saint Petersburg', 'LED'),
       ('Geneva', 'GVA'),
       ('Manila', 'MNL'),
       ('Osaka', 'KIX'),
       ('Edinburgh', 'EDI'),
       ('Rovaniemi', 'RVN'),
       ('Barcelona', 'BCN'),
       ('Milan', 'MXP'),
       ('Milan', 'LIN'),
       ('Salzburg', 'SZG'),
       ('Copenhagen', 'CPH'),
       ('Krakow', 'KRK'),
       ('Trondheim', 'TRD'),
       ('Thessaloniki', 'SKG'),
       ('Bali', 'DPS'),
       ('Jeju', 'CJU'),
       ('Beijing', 'PEK'),
       ('Penang', 'PEN'),
       ('Ho Chi Minh City', 'SGN'),
       ('Cebu', 'CEB'),
       ('Chiang Mai', 'CNX'),
       ('Fukuoka', 'FUK'),
       ('Busan', 'PUS'),
       ('Kaohsiung', 'KHH'),
       ('Novosibirsk', 'OVB'),
       ('Izmir', 'ADB'),
       ('Sochi', 'AER'),
       ('Samarkand', 'SKD'),
       ('Salalah', 'SLL'),
       ('Doha', 'DOH'),
       ('Aqaba', 'AQJ'),
       ('Cairo', 'CAI'),
       ('Jeddah', 'JED'),
       ('Eilat', 'ETH'),
       ('Ganja', 'KVD'),
       ('Batumi', 'BUS'),
       ('Shirak', 'LWN'),
       ('Gomel', 'GME'),
       ('Lviv', 'LWO'),
       ('Nice', 'NCE'),
       ('Lisbon', 'LIS'),
       ('Tallinn', 'TLL'),
       ('Mumbai', 'BOM'),
       ('Almaty', 'ALA'),
       ('Bucharest', 'OTP'),
       ('Frankfurt', 'FRA'),
       ('Amsterdam', 'AMS'),
       ('Hong Kong', 'HKG'),
       ('Singapore', 'SIN'),
       ('Dubai', 'DXB'),
       ('Sydney', 'SYD'),
       ('Istanbul', 'IST'),
       ('Moscow', 'SVO'),
       ('Moscow', 'DME'),
       ('Zurich', 'ZRH'),
       ('Abu Dhabi', 'AUH'),
       ('Helsinki', 'HEL'),
       ('Madrid', 'MAD'),
       ('Vienna', 'VIE'),
       ('Brussels', 'BRU'),
       ('Stockholm', 'ARN'),
       ('Warsaw', 'WAW'),
       ('Oslo', 'OSL'),
       ('Athens', 'ATH'),
       ('Jakarta', 'CGK'),
       ('Seoul', 'ICN'),
       ('Shanghai', 'PVG'),
       ('Kuala Lumpur', 'KUL'),
       ('Hanoi', 'HAN'),
       ('Taipei', 'TPE'),
       ('Tashkent', 'TAS'),
       ('Muscat', 'MCT'),
       ('Bahrain', 'BAH'),
       ('Amman', 'AMM'),
       ('Beirut', 'BEY'),
       ('Riyadh', 'RUH'),
       ('Tel Aviv', 'TLV'),
       ('Baku', 'GYD'),
       ('Tbilisi', 'TBS'),
       ('Yerevan', 'EVN'),
       ('Minsk', 'MSQ'),
       ('Kyiv', 'KBP'),
       ('Riga', 'RIX'),
       ('Nur-Sultan', 'NQZ'),
       ('Chisinau', 'KIV');

UPDATE flights
    JOIN airport_codes ON flights.origin = airport_codes.city
SET flights.origin_airport_code = airport_codes.airport_code;

UPDATE flights
    JOIN airport_codes ON flights.destination = airport_codes.city
SET flights.destination_airport_code = airport_codes.airport_code;


DROP TEMPORARY TABLE IF EXISTS airport_codes;




