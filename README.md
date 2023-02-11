# Backend

## Ferramentas utilizadas 

> * IntelliJ Idea Community Edition;
> * Postman;

## Requisitos para rodar

> Ter instalado na maquina o OpenJDK 17
> Ter instalado na maquina a ferramenta git

## Como Rodar

> * Faça clone desse projeto do git https://github.com/Obedera/avaliacao-full-stack
> * Abra o projeto como Mavem na pasta backend com a IDE de preferência e aguarde baixar as dependências
> * Após baixar as dependências execute a classe Application localizada /backend/src/main/java/com/obedera/transferencias/financeiras/Application.java


## Como testar os endpoints
Segue a lista abaixo de curls para cada endpoint

(Caso use o postman para testar segue o link de como importar o curl https://dev.to/osejudith/how-to-import-curl-into-postman-and-test-requests-1agg)


- Obter resumo da transferência(simular quanto irá ficar a taxa)
> curl --location --request POST 'localhost:8080/api/v1/transferencia/resumo' \
--header 'Content-Type: application/json' \
--data-raw '{
    "contaOrigem": "111111",
    "contaDestino": "222222",
    "valor": 100,
    "dataAgendamento": "2023-01-19",
    "dataTransferencia": "2023-01-19"
}'


- Registrar transferência
> curl --location --request POST 'localhost:8080/api/v1/transferencia/confirmar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "contaOrigem": "123456",
    "contaDestino": "123456",
    "valor": 100,
    "dataAgendamento": "2023-01-19",
    "dataTransferencia": "2023-01-19"
}'


- Obter todas transfêrencias agendadas
> curl --location --request GET 'localhost:8080/api/v1/transferencia/historico'


## Linguagens e dependências utilizadas
1. Java 17
2. Spring boot
- JPA
- WEB
3. H2
4. Lombok
5. Mockito
6. Junit
7. Six2six


## Responsabilidades

### Controller
Responsável por receber as requisições HTTP. Realiza conversões e validações de dados.

### Use case
Contém um ou mais métodos públicos referentes as regras de negócios. 

### Repository Facade
Responsável por intermediar os acessos aos dados, seja por meio de Repository ou API. 

### Repository
Executa operações de leitura/escrita no banco de dados. Deve ter `privilégio de classe` e ser acessado somente pelo RepositoryFacade.

### Entity
Mapeia uma tabela do banco de dados.


-----------------------------------------------------


# Entregáveis
 Pequena documentação no README explicando suas decisões arquiteturais, versões de linguagem,
ferramentas utilizadas e instruções para a subida do projeto.

 É obrigatório a criação de um projeto no seu Github para que vejamos os passos feitos
através dos commits.

# Avaliação

Desenvolver tanto a API quanto o front-end (Spring boot e Vue no front, caso não tenha conhecimentos de vue, aceitamos o front com angular)

O objetivo dessa tarefa é avaliar como você vai desenvolver o código em termos de estilo,
eficiência, qualidade e prazo de entrega.

A tarefa é a seguinte:

Desenvolver um sistema de agendamento de transferências financeiras.

1) O usuário deve poder agendar uma transferência financeira com as seguintes
 informações:
 Conta de origem (padrão XXXXXX)
 Conta de destino (padrão XXXXXX)
 Valor da transferência
 Taxa (a ser calculada)
 Data da transferência (data que será realizada a transferência)
 Data de agendamento (hoje)
 
2) Cada tipo de transação segue uma regra diferente para cálculo da taxa

 A: Tranferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a
ser transferido;

B: Tranferências até 10 dias da data de agendamento possuem uma taxa de $12.

C: Operações do tipo C tem uma taxa regressiva conforme a data de
transferência:

 acima de 10 dias da data de agendamento 8.2%
 
 acima de 20 dias da data de agendamento 6.9%
 
 acima de 30 dias da data de agendamento 4.7%
 
 acima de 40 dias da data de agendamento 1.7%
 
 D: Operações do tipo D tem a taxa igual a A, B ou C dependendo do valor da
transferência.

 Valores até $1.000 seguem a taxação tipo A
 
 Valores de $1.001 até $2.000 seguem a taxação tipo B
 
 Valores maiores que $2.000 seguem a taxação tipo C
 
Obs: Caso não haja taxa aplicável, lançar um alerta sobre o erro.

3) O usuário deve poder ver todos os agendamentos cadastrados.

Nota: A persistência deve ser feita em banco de dados em memória (h2, por exemplo).
Boa sorte!


