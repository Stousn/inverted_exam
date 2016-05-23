-- ************************************************************************************************
-- Tabellen und Views vorher wegwerfen damit das Script die Datenbank immer wieder neu macht
-- ************************************************************************************************
DROP ALL OBJECTS;

-- ************************************************************************************************
-- Tabellen erstellen und Beziehungen festlegen
-- ************************************************************************************************

-- Welche Länder gibt es
CREATE TABLE COUNTRY(
	ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	CODE VARCHAR(255),
	NAME VARCHAR(255));

-- Welche Städte gibt es
CREATE TABLE CITY(
	ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
	NAME VARCHAR(255));

-- Welche Strecken gibt es
CREATE TABLE TRACK(
	ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(255),
  LENGTH VARCHAR(255),
  FASTEST_LAP VARCHAR(255),
  SEATS NUMBER(255));

-- Welche Rennen gibt es
CREATE TABLE RACE(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  LAPS VARCHAR(255),
  START_TIME VARCHAR(255),
  DURATION VARCHAR(255),
  R_DATE DATE(255));

-- Welche Strecken gibt es
CREATE TABLE DRIVER(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  FORNAME VARCHAR(255),
  LASTNAME VARCHAR(255),
  DOB DATE(255),
  WEIGHT NUMBER(255));

-- Welche Teams gibt es
CREATE TABLE TEAM(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
  NAME VARCHAR(255));

-- Welche Marken gibt es
CREATE TABLE BRAND(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  CODE VARCHAR(255),
  NAME VARCHAR(255));

-- Welche Position hat welcher Fahrer bei welchem Rennen erreicht
CREATE TABLE RACE_TO_DRIVER(
  RACE_ID BIGINT NOT NULL,
  DRIVER_ID BIGINT NOT NULL,
  POSITION BIGINT NOT NULL);

-- In der Beziehungstabelle dürfen nur IDs vorkommen die es auch gibt
ALTER TABLE CITY ADD FOREIGN KEY (FK_Country_ID) REFERENCES COUNTRY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE TRACK ADD FOREIGN KEY (FK_City_ID) REFERENCES CITY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE RACE ADD FOREIGN KEY (FK_Track_ID) REFERENCES TRACK(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE DRIVER ADD FOREIGN KEY (FK_Country_ID) REFERENCES COUNTRY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE DRIVER ADD FOREIGN KEY (FK_Team_ID) REFERENCES TEAM(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE TEAM ADD FOREIGN KEY (FK_Brand_ID) REFERENCES BRAND(ID) ON DELETE CASCADE ON UPDATE CASCADE;

-- Keine Doppelten Einträge
ALTER TABLE RACE_TO_DRIVER ADD CONSTRAINT NO_DUPL_PD UNIQUE (RACE_ID, DRIVER_ID);


-- ************************************************************************************************
-- Es wird an der Zeit einige Daten einzufüllen
-- ************************************************************************************************
-- Alle Länder
INSERT INTO COUNTRY (CODE, NAME) VALUES ('DE', 'GERMANY');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('AT', 'AUSTRIA');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('HU', 'HUNGARY');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('NL', 'NETHERLANDS');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('GB', 'United Kingdom');

-- Alle Städte
INSERT INTO CITY (CODE, NAME, FK_Country_ID) VALUES('HOK','Hockenheim','1');

-- Alle Strecken
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Hockenheimring','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Spielberg','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Lausitzring','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Norisring','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Zandvoort','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Moscow Raceway','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Nürburgring','4,574km','1:32,532', '120.000');
INSERT INTO TRACK (NAME, LENGTH, FASTEST_LAP, SEATS) VALUES('Budapest','4,574km','1:32,532', '120.000');

-- Alle Marken
INSERT INTO TEAM (CODE, NAME) VALUES ('MRC', 'Mercedes Benz');
INSERT INTO TEAM (CODE, NAME) VALUES ('BMW', 'BMW');
INSERT INTO TEAM (CODE, NAME) VALUES ('AUD', 'Audi');

-- Alle Teams
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','BMW Team RMG','2');
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','BMW Team RBM','2');
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','Audi Sport Team Abt Sportsline','3');
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','Mercedes-AMG','1');
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','BMW Team MTEK','2');
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','SILBERPFEIL Energy/UBFS invest Mercedes-AMG','1');
INSERT INTO TEAM (CODE, NAME, FK_Brand_ID) VALUES ('','BMW Team RMG','2');

-- Alle Fahrer
INSERT INTO DRIVER (FORNAME, LASTNAME, DOB, WEIGHT, FK_Country_ID, FK_Team_ID) VALUES ('Mattias', 'Ekström', '14.07.1978', '70KG', '2', '3');


-- Alle Rennen
INSERT INTO RACE (LAPS, START_TIME, DURATION, R_DATE, FK_Track_ID) VALUES ('40', '14:00', '2h', '7.5.2016', '1');

-- ************************************************************************************************
-- Erstellen von einigen VIEWS (... sind gespeichert SQL Statements) zwecks Auswertung
-- ************************************************************************************************

-- Welcher Mitarbeiter darf welchen Raum öffnen?
--CREATE VIEW VIEW_RFID_ROOMID AS
--SELECT R.NAME AS Roomname, P.RFID AS RFID, P.NACHNAME AS Lastname FROM DEPARTMENT D
--INNER JOIN PERSON_DEPARTMENT PD ON D.ID = PD.DEPARTMENT_ID
--INNER JOIN PERSON P ON P.ID = PD.PERSON_ID
--INNER JOIN ROOM_DEPARTMENT RD ON RD.DEPARTMENT_ID = D.ID
--INNER JOIN ROOM R ON R.ID = RD.ROOM_ID
--ORDER BY P.NACHNAME;
