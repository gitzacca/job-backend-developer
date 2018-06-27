# Intelipost: Teste prático para Backend Developer

## O desafio

Como você pode ver, nosso maior desafio está na manutenção e otimização de aplicações que estejam prontas para atender um altíssimo volume de dados e transações, por este motivo, todos os membros da nossa equipe estão altamente comprometidos com estes objetivos, de robustez, escalabilidade e performance, e é exatamente isso que esperamos de você através da resolução destes dois desafios:

1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Dado o problema de performance, partiria para a investigação no banco de dados, analisaria throughput e processamento, e caso identifique que o banco esta sendo sobrecarregado, tomaria as seguintes ações:

 1 ) Buscaria por slow queries e faria otimização.

 2 ) Supondo que todas as queries estão otimizadas e o banco continua sobrecarregado, partiria para solução NoSql para auxiliar na leitura de dados (Redis ou MongoDb).

 3 ) Com o auxilio de cache e otimização de queries acredito que o problema de leitura seja resolvido, mas se o problema persistir, escalaria o banco de dados. E para escalar o banco, partiria para replicação, com isso o processamento do banco é distribuido em mais servidores, utilizaria o Pgpool-II (http://www.pgpool.net/)

Outro cenário possível seria problema de performance no servidor da aplicação, da mesma forma analisaria throughput, processamento e consumo de memória. Dado que a aplicação está escrita de forma performática (Sem memory-leak), tomaria as seguintes ações:

 1 ) Deixaria a aplicação state-less, não guardaria nenhum tipo de status no servidor, geralmente os dados de autenticação são guardadas no servidor, e para mudar essa estratégia utilizaria JWT paar gerar tokens, dessa forma a validação de autorização se faz pelo token gerado pelo JWT, e não mais pelos dados armazenados no servidor.

 2 ) Dado que a aplicação é state-less, é possível fazer o deploy da aplicação em mais de uma instância, com isso subiria a aplicação em mais servidores e faria o load balance utilizando https://www.nginx.com/

2) Com base no problema anterior, gostaríamos que você codificasse um novo sistema de login para muitos usuários simultâneos e carregamento da tela inicial. Lembre-se que é um sistema web então teremos conteúdo estático e dinâmico. Leve em consideração também que na empresa existe um outro sistema que também requisitará os dados dos usuários, portanto, este sistema deve expor as informações para este outro sistema de alguma maneira.

### O que nós esperamos do seu teste

* O código deverá ser hospedado em algum repositório público. Diversos quesitos serão avaliados aqui, como organização do código, sequencialidade de commits, nomeação de classes e métodos, etc.
* O código deverá estar pronto para ser executado e testado, portanto, caso exista algum requisito, este deve estar completamente documentado no README do seu projeto.
* Esperamos também alguma explicação sobre a solução, que pode ser em comentários no código, um texto escrito ou até um vídeo narrativo explicando a abordagem utilizada.
* Você deverá utilizar a nossa stack de tecnologias descritas no início deste documento (Java 8 + Spring boot + Spring MVC).

### O que nós ficaríamos felizes de ver em seu teste

* Testes
* Processo de build e deploy documentado
* Ver o código rodando live (em qualquer serviço por aí)

### O que nós não gostaríamos

* Descobrir que não foi você quem fez seu teste
* Ver commits grandes, sem muita explicação nas mensagens em seu repositório

## O que avaliaremos de seu teste

* Histórico de commits do git
* As instruções de como rodar o projeto
* Organização, semântica, estrutura, legibilidade, manutenibilidade do seu código
* Alcance dos objetivos propostos
* Escalabilidade da solução adotada
