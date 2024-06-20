DROP DATABASE almacen;
CREATE DATABASE almacen;

USE almacen;

CREATE TABLE CajaAhorro (
	idCajaAhorro int PRIMARY KEY,
    saldo DOUBLE NOT NULL
);

CREATE TABLE Cliente (
    idCliente INT PRIMARY KEY,
    nombre VARCHAR (50),
    apellido VARCHAR (50),
    email VARCHAR(100),
    telefono VARCHAR(20)
  );

CREATE TABLE Proveedor (
    idProveedor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cuit VARCHAR(20),
    nombreProveedor VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE Empleado (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50),
    contraseña VARCHAR(50),
    dni VARCHAR(20),
    nombre VARCHAR (50),
    apellido VARCHAR (50),
    email VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE Producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(100),
    precioProducto DOUBLE,
    stockProducto INT
);

CREATE TABLE Compra (
    idCompra INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    idProveedor INT,
    idProducto INT,
    cantidadCompra INT,
    precioCompra DOUBLE,
    FOREIGN KEY (idProveedor) REFERENCES Proveedor(idProveedor),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

CREATE TABLE Venta (
    idVenta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    idProducto INT,
    idCliente INT,
    cantidadVenta INT,
    precioVenta DOUBLE,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto),
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

