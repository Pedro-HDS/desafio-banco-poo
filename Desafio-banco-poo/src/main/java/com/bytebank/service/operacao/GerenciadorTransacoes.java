package main.java.com.bytebank.service.operacao;

import main.java.com.bytebank.model.conta.Conta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorTransacoes {
    
    // Classe interna para representar uma transação
    public static class Transacao {
        private String tipo;
        private double valor;
        private LocalDateTime data;
        private String descricao;
        
        public Transacao(String tipo, double valor, String descricao) {
            this.tipo = tipo;
            this.valor = valor;
            this.data = LocalDateTime.now();
            this.descricao = descricao;
        }
        
        @Override
        public String toString() {
            return String.format("[%s] %s: R$ %.2f - %s",
                data.toString(), tipo, valor, descricao);
        }
    }
    
    private Map<Integer, List<Transacao>> historicoTransacoes;
    
    public GerenciadorTransacoes() {
        this.historicoTransacoes = new HashMap<>();
    }
    
    public void registrarTransacao(Conta conta, String tipo, double valor, String descricao) {
        int numeroConta = conta.getNumero();
        
        if (!historicoTransacoes.containsKey(numeroConta)) {
            historicoTransacoes.put(numeroConta, new ArrayList<>());
        }
        
        historicoTransacoes.get(numeroConta).add(new Transacao(tipo, valor, descricao));
    }
    
    public void exibirHistorico(Conta conta) {
        int numeroConta = conta.getNumero();
        
        System.out.println("=== Histórico de Transações ===");
        System.out.println("Conta: " + numeroConta);
        System.out.println("Titular: " + conta.getCliente().getNome());
        
        if (!historicoTransacoes.containsKey(numeroConta) || historicoTransacoes.get(numeroConta).isEmpty()) {
            System.out.println("Não há transações registradas para esta conta.");
            return;
        }
        
        for (Transacao transacao : historicoTransacoes.get(numeroConta)) {
            System.out.println(transacao);
        }
    }
}
