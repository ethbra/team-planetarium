drop table if exists moons cascade;
drop table if exists planets cascade;
drop table if exists users cascade;

create table users(
    id serial primary key,
    username text unique not null,
    password text not null,
    constraint username_length_check check (
        length(username) >= 5 and
        length(username) <= 30
        ),
    constraint password_length_check check (
        length(password) >= 5 and
        length(password) <= 30
        ),
    constraint username_character_check check (
        username ~ '^[a-zA-Z][a-zA-Z0-9_-]*$'
    ),
    constraint password_character_check check (
        password ~ '.*[a-z].*' and
        password ~ '.*[A-Z].*' and
        password ~ '.*[0-9].*' and
        password ~ '^[a-zA-Z0-9_-]*$'
    )
);

insert into users (username, password) values ('Batman', 'Iamthenight1939');

create table planets(
    id serial primary key,
    name text unique not null,
    ownerId integer not null,
    image bytea,
    foreign key(ownerId) references users(id) on delete restrict,
    constraint name_length_check check (length(name) <= 30),
    constraint name_character_check check (
        name ~ '^[a-zA-Z0-9_ -]*$'
        )
);

insert into planets (name, ownerId, image) values ('Earth', 1, null);
insert into planets (name, ownerId, image) values ('Mars', 1, null);

create table moons(
    id serial primary key,
    name text not null unique,
    myPlanetId integer not null,
    image bytea,
    foreign key(myPlanetId) references planets(id) on delete cascade,
    constraint name_length_check check (length(name) <= 30),
    constraint name_character_check check (
        name ~ '^[a-zA-Z0-9_ -]*$'
    )
);

insert into moons (name, myPlanetId, image) values ('Luna', 1, null);
insert into moons (name, myPlanetId, image) values ('Titan', 2, null);
