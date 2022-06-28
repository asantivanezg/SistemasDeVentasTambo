-- Creacion de la base de datos
create database bd_sistema_ventas;

-- Siempre que quieras ver una tabla "select * from..." ejecuta antes el "use"
use bd_sistema_ventas;

-- crear tabla usuario
create table tb_usuario(
idUsuario int (11) auto_increment primary key,
nombre varchar (30) not null,
apellido varchar (30) not null,
usuario varchar (15) not null,
password varchar (15) not null,
telefono varchar (9) not null,
estado int (1) not null
);
-- Ver todas las tabla
show tables;
-- Ver solo la tabla nombrada
select * from tb_usuario;
-- Insertar un usuario
insert into tb_usuario(nombre, apellido, usuario, password, telefono, estado )
values("Miguel", "Eguiguren", "miguel", "123456", "922031723", 1);

select usuario, password from tb_usuario where usuario = "miguel" and password = "123456";



-- creacion Tabla Cliente 
create table tb_cliente(
idCliente int (11) auto_increment primary key,
nombre varchar (30) not null,
apellido varchar (30) not null,
dni varchar (8) not null,
telefono varchar (9) not null,
direccion varchar (100) not null,
estado int (1) not null
);
select * from tb_cliente;
show tables;

-- creacion Tabla Categoria 
create table tb_categoria(
idCategoria int (11) auto_increment primary key,
descripcion varchar (200) not null,
estado int (1) not null
);
select * from tb_categoria;
select descripcion from tb_categoria where descripcion = '';
-- Para vaciar la tabla
truncate table tb_categoria;


-- creacion Tabla Producto 
create table tb_producto(
idProducto int (11) auto_increment primary key,
nombre varchar (100) not null,
cantidad int (11) not null,
precio double (10,2) not null,
descripcion varchar (200) not null,
porcentajeIgv int (2) not null,
idcategoria int (11) not null,
estado int (1) not null
);
select * from tb_producto;
select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIgv, c.descripcion, p.estado from tb_producto As p, tb_categoria As c where p.idcategoria = c.idCategoria;




-- creacion Tabla Cabecera de ventas 
create table tb_cabecera_ventas(
idCabeceraVenta int (11) auto_increment primary key,
idCliente int (11) not null,
valorPagar double (10,2) not null,
fechaVenta date not null,
estado int (1) not null
);

-- creacion Tabla Detalle de ventas 
create table tb_detalle_ventas(
idDetalleVenta int (11) auto_increment primary key,
idCabeceraVenta int (11) not null,
idProducto int (11) not null,
cantidad int (11) not null,
precioUnitario double (10,2) not null,
subtotal double (10,2) not null,
descuento double (10,2) not null,
igv double (10,2) not null,
totalPagar double (10,2) not null,
estado int (1) not null
);


