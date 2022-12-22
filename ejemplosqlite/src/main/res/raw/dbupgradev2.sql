create table telefonos (
    numero integer not null,
    descripcion varchar,
    nif char(9),
    constraint PK_TELEFONOS primary key (nif, numero),
    constraint FK_TELEFONOS_CLIENTES foreign key (nif) references clientes (nif)
)