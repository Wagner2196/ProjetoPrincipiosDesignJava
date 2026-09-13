package projetoprincipiosdesign;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LOJA ACADÊMICA ===");

        Cliente cliente = new Cliente(
                "Ana",
                new Endereco(
                        "Rua das Flores",
                        new Cidade("Belo Horizonte")));

        // Segundo cliente para teste
        Cliente cliente2 = new Cliente(
                "Carlos",
                new Endereco(
                        "Rua Central",
                        new Cidade("Alfenas")));

        Pedido pedido = new Pedido(
                cliente,
                List.of(
                        new ItemPedido(
                                "Livro de Engenharia de Software",
                                120.0,
                                1),
                        new ItemPedido(
                                "Caderno",
                                20.0,
                                2)));

        // Pedido do segundo cliente
        Pedido pedido2 = new Pedido(
                cliente2,
                List.of(
                        new ItemPedido(
                                "Caneta",
                                10.0,
                                2),
                        new ItemPedido(
                                "Mochila",
                                80.0,
                                1)));

        PagamentoCartao pagamento = new PagamentoCartao();

        // Define a regra de desconto usada no pedido.
        CalculadoraDesconto desconto = new DescontoAluno();

        // O serviço recebe o pagamento e o desconto.
        PedidoService servico = new PedidoService(
                pagamento,
                desconto);

        System.out.println();
        System.out.println("Cidade de entrega:");
        System.out.println(servico.obterCidadeEntrega(pedido));

        System.out.println();
        System.out.println("Total com desconto:");

        // O desconto já foi definido no construtor.
        System.out.printf(
                "R$ %.2f%n",
                servico.calcularTotal(pedido));

        System.out.println();
        System.out.println("Pagamento:");
        servico.finalizarPedido(pedido);

        // Teste do segundo cliente
        System.out.println();
        System.out.println("Segundo pedido:");

        System.out.println("Cidade de entrega:");
        System.out.println(servico.obterCidadeEntrega(pedido2));

        System.out.println("Total com desconto:");
        System.out.printf(
                "R$ %.2f%n",
                servico.calcularTotal(pedido2));

        servico.finalizarPedido(pedido2);

        System.out.println();
        System.out.println("Programa executado com sucesso.");
    }
}