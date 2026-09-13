package projetoprincipiosdesign;

public class DescontoAluno implements CalculadoraDesconto {

    @Override
    public double calcularDesconto(double total) {
        return total * 0.10;
    }
}