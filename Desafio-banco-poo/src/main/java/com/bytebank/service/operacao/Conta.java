package  main.java.com.bytebank.service.operacao;

import  main.java.com.bytebank.model.Cliente;

public abstract class Conta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado com sucesso na conta de %s%n", 
                valor, cliente.getNome());
        } else {
            System.out.println("Não foi possível realizar o saque. Verifique o valor solicitado e seu saldo.");
        }
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso na conta de %s%n", 
                valor, cliente.getNome());
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= saldo) {
            this.sacar(valor);
            contaDestino.depositar(valor);
            System.out.printf("Transferência de R$ %.2f realizada com sucesso para a conta de %s%n", 
                valor, contaDestino.cliente.getNome());
        } else {
            System.out.println("Não foi possível realizar a transferência. Verifique o valor solicitado e seu saldo.");
        }
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public abstract void imprimirExtrato();

    protected void imprimirInfoComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("CPF: %s", this.cliente.getCpf()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
    }
    
    public abstract double calcularTaxas();
}