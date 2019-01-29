# CVCCorpTest

* [Quais ferramentas foram usadas](#quais-ferramentas-foram-usadas)
* [Como executar, testar, empacotar e entregar o projeto](#como-executar-testar-empacotar-e-entregar-o-projeto)
* [Como usar](#como-usar)
* [Aspectos de Implementação](#aspectos-de-implementacao)


## Quais ferramentas foram usadas

| Ferramenta | Link para download |
|------------|--------------------|
|Java Development Kit 8 64 bits para Linux | (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) |
| Maven 3.3.9 para Linux | (https://maven.apache.org/download.cgi) |
| IDE InteliJ IDEA Ultimate 2018.3 para linux| (https://www.jetbrains.com/idea/download/#section=linux) |
| Spring Boot 2.1.2.RELEASE | (https://start.spring.io/) |
| HTTPie 64 bits para Linux | (https://httpie.org/) |


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
	> Dentro da pasta target, verificar a existência do arquivo com nome: CvcCorpTestApplication-0.0.1-SNAPSHOT.jar

8. Para executar

	> Entrar na pasta target criada no passo anterior e executar o comando abaixo
``` 
	java -jar CvcCorpTestApplication-0.0.1-SNAPSHOT.jar
``` 

## Como usar 


| O quê você deseja fazer | Comando httpie |
|-------------------------|----------------|
| Consultar lista de hotéis por cidade | http GET http://localhost:8080/consultar/cidade cityCode==1032 checkin==28/01/2019 checkout==01/02/2019 quantidadeDeAdulto==2 quantidadeDeCrianca==2 |
| Consultar hotel | http GET http://localhost:8080/consultar/hotel hotelId==1 checkin==28/01/2019 checkout==01/02/2019 quantidadeDeAdulto==2 quantidadeDeCrianca==2 |


## Aspectos de Implementação
### 1.Sobre o cache:  
A príncipio não usaria o cache, pelo motivo de existir a possibilidade de os valores mudarem, com isso o serviço iria retornar valores desatualizados de proposta de hospedagem. Mas, em se tratando de 1 minuto apenas, não atrapalharia a aplicação, assumindo que o broker não mude o valor da diária de um hotel com tanta frequência. Caso o serviço proposto no teste seja apenas uma tela de busca, poderíamos aumentar o valor do tempo de cache, porém para uma tela de pagamento final usaria um cache menor.	A configuração do cache se encontra no arquivo de configurações da aplicação, no qual os valores podem ser alterados.  
Caminho do arquivo: cvc_corp_test/src/main/resources/application.properties  
Para implementação do cache foi utilizado o Caffeine(http://www.javadoc.io/doc/com.github.ben-manes.caffeine/caffeine/2.6.2). O qual possui várias propriedades configuráveis, das quais foram utilizadas:  
		- size-based eviction (Expira o cache com base no tamanho acumulado)
 		- time-based expiration (Expira o cache com base no tempo)
### 2.Implementação do Client com Feign Clients:  
Para consumir a API fornecida pelo broker foi utilizado o FeingClient, não apenas pela facilidade que o mesmo fornece para implementação, mas também pelo fato de ser completo, possibilitando inclusive a criação de Fallbacks nas chamadas ao serviços, no qual poderiamos avisar aos brokers por exemplo, quando um serviço de broker estivesse indiponível.  
A solução foi criada de forma que, em havendo vário brokers poderiamos adicionar implementações de clientes sem a necessidade alteração em várias partes do código.
### 3.Validação dos Beans:  
Foi também utilizado o  JSR 3, nas classes de request, que representam uma requisição à solução proposta, de forma que a validação é feita de forma implícita através das "annotations", onde em havendo algum valor nulo ou inválido, a requisição para e devolve o status de "Bad Request"
### 4.Uso do Lombok(https://projectlombok.org/)  
O Lombok, facilita a criação de classes de Objeto reescrevendo código, sem a necessidade de criar getters ou setter e até mesmo construtores. No caso da solução apresentada foi utilizado em várias classes a anotação: "AllagrsContructor", que basicamente recria a classe com um construtor usando todos os campos da mesma. então caso precise adicionar algum campo em alguma classe, não é necessário refatorar o seu construtor 
  
## Agradecimentos
* Grato em poder participar dessa seleção. Independete do resultado final já foi um grande aprendizado, visto que basicamente todas as tecnologias são novas em termos de mercado para mim. Com isso, tentei buscar,estudar e analisar formas mais recentes de como implementar a solução mencionada na proposta.  
* Grato ao Rodrigo Rahman, pela disponibilidade para entrevista.
