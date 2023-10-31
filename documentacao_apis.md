<!-- # tc1-phto -->

# :page_facing_up: Documentação APIs

Nesse material poderá ser encontrado o conjunto de APIs desenvolvidas para cadastro de Eletrodomésticos, Pessoas e Endereços.
Esses são os primeiros requisitos referentes ao primeiro TechChallenge da Pós: Arquitetura e Desenvolvimento em Java / FIAP.
#

## :tv: API: Cadastra Eletrodoméstico
Endpoint: http://localhost:8081/eletrodomesticos
<br>Essa API cadastra eletrodomésticos. A entrada deve conter um JSON com os dados obrigatórios:

~~~json
{ 
  "nome":"", 
  "modelo":"", 
  "potencia":"", 
  "selo":"" 
}
~~~
Não sendo aceito: valores vázios ou a não declaração de alguma das chaves apresentadas.

### :green_circle: Sucesso:
Caso todos os requisitos sejam aceitos o retorno será:
~~~~
"Eletrodoméstico cadastrado com sucesso!"
~~~~

###  :red_circle: Erro:
Caso algum valor não seja declarado ou alguma chave, o retorno será uma indicação de erro, como no exemplo baixo:
#### Post:
~~~json
{
"nome":"",
"modelo":"283c",
"potencia":"12",
"selo":"A"
}
~~~
#### Retorno:
~~~json
{
"nome": "Nome não pode estar em branco e não pode ser nulo."
}
~~~

##

## :raising_hand: API: Cadastra Pessoa
Endpoint: http://localhost:8081/pessoas
<br>Essa API cadastra pessoas. A entrada deve conter um JSON com os dados obrigatórios:
~~~json
{ 
    "nome":"",
    "datanascimento": "",
    "sexo": "",
    "parentesco": ""
}
~~~
Não sendo aceito: valores vázios ou a não declaração de alguma das chaves apresentadas.

### :green_circle: Sucesso:
Caso todos os requisitos sejam aceitos o retorno será:
~~~~
"Pessoa cadastrada com sucesso!"
~~~~

### :red_circle: Erro:
Caso algum valor não seja declarado ou alguma chave, o retorno será uma indicação de erro, como no exemplo baixo:
#### Post:
~~~json
{
    "nome":"Paulo",
    "datanascimento": "",
    "sexo": "Masculino",
    "parentesco": "Parentesco"
}
~~~
#### Retorno:
~~~json
{
    "datanascimento": "Data de Nascimento não pode estar em branco e não pode ser nulo."
}
~~~

##

## :house: API: Cadastra Endereço
Endpoint: http://localhost:8081/enderecos
<br>Essa API cadastra endereços. A entrada deve conter um JSON com os dados obrigatórios:
~~~json
{ 
    "bairro": "",
    "cidade": "",
    "rua": "",
    "estado": "",
    "numero": ""
}
~~~
Não sendo aceito: valores vázios ou a não declaração de alguma das chaves apresentadas.

### :green_circle: Sucesso:
Caso todos os requisitos sejam aceitos o retorno será:
~~~~
"Endereço cadastrado com sucesso!"
~~~~

### :red_circle: Erro:
Caso algum valor não seja declarado ou alguma chave, o retorno será uma indicação de erro, como no exemplo baixo:
#### Post:
~~~json
{
    "bairro": "Jardins",
    "cidade": "SP",
    "rua": "",
    "estado": "São Paulo",
    "numero": "600"
}
~~~
#### Retorno:
~~~json
{
    "rua": "Rua não pode estar em branco e não pode ser nulo."
}
~~~


