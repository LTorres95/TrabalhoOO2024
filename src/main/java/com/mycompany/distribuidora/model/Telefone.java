/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.model;

import com.mycompany.distribuidora.exception.TelefoneException;

/**
 *
 * @author ltmar
 */
public class Telefone {
    private String telefone;
    private String ddd;
    
    
    public Telefone(String telefone) throws TelefoneException{  
        setTelefone(telefone);
    }
    
    //telefone válido formato "(32)99175-2829"
    //tamanho 
    public boolean validaTelefone(String telefone){
        if (telefone.charAt(0) != '(' || telefone.charAt(3) != ')'
                || !telefone.contains("-"))
            return false;
        
        else if (telefone.length()!=14 || telefone.length() != 13)
            return false;
        
        return true;
        
    }
    
    
    public void setTelefone (String telefone) throws TelefoneException{
        if (!validaTelefone(telefone)){
            throw new TelefoneException();
        }       
        
            this.ddd = telefone.substring(1, 3);
            this.telefone = telefone.substring(5, telefone.length() - 4) 
                    + "-" + 
                    telefone.substring(telefone.length() - 4);       
    }
    
    public String getDdd() {
        return ddd;
    }   

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    
    public String getTelefone(){
        return telefone;
    }

    @Override
    public String toString() {
        return "(" + ddd + ")" + telefone;
    }
    
}
