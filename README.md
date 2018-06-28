# Intelipost: Teste prático para Backend Developer

## O desafio

Como você pode ver, nosso maior desafio está na manutenção e otimização de aplicações que estejam prontas para atender um altíssimo volume de dados e transações, por este motivo, todos os membros da nossa equipe estão altamente comprometidos com estes objetivos, de robustez, escalabilidade e performance, e é exatamente isso que esperamos de você através da resolução destes dois desafios:

1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Dado o problema de performance, partiria para a investigação no banco de dados, analisaria throughput e processamento, e caso identifique que o banco está sendo sobrecarregado, tomaria as seguintes ações:

 1 ) Buscaria por slow queries e faria otimização.

 2 ) Supondo que todas as queries estão otimizadas e o banco continua sobrecarregado, partiria para solução NoSql para auxiliar na leitura de dados (Redis ou MongoDb).

 3 ) Com o auxílio de cache e otimização de queries acredito que o problema de leitura seja resolvido, mas se o problema persistir, escalaria o banco de dados. E para escalar o banco, partiria para replicação, com isso o processamento do banco é distribuído em mais servidores, utilizaria o Pgpool-II (http://www.pgpool.net/)

Outro cenário possível seria problema de performance no servidor da aplicação, da mesma forma analisaria throughput, processamento e consumo de memória. Dado que a aplicação está escrita de forma performática (Sem memory-leak), tomaria as seguintes ações:

 1 ) Deixaria a aplicação state-less, não guardaria nenhum tipo de status no servidor, geralmente os dados de autenticação são guardadas no servidor, e para mudar essa estratégia utilizaria JWT para gerar tokens, dessa forma a validação de autorização se faz pelo token gerado pelo JWT, e não mais pelos dados armazenados no servidor.

 2 ) Dado que a aplicação é state-less, é possível fazer o deploy da aplicação em mais de uma instância, com isso subiria a aplicação em mais servidores e faria o load balance utilizando https://www.nginx.com/


## Solução prática

Desenvolvi uma aplicação simples, utilizando spring-boot com os seguintes starters: Data, Data Redis, Security, Web e Thymeleaf.

Optei por deixar a aplicação mais simples possível de ser iniciada, utilizei servidor, banco de dados e gradle embedado, única dependência que não foi possível deixar embedada foi o Redis (confesso que tentei)!

O projeto foi desenvolvido utilizando o padrão DDD, nota-se pela divisão de camadas do projeto (Application, Domain, Infrastructure). A ideia é comunicação entre camadas seja sempre nessa direção:

Application -> Domain -> Infrastructure

Outro ponto importante é deixar o domínio mais limpo possível, por esse motivo decidi deixar as classes de entidade JPA na respectiva camada de Infrastructure.

No domínio defino a responsabilidade do repositório, porém a forma que o repositório lida com os dados fica na camada de Infrastructure, o domínio não tem conhecimento da tecnologia utilizada (Sql, NoSql, Arquivo TXT), com isso garanto que a lógico do negócio permanece íntegra independente de tecnologia utilizada na camada abaixo.

Foquei bastante em seguir boas práticas DDD, TDD e deixar claro que estou resolvendo o problema de leitura no banco de dados, por esse motivo optei por utilizar o Redis para otimizar a leitura.


## Deploy

A aplicação foi desenvolvida para que seja iniciada da forma mais simples possível, basta seguir os passos:

 * Checkout do projeto: `git clone git@github.com:gitzacca/job-backend-developer.git`

 * Única dependência é o Redis, caso não tenha instalado, aconselho rodar o Redis via Docker:

	- Caso o docker não esteja instalado: sudo `apt-get install docker.io`
	- Download da imagem e start do container: sudo docker run --rm --name redis -p 6379:6379 -d redis

Pronto, o ambiente já está preparado :)

Para executar a aplicação execute o comando: `gradle bootRun` ou execute a classe `UsersApplication.java` pela IDE.

A página estará disponível no endereço: `http://localhost:8080/`

Disponibilizei uma API Rest que seria consumida por outro sistema: `http://localhost:8080/users?page=0&size=10`

## Agradecimento

Quero frisar que foi um teste muito interessante, e agradeço a oportunidade de mostrar o meu trabalho!

Estou disponível para esclarecer qualquer dúvida :)


zaccabruno@gmail.com
(11)97160-9350