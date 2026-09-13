# Relatório de Refatoração — ProjetoPrincipiosDesignJava

## 1. Introdução

Este relatório apresenta as alterações realizadas no projeto `ProjetoPrincipiosDesignJava`, desenvolvido em Java. O objetivo foi aplicar princípios de projeto e refatoração, buscando melhorar a organização, a manutenção e a flexibilidade do código, sem alterar o comportamento essencial da aplicação.

Após cada etapa, o projeto foi compilado e executado novamente para verificar se as funcionalidades continuavam funcionando corretamente.

## 2. Etapa 1 — Princípio da Responsabilidade Única

### Problema identificado

A classe `PedidoService` possuía várias responsabilidades, incluindo o cálculo do pedido, o processamento do pagamento, a geração do resumo e o salvamento dos pedidos em arquivo.

### Refatoração realizada

Foi criada a classe `PedidoRepository`, responsável exclusivamente por salvar os pedidos no arquivo `pedidos.txt`.

O método `finalizarPedido()` da classe `PedidoService` foi alterado para utilizar o `PedidoRepository`.

### Justificativa

A alteração aplica o Princípio da Responsabilidade Única, pois separa a persistência dos dados das demais operações do pedido.

Dessa forma, alterações na forma de armazenamento podem ser realizadas na classe `PedidoRepository`, sem modificar a lógica principal da classe `PedidoService`.

### Resultado

O programa continuou funcionando corretamente, mantendo o cálculo do pedido, o pagamento, a geração do resumo, o salvamento em arquivo e o envio da mensagem ao cliente.

## 3. Etapa 2 — Princípio da Segregação de Interfaces

### Problema identificado

A interface `IPagamento` possuía os métodos `pagar()`, `parcelar()` e `gerarBoleto()`.

Isso fazia com que as classes de pagamento dependessem de métodos que não necessariamente utilizavam.

### Refatoração realizada

A interface original foi dividida em três interfaces menores:

* `IPagamento`;
* `IPagamentoParcelamento`;
* `IPagamentoBoleto`.

As classes de pagamento passaram a implementar somente as interfaces relacionadas às suas funcionalidades.

### Justificativa

A alteração aplica o Princípio da Segregação de Interfaces, pois evita que as classes dependam de métodos desnecessários.

Essa separação torna o código mais organizado, facilita a manutenção e permite adicionar novas formas de pagamento com menor impacto no restante do sistema.

### Resultado

As operações de pagamento, parcelamento e geração de boleto foram separadas, mantendo as funcionalidades essenciais da aplicação.

## 4. Etapa 3 — Princípio da Inversão de Dependências

### Problema identificado

A classe `PedidoService` possuía uma relação de herança com `PagamentoCartao`.

Essa estrutura era inadequada, pois um serviço de pedidos não é um tipo de pagamento. Além disso, o serviço ficava diretamente dependente de uma forma específica de pagamento.

### Refatoração realizada

A herança entre `PedidoService` e `PagamentoCartao` foi removida.

A dependência foi substituída por composição, utilizando a interface `IPagamento`. Dessa forma, o objeto de pagamento passou a ser recebido pelo construtor de `PedidoService`.

### Justificativa

A alteração aplica o Princípio da Inversão de Dependências, pois `PedidoService` passou a depender de uma abstração, representada pela interface `IPagamento`, em vez de depender diretamente de uma classe concreta.

Isso reduz o acoplamento e permite trocar a forma de pagamento sem modificar a lógica principal do serviço.

### Resultado

O pagamento com cartão continuou funcionando corretamente. A estrutura também passou a permitir o uso de outras formas de pagamento, como PIX e boleto.

## 5. Etapa 4 — Princípio da Composição em vez de Herança

### Problema identificado

A classe `EntregaRetiradaLoja` herdava da classe `Entrega`.

Essa relação não representava adequadamente o comportamento das duas classes, pois a retirada na loja possui uma regra própria de cálculo de frete.

### Refatoração realizada

A herança entre `EntregaRetiradaLoja` e `Entrega` foi substituída por composição.

Foi criada a interface `CalculadoraFrete`, que define o método:

```java
double calcularFrete(double total);
```

A classe `Entrega` passou a receber uma implementação de `CalculadoraFrete` pelo construtor e a delegar a ela o cálculo do frete.

A classe `EntregaRetiradaLoja` deixou de herdar de `Entrega` e passou a implementar `CalculadoraFrete`.

### Justificativa

A composição permite separar o cálculo do frete da classe responsável pela entrega. Assim, diferentes formas de cálculo podem ser utilizadas sem criar uma hierarquia de herança desnecessária.

### Resultado

