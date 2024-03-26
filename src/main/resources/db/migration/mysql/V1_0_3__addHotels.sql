INSERT INTO hotels (name, location, street, stars, img, created_at)
VALUES ('Hilton', 'New York', '1234 Avenue of the Americas', 5, 'hilton.png', UNIX_TIMESTAMP()),
       ('Marriott', 'Chicago', '321 North Clark Street', 5, 'mariott.png', UNIX_TIMESTAMP()),
       ('Sheraton', 'Los Angeles', '711 South Hope Street', 4, 'sheraton.png', UNIX_TIMESTAMP()),
       ('Radisson', 'San Francisco', '1015 Market Street', 3, 'radisson.png', UNIX_TIMESTAMP()),
       ('The Ritz-Carlton', 'New York', '50 Central Park S', 5, 'ritz.png', UNIX_TIMESTAMP()),
       ('The Langham', 'Chicago', '330 N Wabash Ave', 5, 'langham.png', UNIX_TIMESTAMP()),
       ('The Westin', 'Los Angeles', '404 S Figueroa St', 4, 'westin.png', UNIX_TIMESTAMP()),
       ('Four Seasons', 'San Francisco', '757 Market St', 5, 'fourseasons.png', UNIX_TIMESTAMP()),
       ('Sofitel', 'London', '6 Waterloo Pl', 5, 'sofitel.png', UNIX_TIMESTAMP()),
       ('Mandarin Oriental', 'Paris', '251 Rue Saint-Honoré', 5, 'mandarin.png', UNIX_TIMESTAMP()),
       ('Kempinski', 'Berlin', 'Behrenstraße 37', 5, 'kempinski.png', UNIX_TIMESTAMP()),
       ('Boscolo', 'Rome', 'Via Veneto, 50', 5, 'boscolo.png', UNIX_TIMESTAMP()),
       ('Shangri-La', 'Tokyo', 'Marunouchi Trust Tower Main', 5, 'shangrila.png', UNIX_TIMESTAMP()),
       ('Fairmont', 'Beijing', '8 Yong An Dong Li', 5, 'fairmont.png', UNIX_TIMESTAMP()),
       ('Novotel', 'New York', '226 W 52nd St', 4, 'novotelNY.png', UNIX_TIMESTAMP()),
       ('Holiday Inn', 'Chicago', '506 W Harrison St', 3, 'holidayinnC.png', UNIX_TIMESTAMP()),
       ('Mercure', 'Los Angeles', '888 S Figueroa St', 4, 'mercureLA.png', UNIX_TIMESTAMP()),
       ('Ibis', 'San Francisco', '1225 Broadway', 3, 'ibisSF.png', UNIX_TIMESTAMP()),
       ('Novotel', 'London', '173-185 Greenwich High Rd', 4, 'novotelL.png', UNIX_TIMESTAMP()),
       ('Holiday Inn', 'Paris', '10 Place de la République', 3, 'holidayinnP.png', UNIX_TIMESTAMP()),
       ('Mercure', 'Berlin', 'Stephanstraße 41', 4, 'mercureB.png', UNIX_TIMESTAMP()),
       ('Ibis', 'Rome', 'Via Circonvallazione Ostiense 164', 3, 'ibisR.png', UNIX_TIMESTAMP()),
       ('Novotel', 'Tokyo', '1 Chome-6-2 Haneda', 4, 'novotelT.png', UNIX_TIMESTAMP()),
       ('Holiday Inn', 'Beijing', '1 Chong Wen Men Xi Da Jie', 3, 'holidayinnB.png', UNIX_TIMESTAMP()),
       ('Courtyard', 'New York', '410 E 92nd St', 4, 'courtyardNY.png', UNIX_TIMESTAMP()),
       ('Hampton Inn', 'Chicago', '33 W Illinois St', 3, 'hamptoninnC.png', UNIX_TIMESTAMP()),
       ('DoubleTree', 'Los Angeles', '120 S Los Angeles St', 4, 'doubletreeLA.png', UNIX_TIMESTAMP()),
       ('Comfort Inn', 'San Francisco', '825 Polk St', 3, 'comfortinnSF.png', UNIX_TIMESTAMP()),
       ('Courtyard', 'London', '1 Addington St', 4, 'courtyardL.png', UNIX_TIMESTAMP()),
       ('DoubleTree', 'Berlin', 'Landsberger Allee 106', 4, 'doubletreeB.png', UNIX_TIMESTAMP()),
       ('Comfort Inn', 'Rome', 'Via Giovanni Amendola 57', 3, 'comfortinnR.png', UNIX_TIMESTAMP()),
       ('Courtyard', 'Tokyo', '2 Chome-1-2 Nihonbashimuromachi', 4, 'courtyardT.png', UNIX_TIMESTAMP()),
       ('Hampton Inn', 'Beijing', 'No.8 Liangshuihe 2nd Street', 3, 'hamptoninnB.png', UNIX_TIMESTAMP());


