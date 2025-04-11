package  main.java.com.bytebank.model.conta;

import  main.java.com.bytebank.model.Cliente;

public class ContaPoupanca extends Conta {
    private static final double RENDIMENTO_MENSAL = 0.05; // 5% ao mês
    
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        super.imprimirInfoComuns();
        System.out.println(String.format("Rendimento mensal: %.1f%%", RENDIMENTO_MENSAL * 100));
    }
    
    @Override
    public double calcularTaxas() {
        return 0; // Conta poupança não tem taxa mensal
    }
    
    public void calcularRendimento() {
        double rendimento = this.saldo * RENDIMENTO_MENSAL;
        this.saldo += rendimento;
        System.out.printf("Rendimento de R$ %.2f aplicado com sucesso.%n", rendimento);
    }
}
