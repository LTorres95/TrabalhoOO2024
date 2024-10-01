package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.CPF;

public class Vendedor extends Pessoa {

    protected static int idVendedor = 0;
    protected String senha;
    protected CPF cpf;
    
    public Vendedor(String nome) {
        super(nome);
    }

    public Vendedor(String nome, String telefoneInput, String login,
            String endereco, String emailInput)
        throws EmailException, TelefoneException {

        super(nome, telefoneInput,
                emailInput.substring(0, emailInput.indexOf("@")),
                endereco, emailInput,'v');
        idVendedor = Vendedor.idVendedor++;
    }
    //public void Venda(){};
}
