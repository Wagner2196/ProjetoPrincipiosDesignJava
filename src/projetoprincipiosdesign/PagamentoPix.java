
package projetoprincipiosdesign;

public class PagamentoPix implements IPagamento {

    @Override
    public void pagar(double valor) {
        System.out.printf("PIX pago: R$ %.2f%n", valor);
    }

    public void parcelar(double valor, int parcelas) {
        throw new UnsupportedOperationException(
            "PIX não pode ser parcelado."
        );
    }
}