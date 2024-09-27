/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.model;

import com.mycompany.distribuidora.exception.CPFException;
/**
 *
 * @author ltmar
 */
public class CPF {
    private String cpf;

    public CPF  (String cpf) throws CPFException {
        setCPF(cpf);
    }
    
    public boolean validaCPF (String cpf) {
        // considera-se erro cpf"s formados por uma sequencia de numeros iguais
        cpf = cpf.replaceAll("-", "");
        cpf = cpf.replaceAll(" ", "");
        cpf = cpf.replaceAll("\\.", "");
        //Verifica se o CPF é composto por digitos repetidos
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return false;

        char dig10, dig11;
        
        int somaacumulada, i, result, num, peso;

        
        
            // Calculo do 1o. Digito Verificador
            somaacumulada = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-ésimo caractere do cpf em um numero:
                // por exemplo, transforma o caractere "0" no inteiro 0
                // (48 eh a posição de "0" na tabela ASCII)
                num = (int)(cpf.charAt(i) - 48);
                somaacumulada += (num * peso);
                peso -= 1;
            }

            result = 11 - (somaacumulada % 11);
            if ((result == 10) || (result == 11))
                dig10 = '0';
            else dig10 = (char)(result + 48); // converte no respectivo caractere numérico

            // Calculo do 2o. Digito Verificador
            somaacumulada = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                somaacumulada = somaacumulada + (num * peso);
                peso = peso - 1;
            }

            result = 11 - (somaacumulada % 11);
            if ((result == 10) || (result == 11))
                dig11 = '0';
            else dig11 = (char)(result + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        
        }
    
    
    public void setCPF(String cpf) throws CPFException{
        if (!validaCPF(cpf)){
            throw new CPFException();
        }
        this.cpf = formataCPF(cpf);
        // considera-se erro cpf"s formados por uma sequencia de numeros iguais
               
        
    }
    
    public String formataCPF(String cpf){
        cpf = cpf.replaceAll("-", "");
        cpf = cpf.replaceAll(" ", "");
        cpf = cpf.replaceAll("\\.", "");
        
        cpf = cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
        return cpf;
    }

}
