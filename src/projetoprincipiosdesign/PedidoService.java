package projetoprincipiosdesign;

public class PedidoService {

    private IPagamento pagamento;
    private PedidoRepository pedidoRepository;
    private CalculadoraDesconto calculadoraDesconto;

    public PedidoService(
            IPagamento pagamento,
            CalculadoraDesconto calculadoraDesconto) {

        this.pagamento = pagamento;
        this.calculadoraDesconto = calculadoraDesconto;
        this.pedidoRepository = new PedidoRepository();
    }

    public double calcularTotal(Pedido pedido) {
        double total = 0.0;

        for (ItemPedido item : pedido.getItens()) {
            total += item.getPreco() * item.getQuantidade();
        }

        double desconto = calculadoraDesconto.calcularDesconto(total);

        return total - desconto;
    }

    public String obterCidadeEntrega(Pedido pedido) {
        return pedido.obterCidadeEntrega();
    }

    public void finalizarPedido(Pedido pedido) {
        double total = calcularTotal(pedido);

        System.out.println("Salvando pedido em arquivo...");
        pedidoRepository.salvar(pedido, total);

        System.out.println("Gerando resumo do pedido...");
        System.out.println("Cliente: " + pedido.obterNomeCliente());
        System.out.printf("Total: R$ %.2f%n", total);

        pagamento.pagar(total);

        System.out.println(
                "Enviando mensagem para " + pedido.obterNomeCliente()
                        + ": pedido finalizado.");
    }
}