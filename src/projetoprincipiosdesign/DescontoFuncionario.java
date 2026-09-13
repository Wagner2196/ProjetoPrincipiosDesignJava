package projetoprincipiosdesign;

public class DescontoFuncionario implements CalculadoraDesconto {

    @Override
    public double calcularDesconto(double total) {
        return total * 0.20;
    }
}