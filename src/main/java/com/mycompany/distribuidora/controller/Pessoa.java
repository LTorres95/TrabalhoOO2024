package com.mycompany.distribuidora.controller;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.Email;
import com.mycompany.distribuidora.model.Telefone;

public class Pessoa {
    private String nome;
    Telefone telefone;
    Email email;
    String login;
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, Telefone telefoneInput, String login, String endereco,
            Email email) throws EmailException, TelefoneException
    {
        this.nome = nome;
        this.telefone = telefoneInput;
        this.login = this.email.getLogin();
        this.endereco = endereco;
        this.email = email;               
        
    }
    

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone.toString();
    }


}
