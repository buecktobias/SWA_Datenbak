USE filiale;
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (UUID_TO_BIN('10000000-0000-0000-0000-000000000000'), 0, 'EUR');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (UUID_TO_BIN('10000000-0000-0000-0000-000000000001'), 10, 'EUR');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (UUID_TO_BIN('10000000-0000-0000-0000-000000000002'), 20, 'USD');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (UUID_TO_BIN('10000000-0000-0000-0000-000000000030'), 30, 'CHF');
INSERT INTO umsatz (id, betrag, waehrung)
VALUES (UUID_TO_BIN('10000000-0000-0000-0000-000000000040'), 40, 'GBP');

INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000000'), '00000', 'Aachen');
INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000001'), '11111', 'Augsburg');
INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000002'), '22222', 'Aalen');
INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000030'), '33333', 'Ahlen');
INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000040'), '44444', 'Dortmund');
INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000050'), '55555', 'Essen');
INSERT INTO adresse (id, plz, ort)
VALUES (UUID_TO_BIN('20000000-0000-0000-0000-000000000060'), '66666', 'Freiburg');

-- admin
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000000'), 0, 'Admin', 'admin@acme.com',
        'https://www.acme.com', UUID_TO_BIN('10000000-0000-0000-0000-000000000000'),
        UUID_TO_BIN('20000000-0000-0000-0000-000000000000'), '2022-01-31 00:00:00', '2022-01-31 00:00:00');
-- HTTP GET
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000001'), 0, 'Alpha', 'alpha@acme.de',
        'https://www.acme.de', UUID_TO_BIN('10000000-0000-0000-0000-000000000001'),
        UUID_TO_BIN('20000000-0000-0000-0000-000000000001'), '2022-01-01 00:00:00', '2022-01-01 00:00:00');
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000002'), 0, 'Alpha', 'alpha@acme.edu',
        'https://www.acme.edu', UUID_TO_BIN('10000000-0000-0000-0000-000000000002'),
        UUID_TO_BIN('20000000-0000-0000-0000-000000000002'), '2022-01-02 00:00:00', '2022-01-02 00:00:00');
-- HTTP PUT
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000030'), 0, 'Alpha', 'alpha@acme.ch',
        'https://www.acme.ch', UUID_TO_BIN('10000000-0000-0000-0000-000000000030'),
        UUID_TO_BIN('20000000-0000-0000-0000-000000000030'), '2022-01-03 00:00:00', '2022-01-03 00:00:00');
-- HTTP PATCH
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000040'), 0, 'Delta', 'delta@acme.uk',
        'https://www.acme.uk', UUID_TO_BIN('10000000-0000-0000-0000-000000000040'),
        UUID_TO_BIN('20000000-0000-0000-0000-000000000040'), '2022-01-04 00:00:00', '2022-01-04 00:00:00');
-- HTTP DELETE
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000050'), 0, 'Epsilon', 'epsilon@acme.jp',
        'https://www.acme.jp', null, UUID_TO_BIN('20000000-0000-0000-0000-000000000050'),
        '2022-01-05 00:00:00', '2022-01-05 00:00:00');
-- zur freien Verfuegung
INSERT INTO filiale (id, version, name, email, homepage, umsatz_id, adresse_id, erzeugt, aktualisiert)
VALUES (UUID_TO_BIN('00000000-0000-0000-0000-000000000060'), 0, 'Phi', 'phi@acme.cn',
        'https://www.acme.cn', null, UUID_TO_BIN('20000000-0000-0000-0000-000000000060'),
        '2022-01-06 00:00:00', '2022-01-06 00:00:00');

