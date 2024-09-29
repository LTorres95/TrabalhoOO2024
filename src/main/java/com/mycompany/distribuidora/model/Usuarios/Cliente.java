package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.CPF;


public class Cliente extends Pessoa{
    protected static int idCliente=0;
    protected String senha;
    protected CPF cpf;
    

    public Cliente(String nome, String telefoneInput, String login, 
            String endereco, String emailInput) 
            throws EmailException, TelefoneException{
        super(nome, telefoneInput,
                emailInput.substring(0, emailInput.indexOf("@")),
                endereco, emailInput);
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
    public void pagamento(){  }

    protected String getSenha() {
        return senha;
    }
    
    public String getCPF(CPF cpf) {
        return cpf.formataCPF(login);
    }
    
    protected void setSenha(String senha) {
        this.senha = senha;
    }


}
