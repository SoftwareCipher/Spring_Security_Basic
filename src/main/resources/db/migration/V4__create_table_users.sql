create table users
(
    id       bigserial,
    username varchar(255),
    password varchar(255) not null,
    role_id  bigint       not null,
    constraint pk_users primary key (id),
    constraint fk_users foreign key (role_id) references roles(id) on delete cascade
);