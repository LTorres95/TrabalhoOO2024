package com.mycompany.distribuidora.controller;

import com.mycompany.distribuidora.model.Telefone;
import com.mycompany.distribuidora.model.Email;

public class Gerente extends Pessoa {
    private String senha;
    

    public Gerente(String nome, Telefone telefone, String login, 
            String endereco, Email email)
    {
        super(nome, telefone, email.getLogin(), endereco, email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone.getTelefone();
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
    
}
