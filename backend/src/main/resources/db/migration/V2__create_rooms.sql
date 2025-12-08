INSERT INTO room (room_number, room_type, has_minibar)
VALUES ('A-400', 'DOUBLE', true),
       ('A-404', 'SINGLE', true),
       ('B-201', 'SUITE', false) ON CONFLICT (room_number) DO NOTHING;
