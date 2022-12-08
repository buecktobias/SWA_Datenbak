USE filiale;
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (0, 0, 'EUR');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (1, 10, 'EUR');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (2, 20, 'USD');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (30, 30, 'CHF');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (40, 40, 'GBP');


INSERT INTO adresse (id, plz, ort)
VALUES (0, '00000', 'Aachen');
INSERT INTO adresse (id, plz, ort)
VALUES (1, '11111', 'Augsburg');
INSERT INTO adresse (id, plz, ort)
VALUES (2, '22222', 'Aalen');
INSERT INTO adresse (id, plz, ort)
VALUES (30, '33333', 'Ahlen');
INSERT INTO adresse (id, plz, ort)
VALUES (40, '44444', 'Dortmund');
INSERT INTO adresse (id, plz, ort)
VALUES (50, '55555', 'Essen');
INSERT INTO adresse (id, plz, ort)
VALUES (60, '66666', 'Freiburg');

INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (0, 0, 'Admin', 'admin@acme.com',
        'https://www.acme.com', 0,
        0, '2022-01-31 00:00:00', '2022-01-31 00:00:00');

INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (1, 0, 'Alpha', 'alpha@acme.de',
        'https://www.acme.de', 1,
        1, '2022-01-01 00:00:00', '2022-01-01 00:00:00');
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (2, 0, 'Alpha', 'alpha@acme.edu',
        'https://www.acme.edu', 2,
        2, '2022-01-02 00:00:00', '2022-01-02 00:00:00');

INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (30, 0, 'Alpha', 'alpha@acme.ch',
        'https://www.acme.ch', 30,
        30, '2022-01-03 00:00:00', '2022-01-03 00:00:00');

INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (40, 0, 'Delta', 'delta@acme.uk',
        'https://www.acme.uk', 40,
        40, '2022-01-04 00:00:00', '2022-01-04 00:00:00');

INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (50, 0, 'Epsilon', 'epsilon@acme.jp',
        'https://www.acme.jp', null, 50,
        '2022-01-05 00:00:00', '2022-01-05 00:00:00');

INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (60, 0, 'Phi', 'phi@acme.cn',
        'https://www.acme.cn', null, 60,
        '2022-01-06 00:00:00', '2022-01-06 00:00:00');

