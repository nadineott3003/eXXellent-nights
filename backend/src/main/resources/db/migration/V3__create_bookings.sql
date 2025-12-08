-- Buchungen Dezember 2025
-- A-400: 3 Buchungen
INSERT INTO booking (room_number, start_date, end_date, guest_name)
VALUES
    ('A-400', '2025-12-01', '2025-12-05', 'Max Mustermann'),
    ('A-400', '2025-12-10', '2025-12-12', 'Bob Baumeister'),
    ('A-400', '2025-12-20', '2025-12-24', 'Bibi Blocksberg');


-- A-404: 2 Buchungen
INSERT INTO booking (room_number, start_date, end_date, guest_name)
VALUES
    ('A-404', '2025-12-03', '2025-12-08', 'Benjamin Blümchen'),
    ('A-404', '2025-12-18', '2025-12-22', 'Anna Musterfrau');


-- B-201: 1 Buchung über Weihnachten
INSERT INTO booking (room_number, start_date, end_date, guest_name)
VALUES
    ('B-201', '2025-12-15', '2025-12-27', 'Nadine Ott');
