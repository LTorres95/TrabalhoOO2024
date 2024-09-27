package com.mycompany.distribuidora.model;

public class Cliente extends Pessoa{
    private static int idCliente;
    private String senha;

    public Cliente() {
    }

    public Cliente(String nome, String telefone, String email) {
        super(nome, telefone, email);
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

    private void setSenha(String senha) {
        this.senha = senha;
    }


}
