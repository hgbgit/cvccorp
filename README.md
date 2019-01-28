# CVCCorpTest

* [Como usar](#como-usar)
* [Quais ferramentas foram usadas](#quais-ferramentas-foram-usadas)
* [Infraestrutura adicional](#infraestrutura-adicional)
* [Como executar, testar, empacotar e entregar o projeto](#como-executar-testar-empacotar-e-entregar-o-projeto)
* [Instruções para ambiente de produção](#instruções-para-ambiente-de-produção)

## Quais ferramentas foram usadas

| Ferramenta | Link para download |
|------------|--------------------|
|Java Development Kit 8 64 bits para Linux | (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) |
| Maven 3.3.9 para Linux | (https://maven.apache.org/download.cgi) |
| IDE InteliJ IDEA Ultimate 2018.3 para linux| (https://www.jetbrains.com/idea/download/#section=linux) |
| Spring Boot 2.1.2.RELEASE | (https://start.spring.io/) |
| HTTPie 64 bits para Linux | (https://httpie.org/) |

## Como usar 


| O quê você deseja fazer | Comando httpie |
|-------------------------|----------------|
| Consultar lista de hotéis por cidade | http GET http://localhost:8080/consultar/cidade cityCode==1032 checkin==28/01/2019 checkout==01/02/2019 quantidadeDeAdulto==2 quantidadeDeCrianca==2 |
| Consultar hotel | http GET http://localhost:8080/consultar/hotel hotelId==1 checkin==28/01/2019 checkout==01/02/2019 quantidadeDeAdulto==2 quantidadeDeCrianca==2 |
 
  

## Infraestrutura adicional

### Porta do serviço

* A porta padrão para rodar este serviço é 8080. (http://localhost:8080/consultar/cidade)
* Para alterar basta informar a porta desejada no arquivo de propriedade src/main/resources/application.properties, conforme abaixo:
```
	server.port=8080
```

## Como executar, testar, empacotar e entregar o projeto

1. Baixar e instalar o Java 1.8 ou superior
2. Baixar e instalar o Maven 3.3.9 ou superior
5. Baixar os fontes
```
git clone https://github.com/hgbgit/cvccorp.git
cd cvccorp
```
6. Verificar arquivo src/main/resources/application.properties

	> Confirmar a porta do Serviço (porta padrão é 8080)

7. Gerar o pacote Java em formato Jar
```
mvn clean install
```
	> Dentro da pasta target, verificar a existência do arquivo com nome: platcorp-0.0.1.jar

8. Para executar

	> Entrar na pasta target criada no passo anterior e executar o comando abaixo
``` 
	java -jar platcorp-0.0.1.jar
``` 
	

## Instruções para ambiente de produção

- [ ]  JDK 8 ou superior deve estar instalado na máquina que a aplicacao platcorp irá rodar
- [ ]  Liberar portas no firewall, conforme [Infraestrutura adicional](#infraestrutura-adicional)
- [ ]  Criar uma pasta com nome "**platcorp**" 
- [ ]  Copiar o arquivo gerado na seção [Como executar, testar, empacotar e entregar o projeto](#como-executar-testar-empacotar-e-entregar-o-projeto)
- [ ]  Executar o comando: 
```
java -jar platcorp-0.0.1.jar
```
