DROP DATABASE if exists bancoES;
CREATE DATABASE if not exists bancoES;
use bancoES;

CREATE TABLE if not exists CuentasBancarias (
 nCuenta CHAR(10),
 nombre VARCHAR(20),
 nif VARCHAR(10),
 donaciones DOUBLE,
  saldo DOUBLE,

 CONSTRAINT PK_CuentaBancaria PRIMARY KEY (nCuenta,nif));

CREATE TABLE if not exists Movimientos (
fecha DATETIME,
nCuentaMov CHAR(10), 
nifMov VARCHAR(10),
cantidad DOUBLE, 
motivo VARCHAR(50), 
tipo CHAR(1), 
PRIMARY KEY (nCuentaMov,nifMov,cantidad),
CONSTRAINT FOREIGN KEY (nCuentaMov) REFERENCES CuentasBancarias(nCuenta));


