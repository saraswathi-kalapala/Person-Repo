DROP TABLE IF EXISTS persons;
  
CREATE TABLE persons(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  current_Address VARCHAR(250) NOT NULL,
  date_of_birth  Date NOT NULL

);