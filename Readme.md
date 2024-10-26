# Meu Projeto Java com Docker

Este projeto contém uma aplicação Java que se comunica com um banco de dados MySQL, tudo gerenciado com Docker. 

## Estrutura do Projeto
meu-projeto/ │ ├── app/ │ ├── Dockerfile │ └── src/ │ └── Main.java └── docker-compose.yml

## Pré-requisitos

- [Docker](https://www.docker.com/get-started) (versão 20.10 ou superior)
- [Docker Compose](https://docs.docker.com/compose/) (versão 1.27 ou superior)

## Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/viniciusPsilva/GerenciamentoDeFinancas
   cd GerenciamentoDeFinancas

2. Construa a aplicação Java: Certifique-se de que o arquivo JAR da sua aplicação esteja disponível em app/target/app.jar. Você pode usar ferramentas como Maven ou Gradle para isso.

3. Inicie os containers: Execute o seguinte comando para construir e iniciar os containers:
    ```bash
   docker-compose up --build

4. Acesse a aplicação: A aplicação estará disponível em http://localhost:8080.

## Variáveis de Ambiente
A aplicação Java usa as seguintes variáveis de ambiente para se conectar ao banco de dados MySQL:

DB_HOST: O endereço do banco de dados (padrão: db)  
DB_PORT: A porta do banco de dados (padrão: 3306)  
DB_USER: O usuário do banco de dados (padrão: root)  
DB_PASSWORD: A senha do banco de dados (padrão: senha)  


## Estrutura do Código
app/src/Main.java: Classe principal da aplicação Java.  
app/Dockerfile: Dockerfile para construir a imagem da aplicação.  
docker-compose.yml: Configuração dos serviços (aplicação e banco de dados).  

## Como Parar os Containers

Para parar os containers, use o comando:

    docker-compose down





   