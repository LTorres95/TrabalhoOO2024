package com.mycompany.distribuidora.model.Usuarios;

public class Usuario {

    private String nome;
    private String telefone;
    private String email;
    private String senha;

    // Construtor padrão necessário para a deserialização
    public Usuario() {
    }

    public Usuario(String nome, String telefone, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return nome + "," + telefone + "," + email + "," + senha;
    }
}
