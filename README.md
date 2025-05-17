# API Aluno - Integração de Software

Este é um projeto RESTful Web Service para gerenciamento de alunos.

## Pré-requisitos

- NetBeans IDE
- XAMPP (Apache e MySQL)
- Postman (para testes de API)

## Configuração do Ambiente

1. Clone o repositório
2. Abra o projeto no NetBeans
3. Inicie o XAMPP e ative:
   - Apache Server
   - MySQL Server
4. Execute o script SQL para criar o banco de dados e tabelas (arquivo `database.sql`)

## Estrutura do Banco de Dados

Database: `uniacademia`
Tabela: `alunos`

- matricula (INT, Primary Key)
- nome (VARCHAR(100))
- idade (INT)
- email (VARCHAR(100))

## Executando o Projeto

1. Abra o projeto no NetBeans
2. Execute o projeto (clique direito no projeto > Run)
3. O serviço estará disponível em `http://localhost:8080/[nome-do-projeto]`

## Testando a API

Use o Postman para testar os endpoints:

- GET: `/alunos` - Lista todos os alunos
- GET: `/alunos/{matricula}` - Busca um aluno específico
- POST: `/alunos` - Cria um novo aluno
- PUT: `/alunos/{matricula}` - Atualiza um aluno
- DELETE: `/alunos/{matricula}` - Remove um aluno

Exemplos de requisições estão disponíveis na pasta `/postman` do projeto.

# Vídeo de demonstração do funcionamento

https://www.loom.com/share/b85d9218c8514a9bb58b9a1c050d6864?sid=b570b1ca-17b3-4af2-8414-062a44a4e22d
