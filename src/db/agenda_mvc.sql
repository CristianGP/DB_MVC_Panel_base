CREATE DATABASE agenda_mvc;

USE agenda_mvc;

CREATE TABLE contactos( 
    id_contacto integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    email varchar(50) NOT NULL
    telefono varchar(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO contactos (nombre, email) VALUES 
('Dejah Thoris','dejah@barson.ma''7712345678'), 
('Jhon Carter','jhon@barson.ma''7712345678'),
('Carthoris Carter','carthoris@barson.ma''7712345678');


SELECT * FROM contactos;

CREATE USER 'user_mvc'@'localhost' IDENTIFIED BY 'pass_mvc.2018';
GRANT ALL PRIVILEGES ON agenda_mvc.* TO 'user_mvc'@'localhost';
FLUSH PRIVILEGES;
