package main.java.com.bytebank.interfaces;

import main.java.com.bytebank.model.conta.Conta;

public interface IBanco {
    void adicionarConta(Conta conta);
    void listarContas();
    void calcularTaxasTotais();
}