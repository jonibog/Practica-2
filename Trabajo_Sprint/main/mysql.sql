CREATE DATABASE tu_base_de_datos;

USE tu_base_de_datos;

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    correo VARCHAR(255),
    contraseña VARCHAR(255)
);

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    precio DOUBLE,
    stock INT
);

INSERT INTO usuario (nombre, correo, contraseña) VALUES
('Juan Perez', 'juan@example.com', '123456'),
('Maria Gomez', 'maria@example.com', 'password123');

INSERT INTO producto (nombre, precio, stock) VALUES
('Martillo Mediano', 50, 6200),
('Destornillador Phillips Largo', 100, 3900),
('Llave Inglesa 25mm', 50, 21500),
('Sierra Circular 600HP', 10, 214200),
('Cinta Métrica 5 M', 50, 5000),
('Tornillo Para Madera Autoperforante', 10000, 10),
('Tuerca M8', 10000, 225),
('Serrucho de Carpintero 25mm', 50, 16900),
('Clavos De 2 1/2" Caja 5 Kg', 10, 12000),
('Cadena de Acero x 1 M', 10, 1200),
('Tornillo Hexagonal M10', 10000, 56);