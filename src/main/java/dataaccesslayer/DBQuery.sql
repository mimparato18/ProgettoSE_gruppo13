/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  simo5
 * Created: 29 nov 2020
 */

DROP TABLE IF EXISTS user ;
DROP TABLE IF EXISTS typology ;
DROP TABLE IF EXISTS site ;
DROP TABLE IF EXISTS maintainercompetence;
DROP TABLE IF EXISTS maintenanceprocedure;
DROP TABLE IF EXISTS maintenanceactivity;
DROP TABLE IF EXISTS maintaineravailability;

CREATE TABLE user(
    username VARCHAR (20) PRIMARY KEY,
    password VARCHAR (20),
    role VARCHAR (20) check (role = "Planner" or role = "System Administrator" or role = "Maintainer")
);

CREATE TABLE typology(
    type VARCHAR(20) PRIMARY KEY
);

CREATE TABLE site(
    branchoffice VARCHAR(20),
    department VARCHAR(20),
    PRIMARY KEY(branchoffice,department)
);

CREATE TABLE maintainercompetence(
  username VARCHAR(20),
  competence VARCHAR(50),
  PRIMARY KEY(username,competence),
  constraint FK_maintainercompetence FOREIGN KEY (username) 
  REFERENCES user(username)
);

CREATE TABLE maintenanceprocedure(
    name VARCHAR(20) PRIMARY KEY
);

CREATE TABLE maintenanceactivity(
    id INT AUTO_INCREMENT PRIMARY KEY,
    materials VARCHAR(100),
    typology VARCHAR(20),
    estimatedtime INT,
    activitydescription VARCHAR(100),
    branchoffice VARCHAR(20),
    department VARCHAR(20),
    week INT,
    interruptable BOOLEAN,
    workspacenotes VARCHAR(100),
    CONSTRAINT FK_site FOREIGN KEY (branchoffice,department)
    REFERENCES site(branchoffice,department)
    ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT FK_typology FOREIGN KEY (typology)
    REFERENCES typology(type)
    ON DELETE RESTRICT ON UPDATE RESTRICT,
);

CREATE TABLE maintaineravailability(
	username VARCHAR(20),
    week INT CHECK (week > 0 AND week < 53),
    day INT CHECK (day > 0 AND day < 8),
    hour1 INT DEFAULT 60 CHECK (hour1 >= 0 AND hour1 <= 60),
    hour2 INT DEFAULT 60 CHECK (hour2 >= 0 AND hour2 <= 60),
    hour3 INT DEFAULT 60 CHECK (hour3 >= 0 AND hour3 <= 60),
    hour4 INT DEFAULT 60 CHECK (hour4 >= 0 AND hour4 <= 60),
    hour5 INT DEFAULT 60 CHECK (hour5 >= 0 AND hour5 <= 60),
    hour6 INT DEFAULT 60 CHECK (hour6 >= 0 AND hour6 <= 60),
    hour7 INT DEFAULT 60 CHECK (hour7 >= 0 AND hour7 <= 60),
    PRIMARY KEY(username,week,day),
    CONSTRAINT fk_maintainer FOREIGN KEY(username) 
    REFERENCES user(username)
    ON DELETE CASCADE ON UPDATE CASCADE
);