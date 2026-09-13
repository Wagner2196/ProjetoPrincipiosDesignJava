package projetoprincipiosdesign;

public class DescontoProfessor implements CalculadoraDesconto {

    @Override
    public double calcularDesconto(double total) {
        return total * 0.15;
    }
}