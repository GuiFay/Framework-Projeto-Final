Framework-Projeto-Final
=======================

##UnB - Universidade de Brasília - FGA - Faculdade do Gama

                                                Trabalho Final
                                  DAS - Desenvolvimento Avançado de Software
                                  
                                                                                              
###Enunciado
O trabalho tem como objetivo a criação de um framework para autenticação e comunicação de clientes. Os grupos deverão criar o projeto do framework denindo claramente os hot-spots, frozen-spots e distribuindo adequadamente as funcionalidades pelo projeto através da aplicação de padrões de projeto.
A autenticação de clientes pode ser realizada através de dois componentes: uma autenticação via arquivo txt e uma autenticação via arquivo xml. Não importa qual o mecanismo a ser utilizado para autenticar um usuário (txt ou xml), no entanto, toda vez que um usuário for cadastrado ou alterar os dados de um usuário do framework, tal alteração deverá ser reetida em ambos os componentes.
Os serviços de comunicação a serem executados pelo framework serão de mensagens síncronas (clientes logados no framework receberão a mensagem "on-line", tal como um chat típico) e mensagens assíncronas para clientes não conectados em um determinado instante no framework, mas que possuem seu cadastro. Portanto o framework deverá ser capaz de saber em um dado instante de tempo quais os usuários estão cadastrados nele e desses usuários cadastrados quais deles estão conectados naquele momento.
Todos os serviços a serem executados pelo framework (autenticação e mensagens - on e oine) deverão ser implementados como uma extensão do framework e, por esse motivo, executados fora da implementação do núcleo do framework. Nesse ponto espera-se que o Princípio de Hollywood seja aplicado corretamente. 

O trabalho deverá ser entregue na noite de quinta-feira anterior à aula de DAS, para que ele possa ser discutido com os alunos durante a aula de sexta.

####Critérios de avaliação:
* Definição adequada de hot e frozen-spots.
* Distribuição das funcionalidades pelos elementos pertencentes ao framework.
* Aplicação do princípio de Hollywood.
* Domínio do projeto durante a arguição do professor
