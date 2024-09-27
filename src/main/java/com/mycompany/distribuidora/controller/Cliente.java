package com.mycompany.distribuidora.controller;

import com.mycompany.distribuidora.model.CPF;
import com.mycompany.distribuidora.model.Telefone;
import com.mycompany.distribuidora.model.Email;


public class Cliente extends Pessoa{
    private static int idCliente;
    private String senha;
    CPF cpf;
    

    public Cliente() {
    }

    public Cliente(String nome, Telefone telefone, String login, 
            String endereco, Email email) {
        super(nome, telefone, email.getLogin(), endereco, email);
        idCliente = Cliente.idCliente++;
        
    }
    
    //implementar
    public void inserirProduto(){    }
    //implementar
    public void retirarProduto(){    }
    //implementar
    public void consultarProdutos(){    }
    //implementar
    public void finalizarPedido(){    }
    //implementar
    public void pagamento(){ }

    private String getSenha() {
        return senha;
    }
    
    public String getCPF(CPF cpf) {
        return cpf.formataCPF(login);
    }
    
    private void setSenha(String senha) {
        this.senha = senha;
    }


}
