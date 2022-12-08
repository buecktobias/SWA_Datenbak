CREATE TABLE umsatz (
    id        INTEGER(16) NOT NULL PRIMARY KEY,
    betrag    DECIMAL(10,2) NOT NULL,
    waehrung  CHAR(3) NOT NULL
) TABLESPACE filialespace ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS adresse (
    id    INTEGER(16) PRIMARY KEY,
    plz   CHAR(5) NOT NULL,
    ort   VARCHAR(40) NOT NULL,

    INDEX adresse_plz_idx(plz)
) TABLESPACE filialespace ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS filiale (
    id            INTEGER(16) NOT NULL PRIMARY KEY,
    version       INT NOT NULL DEFAULT 0,
    name      VARCHAR(40) NOT NULL,
    email         VARCHAR(40) UNIQUE NOT NULL,
    homepage      VARCHAR(40),
    umsatz_id     BINARY(16) REFERENCES umsatz,
    adresse_id    BINARY(16) NOT NULL REFERENCES adresse,
    erzeugt       DATETIME NOT NULL,
    aktualisiert  DATETIME NOT NULL,

    INDEX kunde_nachname_idx(name)
) TABLESPACE kundespace ROW_FORMAT=COMPACT;

