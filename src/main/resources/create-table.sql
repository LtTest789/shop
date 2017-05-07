CREATE TABLE ITEMS(
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   item_name VARCHAR (256) NOT NULL UNIQUE,
   description VARCHAR(100) NOT NULL,
   country_of_manufacturing VARCHAR(25),
   date_of_manufacturing DATE,
   price INT(20),
   PRIMARY KEY (id)
);