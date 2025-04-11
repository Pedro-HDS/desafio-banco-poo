package main.java.com.bytebank.model;

import main.java.com.bytebank.interfaces.IAutenticavel;
import main.java.com.bytebank.model.Cliente;


public class Cliente implements IAutenticavel {
    private String nome;
    private String cpf;
    private String email;
    private String senhaHash; // Armazenaria o hash da senha, não a senha em texto plano

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
    
    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
    
    @Override
    public boolean autenticar(String senha) {
        // Em uma implementação real, verificaríamos se o hash da senha fornecida
        // corresponde ao hash armazenado
        return senhaHash != null && senhaHash.equals(senha);
    }
}