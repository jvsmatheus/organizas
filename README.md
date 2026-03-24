# Organizas

> 🚧 Em desenvolvimento ativo

API REST para centralizar o dia a dia doméstico em um só lugar — lista de compras compartilhada, controle financeiro e agenda de compromissos.

## Sobre o projeto

O Organizas nasceu de uma necessidade real: ter um único lugar para gerenciar as tarefas domésticas do casal sem depender de múltiplos apps. O backend está sendo construído do zero com Java e Spring Boot, com foco em boas práticas de arquitetura, segurança e código limpo.

## Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4 |
| Segurança | Spring Security + JWT (auth0/java-jwt) |
| Banco de dados | PostgreSQL 16 |
| Migrations | Flyway (container dedicado) |
| Containerização | Docker + Docker Compose |
| ORM | Hibernate / Spring Data JPA |
| Validação | Bean Validation (Jakarta) |
| Email | Spring Mail (Mailtrap) |
| Build | Maven |

## Funcionalidades implementadas

- [x] Cadastro de usuário com validação de senha forte (regex customizado)
- [x] Confirmação de cadastro por email
- [x] Autenticação via JWT (stateless)
- [x] Recuperação de senha com token de uso único
- [x] Tratamento de erros padronizado (4 handlers ordenados por prioridade)
- [x] Resposta padronizada em todos os endpoints (`ResponseBase<T>`)
- [ ] Lista de compras compartilhada
- [ ] Controle financeiro
- [ ] Agenda de compromissos

## Arquitetura

O projeto segue arquitetura em camadas:
```
Controller → Service → Repository → Entity
```

Decisões técnicas relevantes:

- **Flyway como container separado** — as migrations rodam antes da aplicação subir via `depends_on` + `healthcheck`, sem que a aplicação precise de permissão de DDL no banco
- **Profiles Spring Boot** — `dev` (PostgreSQL local) e `prod` (PostgreSQL via Docker Compose completo)
- **Tratamento de exceções com `@Order`** — handlers organizados por prioridade: validação (2), negócio (3), genérico (99)

## Como rodar localmente

### Pré-requisitos

- Docker e Docker Compose instalados
- Java 21
- Maven

### 1. Clone o repositório
```bash
git clone https://github.com/jvsmatheus/organizas.git
cd organizas
```

### 2. Configure as variáveis de ambiente
```bash
cp .env.exemple .env
```

Edite o `.env` com suas credenciais:
```env
POSTGRES_DB=organizas
POSTGRES_USER=seu_usuario
POSTGRES_PASSWORD=sua_senha
POSTGRES_PORT=5432
DB_URL=jdbc:postgresql://localhost:5432/organizas
SECRET_KEY=sua_chave_secreta_jwt
EXPIRATION_DATE=86400000
BASE_URL=http://localhost:8080
MAILTRAP_HOST=sandbox.smtp.mailtrap.io
MAILTRAP_PORT=2525
MAILTRAP_USERNAME=seu_usuario_mailtrap
MAILTRAP_PASSWORD=sua_senha_mailtrap
```

### 3. Suba o banco e rode as migrations
```bash
docker-compose -f docker-compose-dev.yaml up -d
```

Aguarde o Flyway aplicar as migrations. Verifique com:
```bash
docker logs organizas_migrations_dev
```

Você deve ver: `Successfully applied X migrations`.

### 4. Suba a aplicação

Pela IDE com a variável de ambiente:
```
SPRING_PROFILES_ACTIVE=dev
```

Ou via terminal:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints disponíveis

### Autenticação (`/auth`)

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/auth/login` | Login e geração de token JWT | ❌ |
| `GET` | `/auth/verify-email` | Confirmação de email via token | ❌ |
| `POST` | `/auth/forgot-password` | Solicitar redefinição de senha | ❌ |

### Usuário (`/user`)

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/user` | Cadastro de novo usuário | ❌ |

> Endpoints marcados com ✅ exigem header `Authorization: Bearer <token>`.

## Formato de resposta

Todos os endpoints retornam o mesmo envelope:
```json
{
  "timestamp": "2025-06-01T12:00:00Z",
  "status": 200,
  "path": "/auth/login",
  "method": "POST",
  "message": "mensagem descritiva",
  "data": { }
}
```

## Rodando os testes
```bash
./mvnw test
```

Os testes usam H2 em memória via `@ActiveProfiles("test")` — sem necessidade de banco externo.

## Estrutura do projeto
```
src/
├── controllers/       # Endpoints REST
├── services/          # Regras de negócio
├── repositories/      # Acesso ao banco (Spring Data JPA)
├── entities/          # Entidades JPA
├── dto/
│   ├── request/       # DTOs de entrada
│   ├── response/      # DTOs de saída
│   └── errors/        # DTOs de erro
├── security/          # Configuração Spring Security + JWT
├── exceptions/
│   ├── exceptions/    # Exceções de domínio
│   └── handlers/      # @ControllerAdvice por prioridade
├── validation/        # Validações customizadas (@StrongPassword)
└── utils/             # JwtUtils, BuildResponse
resources/
└── db/migration/      # Migrations Flyway (V1 a V5)
```

## Autor

**Matheus Santos**
- GitHub: [@jvsmatheus](https://github.com/jvsmatheus)