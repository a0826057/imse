CREATE TABLE customer (
  customer_ID				int AUTO_INCREMENT,
  title						varchar(20),
  first_name				varchar(120) NOT NULL,
  last_name					varchar(120) NOT NULL,
  drivers_license_number	varchar(15) NOT NULL UNIQUE,
  birth_date				date NOT NULL,
  email						varchar(120) NOT NULL,
  post_code					varchar(10),
  street 					varchar(120),
  house_number				varchar(10),
  appartment_number 		varchar(10),
  town						varchar(100),
  country					varchar(50),
  pwd_hash					varchar(50),
  salt						varchar(20),
  active					boolean DEFAULT true,
  PRIMARY KEY (customer_ID)
);

CREATE TABLE employee (
  employee_number			int AUTO_INCREMENT,
  first_name				varchar(120) NOT NULL,
  last_name					varchar(120) NOT NULL,
  superior_ID				int,
  active					boolean DEFAULT true,
  PRIMARY KEY (employee_number),
  FOREIGN KEY (superior_ID) REFERENCES employee (employee_number)
);

CREATE TABLE color (
  color_ID					int AUTO_INCREMENT,
  description				varchar(255) NOT NULL,
  manufacturer_color_code	varchar(100),
  PRIMARY KEY (color_ID)
);

CREATE TABLE manufacturer (
  manufacturer_ID			int AUTO_INCREMENT,
  name						varchar(120) NOT NULL,
  country					varchar(50),
  PRIMARY KEY (manufacturer_ID)
);

CREATE TABLE model (
  model_ID					int,
  manufacturer_ID			int,
  description				varchar(255) NOT NULL,
  price						decimal(10,2) DEFAULT 0,
  PRIMARY KEY (model_ID, manufacturer_ID),
  FOREIGN KEY (manufacturer_ID) REFERENCES manufacturer (manufacturer_ID),
  CONSTRAINT check_price CHECK (price > 0)
);

CREATE TABLE vehicle (
  vehicle_ID				int AUTO_INCREMENT,
  license_plate_number		varchar(15) NOT NULL UNIQUE,
  color_ID					int,
  model_ID					int,
  manufacturer_ID   int,
  mileage					int DEFAULT 0,
  manufacture_year			smallint,
  active					boolean DEFAULT true,
  PRIMARY KEY (vehicle_ID),
  FOREIGN KEY (color_ID) REFERENCES color (color_ID),
  FOREIGN KEY (model_ID, manufacturer_ID) REFERENCES model (model_ID, manufacturer_ID),
  CONSTRAINT check_vehicle CHECK (mileage >= 0 AND manufacture_year > 1940)
);

CREATE TABLE truck (
  truck_ID					int REFERENCES vehicle,
  length					int,
  height					int,
  loading_limit				int,
  PRIMARY KEY (truck_ID),
  CONSTRAINT check_truck CHECK (loading_limit > 0 AND length > 0 AND height > 0)
);

CREATE TABLE car (
  car_ID					int REFERENCES vehicle,
  doors						int,
  passenger_limit			smallint DEFAULT 4,
  PRIMARY KEY (car_ID)
);

CREATE TABLE rents (
  vehicle_ID				int,
  customer_ID				int,
  employee_number			int,
  date_from					date,
  date_to					date,
  rating					varchar(255),
  PRIMARY KEY (vehicle_ID, customer_ID, employee_number, date_from),
  CONSTRAINT check_date CHECK (date_from <= date_to),
  FOREIGN KEY (vehicle_ID) REFERENCES vehicle (vehicle_ID),
  FOREIGN KEY (customer_ID) REFERENCES customer (customer_ID),
  FOREIGN KEY (employee_number) REFERENCES employee (employee_number)
);

CREATE TABLE accessory (
  accessory_ID				int AUTO_INCREMENT,
  name						varchar(120) NOT NULL,
  description				varchar(255),
  PRIMARY KEY (accessory_ID)
);

CREATE TABLE has_accessory (
  accessory_ID				int,
  vehicle_ID				int,
  PRIMARY KEY (accessory_ID, vehicle_ID),
  FOREIGN KEY (accessory_ID) REFERENCES accessory (accessory_ID),
  FOREIGN KEY (vehicle_ID) REFERENCES vehicle (vehicle_ID)
);
