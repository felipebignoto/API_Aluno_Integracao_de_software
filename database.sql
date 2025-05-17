-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS uniacademia;

USE uniacademia;

-- Criação da tabela alunos
CREATE TABLE IF NOT EXISTS alunos (
    matricula INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT,
    email VARCHAR(100)
);
