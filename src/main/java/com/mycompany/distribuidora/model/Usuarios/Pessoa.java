package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.Email;
import com.mycompany.distribuidora.model.Telefone;

public class Pessoa {
    protected String nome;
    protected Telefone telefone;
    protected Email email;
    protected String login;
    protected String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, String telefoneInput, String login, String endereco,
            String emailInput) throws EmailException, TelefoneException
    {
        this.nome = nome;
        try{
            this.telefone = new Telefone(telefoneInput);
        }
        catch(TelefoneException e) {
            throw new TelefoneException();
        }
        this.login = this.email.getLogin();
        this.endereco = endereco;
        try {
            this.email = new Email(emailInput);
        }   
        catch (EmailException e){
            throw new EmailException();
        }
        
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
