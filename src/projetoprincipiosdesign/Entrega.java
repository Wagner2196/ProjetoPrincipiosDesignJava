package projetoprincipiosdesign;

public class Entrega {

    private CalculadoraFrete calculadoraFrete;

    public Entrega(CalculadoraFrete calculadoraFrete) {
        this.calculadoraFrete = calculadoraFrete;
    }

    public double calcularFrete(double total) {
        return calculadoraFrete.calcularFrete(total);
    }

}