CREATE TABLE room
(
    room_number VARCHAR(20) PRIMARY KEY,
    room_type   VARCHAR(20) NOT NULL,
    has_minibar BOOLEAN     NOT NULL
);

CREATE TABLE booking
(
    id          BIGSERIAL PRIMARY KEY,
    room_number VARCHAR(20) NOT NULL REFERENCES room (room_number),
    start_date  DATE        NOT NULL,
    end_date    DATE        NOT NULL,
    guest_name  VARCHAR(255)
);