O projeto foi compilado e executado com sucesso, mantendo os comportamentos de entrega normal e retirada na loja.

## 6. Etapa 5 — Princípio de Demeter

### Problema identificado

O código possuía acessos encadeados entre objetos, como:

```java
cliente.getEndereco().getCidade().getNome();
```

Esse tipo de acesso faz com que uma classe conheça detalhes internos de outros objetos.

### Refatoração realizada

Foi criado o método `obterNomeCliente()` na classe `Pedido`.

Também foi utilizado o método `obterCidadeEntrega()` para centralizar o acesso às informações relacionadas ao endereço do cliente.

As classes `PedidoService` e `PedidoRepository` passaram a utilizar os métodos da classe `Pedido`, evitando acessar diretamente os objetos internos.

### Justificativa

A alteração aplica o Princípio de Demeter, pois reduz o conhecimento que uma classe precisa ter sobre a estrutura interna de outras classes.

Isso melhora o encapsulamento e reduz o acoplamento entre os objetos.

### Resultado

O comportamento original foi preservado. O sistema continuou exibindo corretamente o nome do cliente, a cidade de entrega e a mensagem de finalização do pedido.

## 7. Etapa 6 — Princípio Aberto/Fechado

### Problema identificado

O método de cálculo do total utilizava condições para verificar o tipo de cliente:

* `ALUNO`;
* `PROFESSOR`;
* `FUNCIONARIO`.

Para adicionar um novo tipo de desconto, seria necessário modificar a classe `PedidoService`.

### Refatoração realizada

Foi criada a interface `CalculadoraDesconto`.

Também foram criadas classes específicas para cada regra de desconto:

* `DescontoAluno`;
* `DescontoProfessor`;
* `DescontoFuncionario`.

A classe `PedidoService` passou a receber uma implementação de `CalculadoraDesconto` pelo construtor.

### Justificativa

A alteração aplica o Princípio Aberto/Fechado, pois permite criar novos tipos de desconto sem modificar a classe `PedidoService`.

O sistema fica aberto para extensão e fechado para alterações na lógica principal.

### Resultado

O desconto de aluno continuou funcionando corretamente, mantendo o total do pedido em `R$ 144,00`.

A estrutura também permite substituir a regra de desconto por outra implementação sem alterar o serviço principal.

## 8. Etapa 7 — Princípio da Substituição de Liskov

### Problema identificado

A classe `EntregaRetiradaLoja` herdava diretamente da classe `Entrega`, apesar de possuir um comportamento específico para retirada na loja.

Essa herança poderia causar comportamentos inadequados quando a classe fosse utilizada como uma entrega comum.

### Refatoração realizada

A classe `EntregaRetiradaLoja` passou a implementar diretamente a interface `CalculadoraFrete`.

A classe verifica se o pedido possui o valor mínimo de `R$ 50,00`. Caso o valor seja suficiente, o frete retornado é `R$ 0,00`.

### Justificativa

A alteração evita uma relação de herança inadequada e permite que cada forma de entrega possua sua própria regra de cálculo.

A utilização da interface `CalculadoraFrete` torna o comportamento mais previsível e facilita a substituição das implementações de cálculo.

### Resultado

O projeto foi compilado e executado novamente sem erros.

A entrega normal continuou apresentando `R$ 15,00` e a retirada na loja apresentou `R$ 0,00`.

## 9. Testes realizados

Após as refatorações, o projeto foi compilado com o seguinte comando:

```powershell
javac -d out src/projetoprincipiosdesign/*.java
```

A execução foi realizada com:

```powershell
java -cp out projetoprincipiosdesign.Main
```

Os testes confirmaram que:

* O cálculo do total continuou funcionando;
* O desconto de aluno foi aplicado corretamente;
* O pagamento com cartão continuou funcionando;
* O pedido continuou sendo salvo no arquivo;
* O nome do cliente foi exibido corretamente;
* A cidade de entrega foi exibida corretamente;
* O frete normal continuou funcionando;
* A retirada na loja continuou funcionando;
* Um segundo cliente e um segundo pedido também foram testados;
* O programa foi executado sem erros.

## 10. Conclusão

As refatorações realizadas melhoraram a organização e a manutenção do projeto `ProjetoPrincipiosDesignJava`.

As responsabilidades foram separadas, as interfaces foram simplificadas, as dependências foram reduzidas e as regras de desconto e frete foram organizadas em abstrações específicas.

Mesmo com as alterações estruturais, o comportamento essencial da aplicação foi preservado. Os testes realizados após cada etapa confirmaram que o programa continuou funcionando corretamente.

Dessa forma, os sete princípios propostos foram aplicados ao projeto com sucesso.
