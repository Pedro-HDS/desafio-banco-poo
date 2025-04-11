package  main.java.com.bytebank.model.conta;

import  main.java.com.bytebank.model.Cliente;

public class ContaCorrente extends Conta {
    private static final double TAXA_MENSAL = 12.90;
    
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfoComuns();
        System.out.println(String.format("Taxa mensal: R$ %.2f", TAXA_MENSAL));
    }
    
    @Override
    public double calcularTaxas() {
        return TAXA_MENSAL;
    }
    
    @Override
    public void sacar(double valor) {
        final double TAXA_SAQUE = 0.5;
        if (valor > 0 && (valor + TAXA_SAQUE) <= saldo) {
            saldo -= (valor + TAXA_SAQUE);
            System.out.printf("Saque de R$ %.2f realizado com sucesso na conta de %s (taxa de R$ %.2f)%n", 
                valor, cliente.getNome(), TAXA_SAQUE);
        } else {
            System.out.println("Não foi possível realizar o saque. Verifique o valor solicitado e seu saldo.");
        }
    }
}