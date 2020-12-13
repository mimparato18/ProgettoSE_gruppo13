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
DROP TABLE IF EXISTS competence;

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
    ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_typology FOREIGN KEY (typology)
    REFERENCES typology(type)
    ON DELETE RESTRICT ON UPDATE CASCADE,
);

CREATE TABLE competence(
    competencename VARCHAR(30) PRIMARY KEY
);