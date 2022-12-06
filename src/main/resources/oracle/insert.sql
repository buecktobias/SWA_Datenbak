-- Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with this program.  If not, see <https://www.gnu.org/licenses/>.

-- docker compose exec oracle sqlplus kunde/p@XEPDB1

INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000000','admin','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');
INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000001','alpha','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');
INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000002','alpha2','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');
INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000030','alpha3','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');
INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000040','delta','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');
INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000050','epsilon','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');
INSERT INTO login (id, username, password) VALUES ('30000000-0000-0000-0000-000000000060','phi','{argon2id}$argon2id$v=19$m=16384,t=3,p=1$QHb5SxDhddjUiGboXTc9S9yCmRoPsBejIvW/dw50DKg$WXZDFJowwMX5xsOun2BT2R3hv2aA9TSpnx3hZ3px59sTW0ObtqBwX7Sem6ACdpycArUHfxmFfv9Z49e7I+TI/g');

INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000000','ADMIN');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000000','KUNDE');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000000','ACTUATOR');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000001','KUNDE');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000002','KUNDE');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000030','KUNDE');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000040','KUNDE');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000050','KUNDE');
INSERT INTO login_rollen (login_id, rolle) VALUES ('30000000-0000-0000-0000-000000000060','KUNDE');

INSERT INTO umsatz (id, betrag, waehrung) VALUES ('10000000-0000-0000-0000-000000000000',0,'EUR');
INSERT INTO umsatz (id, betrag, waehrung) VALUES ('10000000-0000-0000-0000-000000000001',10,'EUR');
INSERT INTO umsatz (id, betrag, waehrung) VALUES ('10000000-0000-0000-0000-000000000002',20,'USD');
INSERT INTO umsatz (id, betrag, waehrung) VALUES ('10000000-0000-0000-0000-000000000030',30,'CHF');
INSERT INTO umsatz (id, betrag, waehrung) VALUES ('10000000-0000-0000-0000-000000000040',40,'GBP');

INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000000','00000','Aachen');
INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000001','11111','Augsburg');
INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000002','22222','Aalen');
INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000030','33333','Ahlen');
INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000040','44444','Dortmund');
INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000050','55555','Essen');
INSERT INTO adresse (id, plz, ort) VALUES ('20000000-0000-0000-0000-000000000060','66666','Freiburg');

-- admin
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000000',0,'Admin','admin@acme.com',0,1,TO_DATE('2022-01-31 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.com','W','VH','10000000-0000-0000-0000-000000000000','20000000-0000-0000-0000-000000000000','admin',TO_TIMESTAMP('2022-01-31 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-31 12:00:00', 'yyyy-MM-dd hh:mi:ss'));
-- HTTP GET
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000001',0,'Alpha','alpha@acme.de',1,1,TO_DATE('2022-01-01 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.de','M','L','10000000-0000-0000-0000-000000000001','20000000-0000-0000-0000-000000000001','alpha',TO_TIMESTAMP('2022-01-01 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-01 12:00:00', 'yyyy-MM-dd hh:mi:ss'));
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000002',0,'Alpha','alpha@acme.edu',2,1,TO_DATE('2022-01-02 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.edu','W','G','10000000-0000-0000-0000-000000000002','20000000-0000-0000-0000-000000000002','alpha2',TO_TIMESTAMP('2022-01-02 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-02 12:00:00', 'yyyy-MM-dd hh:mi:ss'));
-- HTTP PUT
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000030',0,'Alpha','alpha@acme.ch',3,1,TO_DATE('2022-01-03 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.ch','M','VW','10000000-0000-0000-0000-000000000030','20000000-0000-0000-0000-000000000030','alpha3',TO_TIMESTAMP('2022-01-03 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-03 12:00:00', 'yyyy-MM-dd hh:mi:ss'));
-- HTTP PATCH
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000040',0,'Delta','delta@acme.uk',4,1,TO_DATE('2022-01-04 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.uk','W','VH','10000000-0000-0000-0000-000000000040','20000000-0000-0000-0000-000000000040','delta',TO_TIMESTAMP('2022-01-04 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-04 12:00:00', 'yyyy-MM-dd hh:mi:ss'));
-- HTTP DELETE
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000050',0,'Epsilon','epsilon@acme.jp',5,1,TO_DATE('2022-01-05 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.jp','M','L',null,'20000000-0000-0000-0000-000000000050','epsilon',TO_TIMESTAMP('2022-01-05 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-05 12:00:00', 'yyyy-MM-dd hh:mi:ss'));
-- zur freien Verfuegung
INSERT INTO kunde (id, version, nachname, email, kategorie, has_newsletter, geburtsdatum, homepage, geschlecht, familienstand, umsatz_id, adresse_id, username, erzeugt, aktualisiert) VALUES ('00000000-0000-0000-0000-000000000060',0,'Phi','phi@acme.cn',6,1,TO_DATE('2022-01-06 12:00:00', 'yyyy-MM-dd hh:mi:ss'),'https://www.acme.cn','D','L',null,'20000000-0000-0000-0000-000000000060','phi',TO_TIMESTAMP('2022-01-06 12:00:00', 'yyyy-MM-dd hh:mi:ss'),TO_TIMESTAMP('2022-01-06 12:00:00', 'yyyy-MM-dd hh:mi:ss'));

INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000000','L');
INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000001','S');
INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000001','L');
INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000030','S');
INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000030','R');
INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000040','L');
INSERT INTO kunde_interessen (kunde_id, interesse) VALUES ('00000000-0000-0000-0000-000000000040','R');
