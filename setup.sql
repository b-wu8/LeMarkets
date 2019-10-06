DROP DATABASE IF EXISTS markets;
CREATE DATABASE markets;
CONNECT markets;

CREATE TABLE markets(
	id INTEGER PRIMARY KEY,
	name VARCHAR(255),
	web VARCHAR(255),
	address VARCHAR(255),
	city VARCHAR(63),
	county VARCHAR(31),
	state VARCHAR(31),
	zip INTEGER,
	session VARCHAR(63),
	credit CHAR(1),
	bake CHAR(1),
	cheese CHAR(1),
	crafts CHAR(1),
	flowers CHAR(1),
	eggs CHAR(1),
	seafood CHAR(1),
	herbs CHAR(1),
	vegetables CHAR(1),
	honey CHAR(1),
	jams CHAR(1),
	meat CHAR(1),
	wine CHAR(1),
	coffee CHAR(1),
	fruits CHAR(1),
	wildHarvest CHAR(1),
	x FLOAT,
	y FLOAT
);

CREATE TABLE zips(
	zip INTEGER PRIMARY KEY,
	x FLOAT,
	y FLOAT,
	city VARCHAR(63),
	state VARCHAR(31)
);

CREATE TABLE states(
	state VARCHAR(31),
	abb VARCHAR(2)
);

