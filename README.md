# Projeto Principios de Design em Java

Projeto acadêmico desenvolvido em Java para aplicar princípios de design de software e boas práticas de Programação Orientada a Objetos.

## Objetivo

Refatorar um sistema simples de loja acadêmica, melhorando sua organização, manutenção, reutilização e flexibilidade.

## Princípios aplicados

Durante o desenvolvimento, foram aplicados os seguintes princípios:

1. Responsabilidade Única
2. Segregação de Interfaces
3. Inversão de Dependências
4. Prefira Composição à Herança
5. Princípio de Demeter
6. Aberto/Fechado
7. Substituição de Liskov

## Tecnologias utilizadas

- Java
- Programação Orientada a Objetos
- Git
- GitHub
- VS Code

## Funcionalidades

O projeto possui as seguintes funcionalidades:

- Cadastro de clientes;
- Cadastro de endereços e cidades;
- Criação de pedidos;
- Cálculo do valor total dos pedidos;
- Aplicação de descontos;
- Pagamento com cartão, Pix e boleto;
- Salvamento dos pedidos em arquivo;
- Cálculo de frete;
- Retirada de pedidos na loja.

## Estrutura do projeto

```text
ProjetoPrincipiosDesignJava/
├── src/
│   └── projetoprincipiosdesign/
│       ├── Main.java
│       ├── Cliente.java
│       ├── Endereco.java
│       ├── Cidade.java
│       ├── Pedido.java
│       ├── ItemPedido.java
│       ├── PedidoService.java
│       ├── PedidoRepository.java
│       ├── IPagamento.java
│       ├── IPagamentoParcelamento.java
│       ├── IPagamentoBoleto.java
│       ├── PagamentoCartao.java
│       ├── PagamentoPix.java
│       ├── PagamentoBoleto.java
│       ├── CalculadoraFrete.java
│       ├── Entrega.java
│       ├── EntregaRetiradaLoja.java
│       ├── CalculadoraDesconto.java
│       └── DescontoAluno.java
├── README.md
├── Relatorio_Refatoracao.md
├── .gitignore
└── pedidos.txt