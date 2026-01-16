# <img src="./src/main/resources/images/ART Music logo.png" width="25" align="center" style="margin: 0 0px 10px 0;"> Art Music

**Art Music** é uma aplicação desktop desenvolvida em Java para a gestão e compra de bilhetes de concertos e eventos musicais. O projeto utiliza uma interface gráfica construída com Java Swing e conecta-se a uma base de dados MySQL para persistência de dados.

<p align="center">
  <img src="./src/main/resources/images/tela%20apresentacao.png" width="400">
</p>

## 🚀 Funcionalidades

- **Autenticação de Utilizadores**: Sistema de Login e Registo de novos utilizadores.
- **Gestão de Perfil**: Visualização e edição de dados pessoais (Nome, Email, Endereço, etc.).
- **Catálogo de Shows**: Visualização de eventos disponíveis com detalhes (Data, Local, Preço, Artista).
- **Compra de Bilhetes**: Sistema para adquirir bilhetes, validando o saldo disponível na carteira virtual do utilizador.
- **Carteira Virtual**: Funcionalidade para adicionar saldo à conta do utilizador.
- **Persistência de Sessão**: Funcionalidade "Lembrar de mim" para guardar o último utilizador logado.
- **Gestão de Entidades**: Estrutura MVC com DAOs para Clientes, Eventos, Artistas e Participações.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java 21.
- **Gestor de Dependências**: Maven.
- **Interface Gráfica**: Java Swing.
- **Base de Dados**: MySQL.
- **Drivers & Bibliotecas**:
- `mysql-connector-j` (Conexão JDBC).
- `dotenv-java` (Gestão de variáveis de ambiente).

## 📋 Pré-requisitos

Para rodar este projeto, necessita de ter instalado na sua máquina:

1. **Java JDK 21** ou superior.
2. **Maven** (para compilar o projeto).
3. **MySQL Server** (local ou remoto).

## ⚙️ Configuração e Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/o-seu-usuario/artmusic.git
cd artmusic

```

### 2. Configurar a Base de Dados

O projeto não cria as tabelas automaticamente. Deverá criar uma base de dados no MySQL e criar as tabelas necessárias (`Cliente`, `Evento`, `Artista`, `Participacao`) compatíveis com os modelos encontrados no pacote `model`.

### 3. Variáveis de Ambiente (.env)

O projeto utiliza um ficheiro `.env` para gerir as credenciais da base de dados.

1. Localize o ficheiro `.env.example` na raiz do projeto.
2. Renomeie-o para `.env` ou crie um novo ficheiro `.env` com o seguinte conteúdo:

```properties
DB_URL=jdbc:mysql://localhost:3306/nome_da_sua_base_de_dados
DB_USER=o_seu_usuario
DB_PASSWORD=a_sua_senha

```

_Certifique-se de substituir os valores pelos da sua configuração local._

## ▶️ Como Executar

### Via Terminal (Maven)

1. **Compilar o projeto:**
   Este comando irá baixar as dependências e compilar o código.

```bash
mvn clean package

```

2. **Executar a aplicação:**
   O ficheiro `.jar` será gerado na pasta `target`. Execute-o com o comando:

```bash
java -jar target/ArtMusic2-0.0.1-SNAPSHOT-jar-with-dependencies.jar

```

### Via IDE (Eclipse, IntelliJ, VS Code)

1. Importe o projeto como um **Maven Project**.
2. Aguarde a indexação e download das dependências.
3. Navegue até `src/main/java/view/Main.java`.
4. Execute o método `main`.

## 📂 Estrutura do Projeto

```
src/main/java
├── controller   # Data Access Objects (DAO) e conexão SQL
├── model        # Classes de modelo (POJOs)
├── utils        # Utilitários (Carregadores de ficheiros, constantes, segurança)
└── view         # Interface Gráfica (JFrames e JPanels)
    └── JPanels  # Painéis modulares da interface

```

## 👥 Autores

- **Guilherme Roesler**
- **Andrei Baques Borges**

---

_Este projeto foi desenvolvido para fins educativos._
