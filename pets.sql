DROP DATABASE pets;

CREATE DATABASE pets;

USE pets;

CREATE TABLE clients(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	phone_number VARCHAR(10),
	address VARCHAR(255) NOT NULL
);
	
CREATE TABLE pets(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	owner_id INT NOT NULL,
	species VARCHAR(50) NOT NULL,
	gender ENUM('Male', 'Female') NOT NULL,
	age INT NOT NULL,
	weight DOUBLE(100, 2) NOT NULL,
	medical_history_link VARCHAR(150),
	FOREIGN KEY(owner_id) REFERENCES clients(id)
);

CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	phone_number VARCHAR(10),
	role ENUM('Empleado', 'Administrador', 'Estilista')
);

CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
	category VARCHAR(100) NOT NULL
);

CREATE TABLE services(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	DESCRIPTION VARCHAR(255),
	price DECIMAL(19, 3) NOT NULL,
	category_id INT NOT NULL,
	FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE sales(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	client_id INT NOT NULL,
	total_price DECIMAL(19, 3),
	sale_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	status ENUM('Completada', 'Pendiente', 'Cancelada') DEFAULT 'Pendiente',
	payment_method ENUM('Efectivo', 'Tarjeta', 'Transferencia'),
	FOREIGN KEY(user_id) REFERENCES users(id),
	FOREIGN KEY(client_id) REFERENCES clients(id)
);

CREATE TABLE sale_details(
	id INT PRIMARY KEY AUTO_INCREMENT,
	sale_id INT NOT NULL,
	service_id INT NOT NULL,
	pet_id INT NOT NULL,
	scheduled_date DATETIME NOT NULL,
	FOREIGN KEY(sale_id) REFERENCES sales(id),
	FOREIGN KEY(service_id) REFERENCES services(id),
	FOREIGN KEY(pet_id) REFERENCES pets(id)
);