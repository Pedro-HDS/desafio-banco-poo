package main.java.com.bytebank.service.autenticacao;

import main.java.com.bytebank.model.Cliente;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SistemaAutenticacao {
    private Map<String, String> credenciais;
    private Set<String> usuariosAtivos;
    
    public SistemaAutenticacao() {
        this.credenciais = new HashMap<>();
        this.usuariosAtivos = new HashSet<>();
    }
    
    public void cadastrarUsuario(Cliente cliente, String senha) {
        if (credenciais.containsKey(cliente.getCpf())) {
            System.out.println("Usuário já cadastrado. Use outro CPF ou redefina a senha.");
            return;
        }
        
        // Normalmente, armazenaríamos a senha criptografada
        // Em um ambiente real, usaríamos bcrypt ou algoritmo similar
        credenciais.put(cliente.getCpf(), senha);
        cliente.setSenhaHash(senha); // Simplificado para o exemplo
        System.out.println("Usuário " + cliente.getNome() + " cadastrado com sucesso!");
    }
    
    public boolean autenticar(String cpf, String senha) {
        if (credenciais.containsKey(cpf) && credenciais.get(cpf).equals(senha)) {
            usuariosAtivos.add(cpf);
            System.out.println("Login realizado com sucesso!");
            return true;
        }
        
        System.out.println("Falha na autenticação. Verifique CPF e senha.");
        return false;
    }
    
    public void logout(String cpf) {
        if (usuariosAtivos.contains(cpf)) {
            usuariosAtivos.remove(cpf);
            System.out.println("Logout realizado com sucesso!");
        } else {
            System.out.println("Usuário não está logado.");
        }
    }
    
    public boolean estaAutenticado(String cpf) {
        return usuariosAtivos.contains(cpf);
    }
    
    public boolean atualizarSenha(String cpf, String senhaAtual, String novaSenha) {
        if (credenciais.containsKey(cpf) && credenciais.get(cpf).equals(senhaAtual)) {
            credenciais.put(cpf, novaSenha);
            System.out.println("Senha atualizada com sucesso!");
            return true;
        }
        
        System.out.println("Falha ao atualizar senha. Verifique sua senha atual.");
        return false;
    }
    
    public void recuperarSenha(String cpf, String email) {
        if (credenciais.containsKey(cpf)) {
            System.out.println("Um link de recuperação de senha foi enviado para " + email);
        } else {
            System.out.println("CPF não encontrado no sistema.");
        }
    }
}