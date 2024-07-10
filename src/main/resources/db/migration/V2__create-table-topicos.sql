create table topicos (
        id bigint not null auto_increment,
        estado varchar(100) not null,
        fecha_creacion datetime(6) not null,
        is_activo bit not null,
        mensaje varchar(2000) not null,
        nombre_curso varchar(255) not null,
        titulo varchar(255) not null,
        usuario_id bigint not null,
        primary key (id),
        foreign key (usuario_id) references usuarios (id)
    );