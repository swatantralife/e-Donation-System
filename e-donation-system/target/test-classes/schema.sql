DROP TABLE IF EXISTS DONOR;

CREATE TABLE donor (
  donar_id bigint NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  donar_name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  ngo_id bigint DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  phone_number bigint DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (donar_id)
);

DROP TABLE IF EXISTS donation;
CREATE TABLE donation (
  donation_id bigint NOT NULL AUTO_INCREMENT,
  amount double DEFAULT NULL,
  donation_date date DEFAULT NULL,
  donation_type varchar(255) DEFAULT NULL,
  donor_id bigint DEFAULT NULL,
  ngo_id bigint DEFAULT NULL,
  PRIMARY KEY (donation_id)
) ;

DROP TABLE IF EXISTS ngo;
CREATE TABLE ngo (
  ngo_id bigint NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  established_date date DEFAULT NULL,
  ngo_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  phone_number bigint DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (ngo_id)
) ;