 -- DROP DATABASE bancoES;

CREATE DATABASE if not exists bancoES;
use bancoES;

CREATE TABLE if not exists CuentasBancarias (
 nCuenta CHAR(10),
 nombre VARCHAR(20),
 nif VARCHAR(10),
 donaciones DOUBLE,
 CONSTRAINT PK_CuentaBancaria PRIMARY KEY (nCuenta,nif));

CREATE TABLE if not exists Movimientos (
fecha DATETIME PRIMARY KEY,
nCuentaMov CHAR(10), 
nifMov VARCHAR(10),
cantidad DOUBLE, 
motivo VARCHAR(50), 
tipo CHAR(1), 
saldo DOUBLE,
CONSTRAINT FK_CuentaBancaria FOREIGN KEY (nCuentaMov, nifMov) REFERENCES CuentasBancarias(nCuenta,nif));

-- INSERT INTO CuentasBancarias (nCuenta, nombre, nif, donaciones)
   -- VALUES ("Peter", "Rabbit", "peter@rabbit.com", "555-6666", "2002-06-24");
    
-- INSERT INTO Movimientos (fecha, nCuentaMov, nifMov, cantidad, motivo, tipo, saldo)
   -- VALUES ("Peter", "Rabbit", "peter@rabbit.com", "555-6666", "2002-06-24");