create table comentarios (
        id bigint not null auto_increment,
        respuesta varchar(2000) not null,
        topico_id bigint not null,
        usuario_id bigint not null,
        primary key (id),
        foreign key (topico_id) references topicos (id),
        foreign key (usuario_id) references usuarios (id)
    )