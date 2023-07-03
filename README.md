<center>
  <p align="center">
    <img src="https://gitlab.com/desafio-banco-inter-thales/user-manager/-/raw/main/public/logo.png" width="150"/>&nbsp;
  </p>  
  <h1 align="center">🚀 Microserviço: Gerenciador de wishlists</h1>
  <p align="center">
    Microserviço referente ao desafio para Luizalabs<br />
    Espero que gostem!!
  </p>
</center>
<br />

## Objetivo do microserviço

O Wishlist Service é um serviço de gerenciamento de listas de desejos. Ele permite aos usuários criar, visualizar, atualizar e excluir produtos das wishlists.

## Ferramentas necessárias para rodar o projeto em container

- Docker
- Docker compose

## Ferramentas necessárias para o projeto localmente

- IDE de sua preferência
- Postman
- JDK 17
    - Utilizei  instalação da versão corretto 17 pela própria IDE, no caso Intellij.
- Docker e Docker compose
    - Para rodar as dependências
- Maven
    - Utilizei a versão **3.9.2**

## Documentação

1. Acessar documentação após startar o service através da seguinte url:
```sh
http://localhost:8080/swagger-ui/index.html#/
```

## Pré-execução

1. Clonar o repositório:
```sh
git clone https://github.com/EduMonteiroDev/Luizalabs-Wishlist
```

2. Entre na pasta referente ao repositório:
```sh
cd Luizalabs-Wishlist
```

## Execução


É possível executar o projeto de duas formas:

>Importe a collection do postman que está na pasta postman com todos os endpoints configurados.

### Apenas containers

Execute o comando
```shell
docker-compose -f containers/docker-compose.yml up -d --build
```

> A opção **--build** serve para não utilizarmos o cache para construção
> da imagem do wishlist-app, caso queira buildar uma imagem já tendo gerado
> a primeira e não quer perder tempo pode remover essa opção de --build

### Aplicação rodando localmente

Execute o comando
```shell
docker-compose -f containers/database-compose.yml up -d --build

Ajuste a IDE para executar utilizando o contexto das enviroments variables abaixo

mongodb.username=admin;
spring.data.mongodb.database=luizalabs;
spring.data.mongodb.uri=mongodb://localhost:27017/luizalabs;
mongodb.password=mm236533;
```

2. Execute a aplicação
> Execute a API baseado no método main() na classe Main.java



## Banco de dados
O banco de dados principal é um MongoDB e para subir localmente não precisamos de
passos extras, a própria aplicação se encarrega de subir as tabelas descritas
no arquivo presente em ```containers/init-mongo.js```

## Stop

Caso queira derrubar a aplicação dentro do container

1. Execute o comando
```shell
docker-compose -f containers/docker-compose.yml down
```

## Testes

1. Execute o comando

> É possível executar via IDE. Utilizando o Intellij vá até a pasta
> **src/test/java/com/example/wishlist** clique com o botão direito e na metade
> das opções apresentadas escolha **Run Tests in 'com.example.wishlist''**

## Responsabilidades do microserviço

- [x] Adicionar produto a wishlist
- [x] Remover produto da wishlist
- [x] Verificar se o produto existe na wishlist
- [x] Retornar a wishlist com todos os produtos
