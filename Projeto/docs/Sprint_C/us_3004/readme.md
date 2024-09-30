# US 3004

Este documento contém a documentação relativa à US 3004.

## 1. Contexto

Esta *User Story (US)* foi introduzida neste *sprint* para ser desenvolvida seguindo as boas práticas de engenharia de
*software*, além disso o desenho e a implementação da solução devem ser baseados em *threads*, variáveis de condição e mutexes.
Esta *US* faz parte da disciplina de **EAPLI** e **SCOMP**.

## 2. Requisitos

**US 3004** - As User, I want to share a board

A respeito deste requisito, entendemos que o utilizador deve selecionar uma das board ativas em que é dono para escolher os utilizadores a partilhar.

### 2.1. Complementos encontrados

Não existem *User Stories* complementares.

### 2.2. Dependências encontradas

- **US 3002** - As User, I want to create a board.

  **Explicação:** Se não existir uma *board*, não é possível partilhá-la.

### 2.3. Critérios de aceitação

**CA 1:** This functional part of the system has very specific technical requirements, particularly some concerns about
synchronization problems.
In fact, several clients will try to concurrently update boards.
As such, the solution design and implementation must be based on threads, condition variables and mutexes. Specific
requirements will be provided in SCOMP.

## 3. Análise

### 3.1. Respostas do cliente

Não foi necessário questionar o cliente em função da realização desta *User Story (US)*.

### 3.2. Diagrama de Sequência do Sistema

![Diagrama de Sequência do sistema](./SVG/system-sequence-diagram.svg)

### 3.3. Classes de Domínio

![Diagrama de Classes de Domínio](SVG/domain-classes.svg)

## 4. Design

### 4.1. Diagrama de Sequência
![sequence diagram](/SVG/sequence-diagram.svg)

### 4.2. Diagrama de Classes

![a class diagram](SVG/class-diagram.svg)

### 4.3. Padrões Aplicados

|                  Questão: Que classe...                   |       Resposta       |        Padrão        |                                                                Justificação                                                                 |
|:---------------------------------------------------------:|:--------------------:|:--------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------:|
|       é responsável por interagir com o utilizador?       |     ShareBoardUI     |  *Pure Fabrication*  |                        Não há razão para atribuir esta responsabilidade a uma classe presente no Modelo de Domínio.                         |
|       é responsável por coordenar a funcionalidade?       | ShareBoardController |     *Controller*     |                                                                                                                                             |
|   é responsável por criar todas as classes Repository?    |  RepositoryFactory   |      *Factory*       |                               Quando uma entidade é demasiado complexa, as fábricas fornecem encapsulamento.                                |
| é responsável por persistir os cursos criadas/utilizadas? |   BoardRepository    |     *Repository*     | Quando se pretende ocultar os detalhes de persistência/reconstrução de objetos cria-se uma classe Repository responsável por essas tarefas. |
|   é responsável por saber todos os dados de uma board?    |        Board         | *Information Expert* |                                             Sabe toda a informação dos dados que lhe pertencem.                                             |


### 4.4. Testes


## 5. Implementação

## 5.1. Arquitetura em Camadas

### Domínio

Na camada de domínio utilizou-se a entidade abstrata *Board* e os respetivos value objects.

### Aplicação

Na camada de aplicação criou-se o controller *ShareBoardController*.

### Repositório

Na camada de repositório utilizou-se a interface *BoardRepository* que é implementada em *JPA* e *InMemory* no módulo de *impl*.

### Apresentação

Nesta camada foi desenvolvida a *ShareBoardUI* que faz a interação entre o professor e o sistema.

## 5.2. Commits Relevantes

[Listagem dos Commits realizados](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-22-23-20/issues/38)

## 6. Integração/Demonstração

No menu da aplicação cliente foi adicionado a opção *Share a board*.

## 7. Observações

Não existem observações relevantes a acrescentar.