-- ************************************************************************************************
-- Tabellen erstellen und Beziehungen festlegen
-- ************************************************************************************************

-- HOTFIX: MySQL haut es (trotz CASCADE), wenn das eigeschalten bleibt. Da eh alles gelöscht wird, ist es so sogar performanter
SET FOREIGN_KEY_CHECKS = 0;

CREATE OR REPLACE TABLE COUNTRY(
	ID INT(255) AUTO_INCREMENT PRIMARY KEY,
	CODE VARCHAR(2),
	NAME VARCHAR(255)
);

-- Welche Städte gibt es
CREATE OR REPLACE TABLE CITY(
	ID INT(255) AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
	NAME VARCHAR(255),
  FK_Country_ID INT(255)
);

-- Welche Strecken gibt es
CREATE OR REPLACE TABLE TRACK(
	ID INT(255) AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(255),
  LENGTH VARCHAR(255),
  FASTEST_LAP VARCHAR(255),
  SEATS INT(255),
  FK_City_ID INT(255)
);

-- Welche Rennen gibt es
CREATE OR REPLACE TABLE RACE(
  ID INT(255) NOT NULL AUTO_INCREMENT,
  LAPS VARCHAR(255),
  START_TIME VARCHAR(255),
  DURATION VARCHAR(255),
  R_DATE DATE,
  FK_Track_ID INT(255),
  PRIMARY KEY (ID)
);


-- Welche Strecken gibt es
CREATE OR REPLACE TABLE DRIVER(
  ID INT(255) AUTO_INCREMENT PRIMARY KEY,
  FORNAME VARCHAR(255),
  LASTNAME VARCHAR(255),
  DOB DATE,
  WEIGHT INT(255),
  FK_Country_ID INT(255),
  FK_Team_ID INT(255)
);

-- Welche Teams gibt es
CREATE OR REPLACE TABLE TEAM(
  ID INT(255) AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
  NAME VARCHAR(255),
  FK_Brand_ID INT(255)
);

-- Welche Marken gibt es
CREATE OR REPLACE TABLE BRAND(
  ID INT(255) AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
  NAME VARCHAR(255)
);

-- Welche Position hat welcher Fahrer bei welchem Rennen erreicht
CREATE OR REPLACE TABLE RACE_TO_DRIVER(
  RACE_ID INT(255) NOT NULL,
  DRIVER_ID INT(255) NOT NULL,
  POSITION INT(255) NOT NULL
);

-- In der Beziehungstabelle dürfen nur IDs vorkommen die es auch gibt
ALTER TABLE CITY ADD FOREIGN KEY (FK_Country_ID) REFERENCES COUNTRY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE TRACK ADD FOREIGN KEY (FK_City_ID) REFERENCES CITY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE RACE ADD FOREIGN KEY (FK_Track_ID) REFERENCES TRACK(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE DRIVER ADD FOREIGN KEY (FK_Country_ID) REFERENCES COUNTRY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE DRIVER ADD FOREIGN KEY (FK_Team_ID) REFERENCES TEAM(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE TEAM ADD FOREIGN KEY (FK_Brand_ID) REFERENCES BRAND(ID) ON DELETE CASCADE ON UPDATE CASCADE;

-- Keine Doppelten Einträge
ALTER TABLE RACE_TO_DRIVER ADD CONSTRAINT NO_DUPL_PD UNIQUE (RACE_ID, DRIVER_ID);


SET FOREIGN_KEY_CHECKS = 1;
-- END OF HOTFIX ;)

-- ************************************************************************************************
-- Es ist an der Zeit einige Daten einzufüllen
-- ************************************************************************************************
-- Alle Länder

-- Alle Städte

-- Alle Strecken

-- Alle Marken

-- Alle Teams

-- Alle Fahrer: Team Zuweisung, Gewicht und DOB sind zufällig und entsprechen nicht der Realität

-- Alle Rennen

-- ************************************************************************************************
-- Erstellen von einigen VIEWS (... sind gespeichert SQL Statements) zwecks Auswertung
-- ************************************************************************************************
