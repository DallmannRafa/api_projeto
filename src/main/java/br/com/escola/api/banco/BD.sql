CREATE DATABASE escola;

CREATE TABLE aluno (
 ID_ALUNO int(11) NOT NULL AUTO_INCREMENT,
 NOME_ALUNO varchar(50) NOT NULL,
DATA_NASC_ALUNO varchar(10) NOT NULL,
 STATUS_ALUNO varchar(15) NOT NULL,
 PRIMARY KEY (ID_ALUNO)
 );
CREATE TABLE curso (
 ID_CURSO int(10) NOT NULL AUTO_INCREMENT,
 NOME_CURSO varchar(50) NOT NULL,
 CARGA_HORARIO varchar(50) NOT NULL,
 ID_TURMA varchar(45) DEFAULT NULL,
 STATUS_CURSO varchar(15) NOT NULL,
 PRIMARY KEY (ID_CURSO)
 );
CREATE TABLE professor (
 ID_PROFESSOR int(11) NOT NULL AUTO_INCREMENT,
 NOME_PROFESSOR varchar(50) NOT NULL,
 EMAIL_PROFESSOR varchar(50) NOT NULL,
 STATUS_PROFESSOR varchar(15) NOT NULL,
 PRIMARY KEY ( ID_PROFESSOR )
 );
 CREATE TABLE turma (
 ID_TURMA int(11) NOT NULL AUTO_INCREMENT,
 PRIMARY KEY (ID_TURMA),
 ID_CURSO int(11) NOT NULL,
 ID_PROFESSOR INT NOT NULL,
 ID_ALUNO INT (11) NOT NULL,
 DATA_INICIO_TURMA varchar(10) NOT NULL,
 DATA_FINAL_TURMA varchar(10) NOT NULL,
 STATUS_TURMA varchar(15) NOT NULL,
 foreign key (ID_PROFESSOR) REFERENCES professor (ID_PROFESSOR),
 foreign key (ID_ALUNO) REFERENCES aluno (ID_ALUNO),
 foreign key (ID_CURSO) REFERENCES curso (ID_CURSO)
 );