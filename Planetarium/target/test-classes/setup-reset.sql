-- Use this script to set up your Planetarium database: do not edit the script

-- needed for referential integrity enforcement if executing the queries manually
PRAGMA foreign_keys = ON;

drop table if exists moons;

drop table if exists planets;

drop table if exists users;

create table users(
	id integer primary key,
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
		username GLOB '[a-zA-Z]*' AND 
		username not glob '*[^a-zA-Z0-9_-]*'
	),
	constraint password_character_check check (
		password GLOB '*[a-z]*' and
		password GLOB '*[A-Z]*' and 
		password GLOB '*[0-9]*' and
		password GLOB '[a-zA-Z]*' AND 
		username not glob '*[^a-zA-Z0-9_-]*'
	)
);

insert into users (username, password) values ('Batman', 'Iamthenight1939');

create table planets(
	id integer primary key,
	name text unique not null,
	ownerId integer not null,
	image blob,
	foreign key(ownerId) references users(id) on delete restrict,
	constraint name_length_check check (length(name) <= 30),
	constraint name_character_check check (
		name GLOB '[a-zA-Z]*' and
		name not GLOB '*[^a-zA-Z0-9_ -]*'
	)
);

insert into planets (name, ownerId, image) values ('Earth', 1, ?);
insert into planets (name, ownerId, image) values ('Mars', 1, ?);

create table moons(
	id integer primary key,
	name text not null,
	myPlanetId integer not null,
	image blob,
	foreign key(myPlanetId) references planets(id) on delete cascade,
	constraint name_length_check check (length(name) <= 30),
	constraint name_character_check check (
		name GLOB '[a-zA-Z]*' AND
		name not GLOB '*[^a-zA-Z0-9_ -]*'
	)
);

insert into moons (name, myPlanetId, image) values ('Luna', 1, ?);
insert into moons (name, myPlanetId, image) values ('Titan', 2, ?);