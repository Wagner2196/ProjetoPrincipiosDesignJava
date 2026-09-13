package projetoprincipiosdesign;

public class EntregaRetiradaLoja implements CalculadoraFrete {

    @Override
    public double calcularFrete(double total) {

        // Verifica se o pedido possui o valor mínimo.
        if (total < 50.0) {
            throw new IllegalStateException(
                    "Retirada na loja disponível apenas para pedidos a partir de R$ 50,00."
            );
        }

        // A retirada na loja não possui custo de frete.
        return 0.0;
    }
}