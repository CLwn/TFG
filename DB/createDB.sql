CREATE DATABASE webserver;

CREATE TABLE user{
  iduser int,
  username varchar(25),
  PRIMARY KEY (iduser)
};

CREATE TABLE role{
  idrole int,
  role varchar(25),
  description varchar(25),
  PRIMARY KEY (idrole)
};

CREATE TABLE pass{
  idpass int,
  password varchar(200),
  PRIMARY KEY (idpass)

}

CREATE VIEW user_role AS,


INSERT INTO user (username) VALUES ('pepe');
INSERT INTO user (username) VALUES ('juan');
INSERT INTO user (username) VALUES ('juan');

INSERT INTO pass (username, password) VALUES ('admin');

INSERT INTO role (role, description) VALUES ('admin', 'admin');
INSERT INTO role (role, description) VALUES ('user', 'user');
