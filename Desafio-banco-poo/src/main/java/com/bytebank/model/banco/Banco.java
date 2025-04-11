package main.java.com.bytebank.model.banco;

import main.java.com.bytebank.interfaces.IBanco;
import main.java.com.bytebank.model.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco implements IBanco {
    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("Conta adicionada com sucesso ao banco " + this.nome);
    }

    @Override
    public void listarContas() {
        System.out.println("=== Lista de Contas do Banco " + this.nome + " ===");
        for (Conta conta : contas) {
            System.out.println("-------------------------");
            System.out.println("Tipo: " + (conta instanceof main.java.com.bytebank.model.conta.ContaCorrente ? "Conta Corrente" : "Conta Poupança"));
            System.out.println("Titular: " + conta.getCliente().getNome());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Número: " + conta.getNumero());
            System.out.println("Saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        }
    }
    
    @Override
    public void calcularTaxasTotais() {
        double taxasTotais = 0;
        for (Conta conta : contas) {
            taxasTotais += conta.calcularTaxas();
        }
        System.out.printf("Total de taxas a serem cobradas: R$ %.2f%n", taxasTotais);
    }
}