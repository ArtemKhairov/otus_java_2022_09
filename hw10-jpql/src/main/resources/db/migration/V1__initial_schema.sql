--drop table flyaway_schema_history cascade;
--drop table if exists client cascade;
--drop table if exists address cascade;
--drop table if exists phone cascade;

-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 1 increment by 1;

create table address
(
    id   bigint not null primary key,
    street varchar(50)
);


create table client
(
    id   bigint not null primary key,
    name varchar(50),
    address_id bigint references address(id)
);


create table phone
(
    id   bigint not null primary key,
    number varchar(50),
    client_id bigint references client(id)
);

);