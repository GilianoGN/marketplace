# Marketplace Modular System
### 🚀 Sobre o Projeto
Este projeto é uma arquitetura de referência de um __Marketplace Modular__ desenvolvido em Java com Spring Boot. A aplicação foi projetada para demonstrar a separação de contextos (_Bounded Contexts_) em uma arquitetura monolítica modular, utilizando diferentes tecnologias de persistência para atender às necessidades específicas de cada domínio (SQL, NoSQL e Cache).

O sistema é dividido em três domínios principais:

* __Catalog__: Gerenciamento de eventos e catálogo de produtos (MongoDB + Redis).
* __Registration__: Cadastro de clientes e endereços (MySQL + Spring Data REST).
* __Ticketing__: Gestão de ingressos e seleção de assentos (PostgreSQL + Redis).

---
### 🛠 Tecnologias Utilizadas
* __Linguagem__: Java 21
* __Framework__: Spring Boot 3.4.2
* __Persistência__:
    * _JPA/Hibernate_ (MySQL, PostgreSQL)
    * _Spring Data MongoDB_
    * _Spring Data Redis_ (Caching)
* __Documentação__: _SpringDoc OpenAPI_ (Swagger) com suporte a múltiplos grupos.
* __Ferramentas__: _Lombok_, _Spring HATEOAS_, _Maven_.

---
### 🏗 Arquitetura do Sistema
O sistema implementa o conceito de Modular Monolith, onde cada módulo possui seu próprio _EntityManager_, _DataSource_ e configurações de infraestrutura isoladas, garantindo baixo acoplamento e alta coesão.

---
### 📋 Funcionalidades Principais
* __Isolamento de Dados__: Cada módulo gerencia seu próprio ciclo de vida de persistência.
* __Documentação Interativa__: API documentada por módulo, permitindo a exploração individual de cada domínio via Swagger UI.
* __Caching de Alta Performance__: Utilização de Redis para otimizar consultas frequentes nos módulos de Catalog e Ticketing.
* __Event-Driven__ (__Preliminar__): Uso de _Spring Data REST Events_ para sincronização e publicação de eventos internos.

---
### 🚀 Como Executar
#### Pré-requisitos
* Java 21 instalado.
* Docker instalado (para subir os bancos de dados).
* Maven.

#### Passos para rodar
1. Clone o repositório:
```
Bash
git clone https://github.com/GilianoGN/marketplace.git
```

2. Inicie os bancos de dados (via Docker Compose ou localmente):

    * MySQL (portas 3307 e 3308)
    * PostgreSQL (porta 5433)
    * MongoDB (porta 27018)
    * Redis (porta 6380 e 6382)

3. Compile e execute o projeto:
```
Bash
mvn clean install
mvn spring-boot:run
```

4. Acesse a documentação da API em: http://localhost:8080/swagger-ui.html

---
### 🧪 Estrutura de Documentação (Swagger)
O projeto utiliza grupos OpenAPI para separar os domínios. Ao acessar o Swagger, utilize o seletor no canto superior direito para alternar entre:
* __Catalog__
* __Registration__
* __Ticketing__

---
### 📚 Lições Aprendidas
Este projeto foi um desafio técnico gratificante, permitindo o aprofundamento em:
* Configuração avançada de múltiplos _LocalContainerEntityManagerFactoryBean_.
* Resolução de conflitos de _Beans_ em contextos complexos do Spring.
* Integração poliglota (SQL/NoSQL) em uma única aplicação.

---
### ✒️ Autor
Giliano GN - https://github.com/GilianoGN