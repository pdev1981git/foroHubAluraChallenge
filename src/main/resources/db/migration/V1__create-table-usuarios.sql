create table usuarios (
        id bigint not null auto_increment,
        clave varchar(100) not null,
        login varchar(300) not null,
        primary key (id)
    );