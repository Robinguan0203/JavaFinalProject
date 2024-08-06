-- Author: Robin Guan
-- Date: 2024-07-26
-- Description: This script sets up the database schema for the FWRP application.

-- Drop the existing database if it exists and create a new one
DROP DATABASE IF EXISTS fwrp;
CREATE DATABASE fwrp;

-- Create test user without password
DROP USER IF EXISTS 'test'@'localhost';
CREATE USER 'test'@'localhost' IDENTIFIED BY '';
GRANT USAGE ON *.* TO 'test'@'localhost';
GRANT ALL PRIVILEGES ON fwrp.* TO 'test'@'localhost';

-- Apply the changes to the privileges
FLUSH PRIVILEGES;

-- Use the newly created database
USE fwrp;

-- Create the users table to store user information
CREATE TABLE users
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    type INT NOT NULL,
    organization VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the foods table to store food information
CREATE TABLE foods
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    expire_days INT NOT NULL,
    unitprice DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the subscriptions table to store user subscriptions
CREATE TABLE subscriptions
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    method INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the preferences table to store user food preferences
CREATE TABLE preferences
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    food_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the notifications table to store user notifications
CREATE TABLE notifications
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    method INT NOT NULL,
    date DATE NOT NULL,
    notification TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the expire_infos table to store information about food expiration
CREATE TABLE expire_infos
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    food_id INT NOT NULL,
    quantity INT NOT NULL,
    expire_date DATE NOT NULL,
    is_surplus BOOLEAN NOT NULL,
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the inventory table to store inventory information
CREATE TABLE inventory
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    food_id INT NOT NULL,
    quantity_normal INT NOT NULL,
    quantity_discount INT NOT NULL,
    quantity_donation INT NOT NULL,
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the orders table to store order information
CREATE TABLE orders
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    food_id INT NOT NULL,
    date DATE NOT NULL,
    unitprice DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the claims table to store claim information
CREATE TABLE claims
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    food_id INT NOT NULL,
    date DATE NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (food_id) REFERENCES foods(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create the transactions table to store transaction information
CREATE TABLE transactions
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    food_id INT NOT NULL,
    user_id INT NOT NULL,
    order_id INT,
    claim_id INT,
    date DATE NOT NULL,
    type INT NOT NULL,
    quantity_normal INT NOT NULL,
    quantity_discount INT NOT NULL,
    quantity_donation INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (food_id) REFERENCES foods(id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (claim_id) REFERENCES claims(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert sample user data into the users table
INSERT INTO users (firstname, lastname, phone, email, password, type, organization) 
            VALUES ('John', 'Doe', '1234567890', 'john@test.com', '123456', 1, 'Retailer');
INSERT INTO users (firstname, lastname, phone, email, password, type, organization)
            VALUES ('Jane', 'Wang', '1234567891', 'jane@test.com', '123456', 2, 'Food Bank');
INSERT INTO users (firstname, lastname, phone, email, password, type, organization)
            VALUES ('Tom', 'Smith', '1234567892', 'tom@test.com', '123456', 3, 'Consumer');


