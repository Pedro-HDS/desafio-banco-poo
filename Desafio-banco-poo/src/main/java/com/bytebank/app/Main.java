package main.java.com.bytebank.app;

import main.java.com.bytebank.model.Cliente;
import main.java.com.bytebank.model.banco.Banco;
import main.java.com.bytebank.model.conta.Conta;
import main.java.com.bytebank.model.conta.ContaCorrente;
import main.java.com.bytebank.model.conta.ContaPoupanca;
import main.java.com.bytebank.service.autenticacao.SistemaAutenticacao;
import main.java.com.bytebank.service.operacao.GerenciadorTransacoes;

public class Main {
    public static void main(String[] args) {
        // Criando o banco
        Banco meuBanco = new Banco("ByteBank");
        
        // Criando o sistema de autenticação
        SistemaAutenticacao sistemaAuth = new SistemaAutenticacao();
        
        // Criando gerenciador de transações
        GerenciadorTransacoes gerenciadorTransacoes = new GerenciadorTransacoes();
        
        // Criando clientes
        Cliente ana = new Cliente("Ana Silva", "12345678901", "ana@email.com");
        Cliente carlos = new Cliente("Carlos Oliveira", "98765432109", "carlos@email.com");
        
        // Cadastrando usuários no sistema de autenticação
        sistemaAuth.cadastrarUsuario(ana, "senha123");
        sistemaAuth.cadastrarUsuario(carlos, "senha456");
        
        // Autenticando usuários
        boolean loginAna = sistemaAuth.autenticar(ana.getCpf(), "senha123");
        boolean loginCarlos = sistemaAuth.autenticar(carlos.getCpf(), "senha456");
        
        if (loginAna && sistemaAuth.estaAutenticado(ana.getCpf())) {
            // Criando contas para Ana
            Conta contaCorrenteAna = new ContaCorrente(ana);
            Conta contaPoupancaAna = new ContaPoupanca(ana);
            
            // Adicionando contas ao banco
            meuBanco.adicionarConta(contaCorrenteAna);
            meuBanco.adicionarConta(contaPoupancaAna);
            
            // Realizando operações
            contaCorrenteAna.depositar(1000);
            gerenciadorTransacoes.registrarTransacao(contaCorrenteAna, "DEPÓSITO", 1000, "Depósito inicial");
            
            contaPoupancaAna.depositar(500);
            gerenciadorTransacoes.registrarTransacao(contaPoupancaAna, "DEPÓSITO", 500, "Depósito inicial");
            
            contaCorrenteAna.transferir(300, contaPoupancaAna);
            gerenciadorTransacoes.registrarTransacao(contaCorrenteAna, "TRANSFERÊNCIA", 300, "Transferência para Poupança");
            gerenciadorTransacoes.registrarTransacao(contaPoupancaAna, "RECEBIMENTO", 300, "Transferência da Conta Corrente");
            
            // Calculando rendimento da poupança
            ((ContaPoupanca) contaPoupancaAna).calcularRendimento();
            gerenciadorTransacoes.registrarTransacao(contaPoupancaAna, "RENDIMENTO", contaPoupancaAna.getSaldo() * 0.05, "Rendimento mensal");
            
            // Imprimindo extrato
            contaCorrenteAna.imprimirExtrato();
            System.out.println();
            contaPoupancaAna.imprimirExtrato();
            
            // Exibindo histórico de transações
            System.out.println();
            gerenciadorTransacoes.exibirHistorico(contaCorrenteAna);
            System.out.println();
            gerenciadorTransacoes.exibirHistorico(contaPoupancaAna);
            
            // Fazendo logout
            sistemaAuth.logout(ana.getCpf());
        }
        
        if (loginCarlos && sistemaAuth.estaAutenticado(carlos.getCpf())) {
            // Criando conta para Carlos
            Conta contaCorrenteCarlos = new ContaCorrente(carlos);
            
            // Adicionando conta ao banco
            meuBanco.adicionarConta(contaCorrenteCarlos);
            
            // Realizando operações
            contaCorrenteCarlos.depositar(2000);
            gerenciadorTransacoes.registrarTransacao(contaCorrenteCarlos, "DEPÓSITO", 2000, "Depósito inicial");
            
            contaCorrenteCarlos.sacar(500);
            gerenciadorTransacoes.registrarTransacao(contaCorrenteCarlos, "SAQUE", 500, "Saque em caixa eletrônico");
            
            // Imprimindo extrato
            contaCorrenteCarlos.imprimirExtrato();
            
            // Exibindo histórico de transações
            System.out.println();
            gerenciadorTransacoes.exibirHistorico(contaCorrenteCarlos);
            
            // Fazendo logout
            sistemaAuth.logout(carlos.getCpf());
        }
        
        // Listando todas as contas
        System.out.println();
        meuBanco.listarContas();
        
        // Calculando taxas totais
        System.out.println();
        meuBanco.calcularTaxasTotais();
    }
}