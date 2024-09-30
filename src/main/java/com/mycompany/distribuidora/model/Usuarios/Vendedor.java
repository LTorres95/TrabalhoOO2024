/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.CPF;

/*
 João Nilson Quintão Barros - 202276007
 Pedro Souza Pinheiro da Silva Araujo - 202165560C
 Lucas Torres Martins - 202135023
 Gustavo do Bem Ferreira - 202065036AC
*/
public class Vendedor extends Pessoa{
    protected static int idVendedor=0;
    protected String senha;
    protected CPF cpf;
    

    public Vendedor(String nome, String telefoneInput, String login, 
            String endereco, String emailInput) 
            throws EmailException, TelefoneException{
        
        super(nome, telefoneInput, 
                emailInput.substring(0, emailInput.indexOf("@")), 
                endereco, emailInput);
        idVendedor = Vendedor.idVendedor++;
        
    }
    
    //public void Venda(){};
    
    
}
