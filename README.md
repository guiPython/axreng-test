# Html-Search-Engine
Nesse desafio técnico o objetivo era encontrar palavras em páginas html, a ideia era dado um link de entrada eu deveria buscar a palavra solicitada,
além disso era preciso mapear links internos e realizar buscas neles também. 

Exemplo:

* url: teste.com.br/inicio
* termo: "teste"

Caso a página contivesse o termo "teste" tinhamos o incremento do resultado, caso ela tivesse a referênca para algum link da forma "teste.com.br/xxx/..."
deveriamos manter a busca ativa e acessar o conteúdo dessa nova referência.

Para o problema pensei em uma abordagem concorrente, eu não conhecia o modelo de atores do Akka|Elixir, porém utilizei o conhecimento que obtive usando Golang.
A ideia foi pensar em uma comunicação baseada em eventos, os processos de busca do termo enviam mensagens relacionadas aos eventos de "termo encontrado" e 
"url encontrada", os handlers desses eventos coordenam chamadas dos métodos da entidade busca que tive que implementar como "synchronized", assim consigo 
usar a thread principal como orquestradora e controladora da busca, sempre verificando um estado alterado por threads que ela lançou.  


A relação entre essa solução e Golang foi pensar no dispachador dos eventos mencionados como channel, 
basicamente recebendo sinais de diversas goroutines que seriam as famosas threads do Java.

