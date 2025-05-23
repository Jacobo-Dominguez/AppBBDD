DROP DATABASE IF EXISTS mini_steam; -- USAMOS DROP PARA ELIMINAR LA BASE DE DATOS POR SI EXISTE Y EMPEZAR DE 0
CREATE DATABASE IF NOT EXISTS mini_steam; -- CREAMOS LA BASE DE DATOS
USE mini_steam; -- USAMOS LA BASE

-- Tabla de juegos
CREATE TABLE juegos (
    id_juego INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    ruta_imagen VARCHAR(250),
    genero VARCHAR(50),
    precio FLOAT,
    fecha_lanzamiento DATE
);

-- Tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    tipo VARCHAR(20) -- 'admin' o 'cliente'
);

-- Tabla de compras
CREATE TABLE compras (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_juego INT,
    fecha_compra DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES juegos(id_juego)
);

-- Tabla de reseñas
CREATE TABLE resenas (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_juego INT,
    puntuacion INT,
    comentario TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES juegos(id_juego)
);

-- Datos de ejemplo
INSERT INTO usuarios (nombre, email, tipo) VALUES
('Juan Pérez', 'juan.perez@email.com', 'cliente'),
('María García', 'maria.garcia@email.com', 'cliente'),
('Admin Steam', 'admin@steam.com', 'admin'),
('Carlos López', 'carlos.lopez@email.com', 'cliente'),
('Ana Martínez', 'ana.martinez@email.com', 'cliente'),
('Pedro Sánchez', 'pedro.sanchez@email.com', 'cliente'),
('Sofía Rodríguez', 'sofia.rodriguez@email.com', 'cliente'),
('Luis Fernández', 'luis.fernandez@email.com', 'cliente'),
('Laura Gómez', 'laura.gomez@email.com', 'cliente'),
('David Torres', 'david.torres@email.com', 'admin');

INSERT INTO juegos (nombre, genero, precio, fecha_lanzamiento) VALUES
('The Witcher 3: Wild Hunt', 'RPG', 39.99, '2015-05-18'),
('Counter-Strike: Global Offensive', 'FPS', 0.00, '2012-08-21'),
('Stardew Valley', 'Simulación', 14.99, '2016-02-26'),
('Grand Theft Auto V', 'Acción-Aventura', 29.99, '2015-04-14'),
('Portal 2', 'Puzzle', 9.99, '2011-04-19'),
('Hollow Knight', 'Metroidvania', 14.99, '2017-02-24'),
('The Elder Scrolls V: Skyrim', 'RPG', 19.99, '2011-11-11'),
('Celeste', 'Plataformas', 19.99, '2018-01-25'),
('Rocket League', 'Deportes', 0.00, '2015-07-07'),
('Among Us', 'Multijugador', 4.99, '2018-06-15'),
('Clair Obscur: Expedition 33', 'JRPG', 49.99, '2017-04-23');

INSERT INTO compras (id_usuario, id_juego, fecha_compra) VALUES
(1, 3, '2020-03-15'),
(1, 5, '2020-03-15'),
(2, 1, '2019-07-22'),
(3, 2, '2021-01-10'),
(4, 4, '2018-11-05'),
(5, 6, '2020-09-30'),
(6, 7, '2017-12-25'),
(7, 8, '2019-04-18'),
(8, 9, '2020-07-07'),
(9, 10, '2021-02-14');

INSERT INTO resenas (id_usuario, id_juego, puntuacion, comentario) VALUES
(1, 3, 5, 'Un juego relajante y adictivo. Perfecto para desconectar.'),
(1, 5, 4, 'Muy bueno, pero algo corto. Los puzzles son excelentes.'),
(2, 1, 5, 'La mejor experiencia RPG que he tenido. Historia increíble.'),
(4, 4, 4, 'Gran juego, pero ya es viejo. Necesita una secuela.'),
(5, 6, 5, 'Arte hermoso, jugabilidad desafiante y música increíble.'),
(6, 7, 5, 'Un clásico que nunca envejece. Mods lo hacen infinito.'),
(7, 8, 4, 'Difícil pero satisfactorio. Historia conmovedora.'),
(8, 9, 3, 'Divertido con amigos, pero la comunidad puede ser tóxica.'),
(9, 10, 4, 'Muy divertido en grupo. Ideal para fiestas virtuales.'),
(3, 2, 5, 'El mejor FPS competitivo. Siempre hay partidas disponibles.');
