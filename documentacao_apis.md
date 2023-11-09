<!-- # tc1-phto -->

# :page_facing_up: Funcionalidades da Aplicação


## 🕐 API: Cadastro de Tempo
O Condutor escolhe o tipo do tempo que será contratado: FIXO ou VARIAVEL.<br>
No caso de tempo FIXO: necessário incluir as horas contratadas.

Endpoint: http://localhost:82/tempo
<br>Essa API cadastra o tempo FIXO inicial:<br>
### Entrada:
~~~json
{
    "tipoTempo":"FIXO",
    "tempoContratado": 2
}
~~~
### Retorno:
~~~json
{
    "id": 2,
    "tipoTempo": "FIXO",
    "inicio": "2023-11-08T22:32:27.4714841",
    "fim": "2023-11-09T00:32:27.4714841",
    "tempoContratado": 2
}
~~~

## 🕐 API: Cadastro de Tempo Adicional
Caso o condutor precise contratar, horas adicionais precisará informar: <br>
Tempo Adicional e o Id do tempo no qual será adicionado o novo tempo.

Endpoint: http://localhost:82/tempoadd
### Entrada:
~~~json
{
    "tempoAdicional": 1,
    "tempo": {
        "id": 1
       }
}

~~~
### Retorno:
~~~json
{
    "id": 1,
    "novoInicio": "2023-11-10T00:32:27.4714841",
    "novoFim": "2023-11-10T01:32:27.4714841",
    "tempoContratado": 1,
    "tempo": {
    	"id": 2,
    	"tipoTempo": "FIXO",
    	"inicio": "2023-11-08T22:32:27.4714841",
    	"fim": "2023-11-09T00:32:27.4714841",
    	"tempoContratado": 2
       }
}
~~~

## 🕐 API: Visão Geral do Tempo Contratado
Ao final o condutor terá acesso a toda informação de tempo cadastrado.
Endpoint: http://localhost:82/tempo?pagina=0&tamanho=10

### Retorno:
~~~json
{
    "id": 2,
    "tipoTempo": "FIXO",
    "inicio": "2023-11-08T22:32:27.4714841",
    "fim": "2023-11-09T00:32:27.4714841",
    "tempoContratado": 2,
    "tempoAdd": [
        {
   		 "id": 1,
    		"novoInicio": "2023-11-10T00:32:27.4714841",
    		"novoFim": "2023-11-10T01:32:27.4714841",
    		"tempoContratado": 1,
        },
        {
   		 "id": 2,
    		"novoInicio": "2023-11-10T01:32:27.4714841",
    		"novoFim": "2023-11-10T02:32:27.4714841",
    		"tempoContratado": 1,
        }
      ]
}

~~~

