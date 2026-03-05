# ForumHub - API REST de Gerenciamento de Tópicos 🚀

O **ForumHub** é uma API desenvolvida em Java com o framework Spring Boot para gerenciar tópicos de um fórum de discussões. O projeto implementa um CRUD completo (Criar, Ler, Atualizar e Deletar) integrado a um sistema de segurança stateless baseado em tokens JWT.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**: Linguagem principal do projeto.
* **Spring Boot 3.4.3**: Base da aplicação e gerenciamento de dependências.
* **Spring Security & JWT**: Implementação de autenticação e autorização via tokens.
* **PostgreSQL**: Banco de dados relacional para persistência de dados.
* **Flyway**: Controle de versões e migrações do banco de dados.
* **Lombok**: Utilizado para reduzir código boilerplate (Getters, Setters, etc.).
* **Maven**: Automação de build e gerenciamento de dependências.

---

## 📋 Funcionalidades

* **Autenticação**: Endpoint de login que retorna um Token JWT válido por 2 horas.
* **Tópicos**:
    * **Cadastro**: Permite registrar novos tópicos, validando se já existe um título ou mensagem idêntica.
    * **Listagem**: Retorna todos os tópicos de forma paginada e ordenada por data de criação.
    * **Detalhamento**: Consulta de informações completas de um tópico através de seu ID.
    * **Atualização**: Permite editar os dados de um tópico já existente.
    * **Exclusão**: Remove permanentemente um tópico do banco de dados.
* **Tratamento de Erros**: Centralização de exceções para retornar status HTTP padronizados, como 404 (Not Found) e 400 (Bad Request).

---

## 🚦 Endpoints Principais

| Método | Endpoint | Descrição | Requer Auth |
| :--- | :--- | :--- | :--- |
| **POST** | `/login` | Gera o token de acesso | Não |
| **POST** | `/topicos` | Cria um novo tópico | **Sim** |
| **GET** | `/topicos` | Lista tópicos de forma paginada | **Sim** |
| **GET** | `/topicos/{id}` | Detalha um tópico específico | **Sim** |
| **PUT** | `/topicos/{id}` | Atualiza um tópico | **Sim** |
| **DELETE** | `/topicos/{id}` | Exclui um tópico | **Sim** |

---

## ⚙️ Como Configurar o Projeto

1.  **Clone o repositório**:
    ```bash
    git clone [https://github.com/lucas-almeida729/forumhub.git](https://github.com/lucas-almeida729/forumhub.git)
    ```
2.  **Configure o Banco de Dados**:
    No arquivo `src/main/resources/application.properties`, configure as variáveis de ambiente ou altere os valores diretamente para o seu banco PostgreSQL:
    * `spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub_db`
    * `spring.datasource.username=${DB_USER}`
    * `spring.datasource.password=${DB_PASSWORD}`
3.  **Segurança (JWT)**:
    Defina sua chave secreta para a geração dos tokens:
    * `api.security.token.secret=${JWT_SECRET:********}`
4.  **Execute a aplicação**:
    ```bash
    ./mvnw spring-boot:run
    ```

---

## 👤 Autor

Desenvolvido por **Lucas Almeida**. 🚀

---