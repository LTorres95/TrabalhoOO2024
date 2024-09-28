/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.model;
import com.mycompany.distribuidora.exception.EmailException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ltmar
 */
public class Email {
    private String email;
    private String login;
    private String dominio;
    
    public Email(String email) throws EmailException{
        try{
            setEmail(email);
        }
        catch (EmailException e){
            throw new EmailException();
        }
    }
    
    private boolean ehValidoEmail(String email){
        //retirado do exemplo da agenda
        String emailRegex = 
          "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email) throws EmailException{
    if (!ehValidoEmail(email))
        throw new EmailException();
    
    this.email = email; 
    
    String[] partes = email.split("@");
    
    this.login = partes[0];
    this.dominio = partes[1];
    }
    
    public String getLogin() {
        return login;
    }

    public String getDominio() {
        return dominio;
    }

    @Override
    public String toString() {
        return login + "@" + dominio;
    }
    
    

}
