create table clientes (
    nif char(9) primary key,
    nombre varchar(50) not null,
    fecha date not null,
    direccion varchar(50),
    estudiante integer not null,
    sexo char(6) not null
);

create table tarjetas (
    nif char(9),
    numero char(16) not null,
    mes integer not null,
    año integer not null,
    cvc integer not null,
    primary key (nif, numero),
    foreign key (nif) references clientes (nif)
);

create table telefonos (
    numero integer not null,
    descripcion varchar,
    nif char(9),
    constraint PK_TELEFONOS primary key (nif, numero),
    constraint FK_TELEFONOS_CLIENTES foreign key (nif) references clientes (nif)
)