# Insurance Onboarding

Este repositório contém dois serviços Spring Boot (Java 21):

* **customer-service** (API de cadastro de clientes)
* **insurance-service** (API de simulação e contratação de seguros)

---

## Pré-requisitos

* Java 21
* Maven
* (Opcional) Docker & Docker Compose

---

## Executando com Docker

1. Clone o repositório:

   ```bash
   git clone git@github.com:xandroalmeida/insurance-onboarding.git
   cd insurance-onboarding
   ```
2. Suba os containers:

   ```bash
   docker-compose up --build
   ```
3. Acesse as APIs:

    * Customer: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
    * Insurance: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

---

## Executando sem Docker

### 1. Banco de dados

Instale e rode um PostgreSQL local (porta 5432) com:

* Banco: `onboarding`
* Usuário: `postgres`
* Senha: `postgres`

### 2. Build e run dos serviços

No diretório do projeto, abra dois terminais:

#### Customer Service

```bash
cd customer-service
mvn clean package
java -jar target/customer-service-*.jar
```

#### Insurance Service

```bash
cd insurance-service
mvn clean package
java -jar target/insurance-service-*.jar
```

As APIs estarão disponíveis em:

* Customer: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
* Insurance: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