DELIMITER ;;
DROP FUNCTION IF EXISTS generateRooms ;;
CREATE PROCEDURE generateRooms()
BEGIN
    DECLARE roomType varchar(20);
    DECLARE capacity INT default 1;
    DECLARE price_per_night INT;
    DECLARE hotelId INT default 1;
    DECLARE hotelNum INT;
    DECLARE hotelCounter INT default 1;
    SET hotelNum = (select count(id) from hotels);
    WHILE hotelCounter <= hotelNum
        DO
            SET @singleNum = (SELECT FLOOR(RAND() * (7 - 1 + 1) + 1));
            SET @doubleNum = (SELECT FLOOR(RAND() * (7 - 1 + 1) + 1));
            SET @tripleNum = (SELECT FLOOR(RAND() * (7 - 1 + 1) + 1));
            SET @familyNum = (SELECT FLOOR(RAND() * (7 - 1 + 1) + 1));
            SET @counter = 0;
            WHILE @counter <= @singleNum
                DO
                    SET @roomType = 'SINGLE';
                    SET @capacity = 1;
                    SET @price_per_night = 100;
                    SET @hotelId = hotelCounter;
                    INSERT INTO rooms (room_type, capacity, price_per_night, hotel_id)
                    VALUES (@roomType, @capacity, @price_per_night, @hotelId);
                    SET @counter = @counter + 1;
                END WHILE;
            SET @counter = 0;
            WHILE @counter <= @doubleNum
                DO
                    SET @roomType = 'DOUBLE';
                    SET @capacity = 2;
                    SET @price_per_night = 150;
                    SET @hotelId = hotelCounter;
                    INSERT INTO rooms (room_type, capacity, price_per_night, hotel_id)
                    VALUES (@roomType, @capacity, @price_per_night, @hotelId);
                    SET @counter = @counter + 1;
                END WHILE;
            SET @counter = 0;
            WHILE @counter <= @tripleNum
                DO
                    SET @roomType = 'TRIPLE';
                    SET @capacity = 3;
                    SET @price_per_night = 200;
                    SET @hotelId = hotelCounter;
                    INSERT INTO rooms (room_type, capacity, price_per_night, hotel_id)
                    VALUES (@roomType, @capacity, @price_per_night, @hotelId);
                    SET @counter = @counter + 1;
                END WHILE;
            SET @counter = 0;
            WHILE @counter <= @familyNum
                DO
                    SET @roomType = 'FAMILY';
                    SET @capacity = 4;
                    SET @price_per_night = 250;
                    SET @hotelId = hotelCounter;
                    INSERT INTO rooms (room_type, capacity, price_per_night, hotel_id)
                    VALUES (@roomType, @capacity, @price_per_night, @hotelId);
                    SET @counter = @counter + 1;
                END WHILE;
            SET hotelCounter = hotelCounter + 1;
        END WHILE;
END ;;
DELIMITER ;

CALL generateRooms();