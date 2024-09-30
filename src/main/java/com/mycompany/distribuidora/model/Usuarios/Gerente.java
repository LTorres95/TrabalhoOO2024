package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.Telefone;
import com.mycompany.distribuidora.model.Email;

public class Gerente extends Pessoa {

    protected String senha;
    protected static int idGerente = 0;

    // Construtor privado para uso apenas dentro da classe
    public Gerente(String nome, String telefoneInput, String login,
            String endereco, String emailInput) throws EmailException,
            TelefoneException {
        super(nome, telefoneInput,
                emailInput.substring(0, emailInput.indexOf("@")),
                endereco, emailInput);
        idGerente++;
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

    //public void addEstoque(Produto p){};
    //public void relatorioEstoque(HashMap<Produtos, int> relatorioEstoque){};
}
