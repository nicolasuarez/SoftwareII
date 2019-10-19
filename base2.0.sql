CREATE DATABASE desercion;

USE desercion;

CREATE TABLE preguntas (
  IdPre INTEGER NOT NULL,
  ListaPre VARCHAR(100) NOT NULL,
  PRIMARY KEY (IdPre));


CREATE TABLE respuestas (
  IdRes INTEGER,
  ListaRes VARCHAR(100),
  PRIMARY KEY (IdRes));


CREATE TABLE encuestas (
  IdEnc INTEGER NOT NULL,
  IdPre INTEGER NOT NULL,
  IdRes INTEGER NOT NULL,
  PRIMARY KEY (IdEnc),
  KEY IdPre (IdPre),
  KEY IdRes (IdRes),
  FOREIGN KEY (IdPre) REFERENCES preguntas (IdPre),
  FOREIGN KEY (IdRes) REFERENCES respuestas (IdRes)); 


CREATE TABLE usuarios (
  IdUsu INTEGER NOT NULL,
  Usu varchar(20) NOT NULL,
  Contreseña varchar(20) NOT NULL,
  Nombre varchar(20) NOT NULL,
  Apellido varchar(20) NOT NULL,
  Tipo varchar(20) NOT NULL,
  Sexo varchar(20) NOT NULL,
  Correo varchar(20) NOT NULL,
  PRIMARY KEY (IdUsu)); 


CREATE TABLE usuencuesta (
  IdUsuEnc INTEGER NOT NULL,
  IdUsu INTEGER NOT NULL,
  IdEnc INTEGER NOT NULL,
  PRIMARY KEY (IdUsuEnc),
  KEY IdUsu (IdUsu),
  KEY IdEnc (IdEnc),
  FOREIGN KEY (IdUsu) REFERENCES usuarios (IdUsu),
  FOREIGN KEY (IdEnc) REFERENCES encuestas (IdEnc)); 

CREATE TABLE notas (
  IdNot INTEGER NOT NULL,
  Notas double NOT NULL,
  Corte INTEGER NOT NULL,
  PRIMARY KEY (IdNot));

CREATE TABLE materia (
  IdMat INTEGER NOT NULL,
  Nombre varchar(20) NOT NULL,
  Hora time NOT NULL,
  IdNot INTEGER NOT NULL,
  PRIMARY KEY (IdMat),
  KEY IdNot (IdNot),
  FOREIGN KEY (IdNot) REFERENCES notas (IdNot)); 


CREATE TABLE horario (
  IdHor INTEGER NOT NULL,
  IdMat INTEGER NOT NULL,
  PRIMARY KEY (IdHor),
  KEY IdMat (IdMat),
  FOREIGN KEY (IdMat) REFERENCES materia (IdMat)); 


CREATE TABLE historia (
  IdHistoria INTEGER NOT NULL,
  IdUsuEnc INTEGER NOT NULL,
  IdHor INTEGER NOT NULL,
  PRIMARY KEY (IdHistoria),
  KEY IdUsuEnc (IdUsuEnc),
  KEY IdHor (IdHor),
  FOREIGN KEY (IdUsuEnc) REFERENCES usuencuesta (IdUsuEnc),
  FOREIGN KEY (IdHor) REFERENCES horario (IdHor));
