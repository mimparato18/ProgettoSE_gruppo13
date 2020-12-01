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