DROP TABLE  IF EXISTS TourLogs;
DROP TABLE IF EXISTS Tours;
DROP SEQUENCE  IF EXISTS hibernate_sequence;


CREATE TABLE Tours(
    tour_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    start_location VARCHAR(255),
    end_location VARCHAR(255),
    transport_type VARCHAR(50),
    distance FLOAT,
    estimated_time VARCHAR(20),
    route_info VARCHAR(500)
    );

CREATE TABLE TourLogs(
    log_id SERIAL PRIMARY KEY,
    tour_id INT NOT NULL,
    date_time DATE NOT NULL,
    rating FLOAT NOT NULL,
    comment VARCHAR(500),
    difficulty FlOAT,
    total_time VARCHAR(200),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id)
    );

CREATE SEQUENCE hibernate_sequence START 1;