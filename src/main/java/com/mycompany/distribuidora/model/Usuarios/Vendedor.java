/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.CPF;
import com.mycompany.distribuidora.model.Email;
import com.mycompany.distribuidora.model.Telefone;

/**
 *
 * @author ltmar
 */
public class Vendedor extends Pessoa{
    private static int idVendedor=0;
    private String senha;
    CPF cpf;
    

    public Vendedor(String nome, Telefone telefone, String login, 
            String endereco, Email email) 
            throws EmailException, TelefoneException{
        
        super(nome, telefone, email.getLogin(), endereco, email);
        idVendedor = Vendedor.idVendedor++;
        
    }
    
    //public void Venda(){};
    
    
}
