Teste Fork
### TESTE PARA UDS - Pizzaria
## @Author - Argel Gonçalves (argeljunior@gmail.com)

## STACK DE TECNOLOGIAS
* SpringBoot 2.1.9
* Maven 3
* Java 8
* Postgres
* H2
* Docker Compose

## COMO EXECUTAR
1. Inicializar contâiner do banco de dados

>docker-compose up -d

2. Baixar dependências do projeto

>./mvnw install

3. Rodar aplicação

>./mvnw spring-boot:run


###ENDPOINTS

[Coleção  para Postman](https://github.com/argelgoncalves/pizzaria-uds/blob/develop/Pizzaria%20UDS.postman_collection.json) 

1.Montar a pizza selecionando o tamanho,  e armazenar o _idPedido_ retornado

>POST http://localhost:8097/api/pedidos/pizza/montar/tamanho

Body: 
```
{
   "solicitacao": "(pequena|média|grande)"
}
```

Resposta 200 OK - Sucesso
```
{
  "idPedido": {idPedido},
  "solicitacao": "(pequena|média|grande)",
  "resposta": "Tamanho adicionado com sucesso"
}
```

Resposta 400 BAD REQUEST - Solicitação não encontrada
```
{
  "idPedido": null,
  "solicitacao": "calabresa",
  "resposta": "calabresa não encontrado"
}
```

----

2.Montar a pizza selecionando o sabor

>PUT http://localhost:8097/api/pedidos/pizza/{idPedido}/montar/sabor

Body: 
```
{
   "solicitacao": "(calabresa|marguerita|portuguesa)"
}
```

Resposta 200 OK - Sucesso
```
{
  "idPedido": {idPedido},
  "solicitacao": "(calabresa|marguerita|portuguesa)",
  "resposta": "Sabor adicionado com sucesso"
}
```

Resposta 400 BAD REQUEST - Solicitação não encontrada
```
{
  "idPedido": {idPedido},
  "solicitacao": "grande",
  "resposta": "grande não encontrado"
}
```

Resposta 400 BAD REQUEST - Pedido não encontrado
```
{
  "idPedido": {idPedido},
  "solicitacao": "(calabresa|marguerita|portuguesa)",
  "resposta": "Pedido não encontrado"
}
```

Resposta 400 BAD REQUEST - Pedido não é de pizza
```
{
  "idPedido": {idPedido},
  "solicitacao": "(calabresa|marguerita|portuguesa)",
  "resposta": "Pedido não contém pizza"
}
```

Resposta 400 BAD REQUEST - Pizza já teve um sabor selecionado
```
{
  "idPedido": {idPedido},
  "solicitacao": "(calabresa|marguerita|portuguesa)",
  "resposta": "Pizza já contém sabor"
}
```

3.Montar a pizza selecionando as personalizações

>PUT http://localhost:8097/api/pedidos/pizza/{idPedido}/montar/personalização

Body: 
```
[
{
   "solicitacao": "(sem cebola|extra bacon|borda recheada)"
}
]
```

Resposta 200 OK - Sucesso
```
{
  "idPedido": {idPedido},
  "solicitacao": "(sem cebola|extra bacon|borda recheada)",
  "resposta": "Adicionais incluídos com sucesso"
}
```

Resposta 400 BAD REQUEST - Solicitação não encontrada
```
{
  "idPedido": {idPedido},
  "solicitacao": "grande",
  "resposta": "grande não encontrado"
}
```

Resposta 400 BAD REQUEST - Pedido não encontrado
```
{
  "idPedido": {idPedido},
  "solicitacao": "(sem cebola|extra bacon|borda recheada)",
  "resposta": "Pedido não encontrado"
}
```

Resposta 400 BAD REQUEST - Pedido não é de pizza
```
{
  "idPedido": {idPedido},
  "solicitacao": "(sem cebola|extra bacon|borda recheada)",
  "resposta": "Pedido não contém pizza"
}
```

Resposta 400 BAD REQUEST - Pedido não teve sabor selecionado
```
{
  "idPedido": {idPedido},
  "solicitacao": "(sem cebola|extra bacon|borda recheada)",
  "resposta": "Pizza necessita de sabor para incluir adicionais"
}
```

Resposta 400 BAD REQUEST - Adicional já foi incluso no pedido
```
{
  "idPedido": {idPedido},
  "solicitacao": "(sem cebola|extra bacon|borda recheada)",
  "resposta": "Já está incluso no pedido"
}
```

4.Resumo do Pedido

>GET http://localhost:8097/api/pedidos/pizza/{idPedido}/resumo

Resposta 200 OK - Sucesso
```
{
    "tamanho": {
        "nome": "Média",
        "valor": "R$ 30,00"
    },
    "sabor": {
        "nome": "Marguerita",
        "valor": "R$ 0,00"
    },
    "personalizacoes": [
        {
            "nome": "Borda Recheada",
            "valor": "R$ 5,00"
        }
    ],
    "valorTotal": "R$ 35,00",
    "tempoDePreparo": 25
}
```

Resposta 400 BAD REQUEST - Pedido não encontrado
```
{
  "idPedido": {idPedido},
  "solicitacao": "Resumo",
  "resposta": "Pedido não encontrado"
}
```

Resposta 400 BAD REQUEST - Pizza sem sabor selecionado
```
{
  "idPedido": {idPedido},
  "solicitacao": "Resumo",
  "resposta": "Pedido não está completo"
}
```

Resposta 400 BAD REQUEST - Pedido não é de pizza
```
{
  "idPedido": {idPedido},
  "solicitacao": "(sem cebola|extra bacon|borda recheada)",
  "resposta": "Pedido não contém pizza"
}
```

Teste de Fork
