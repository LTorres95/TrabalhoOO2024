package com.mycompany.distribuidora.controller;

import com.mycompany.distribuidora.model.Email;
import com.mycompany.distribuidora.model.Telefone;
import java.util.InputMismatchException;

public class Pessoa {
    private String nome;
    Telefone telefone;
    Email email;
    String login;
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, Telefone telefone, String login, String endereco,
            Email email)
    {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.login = email.getLogin();       
        this.endereco = endereco;
    }
    
    public boolean validacpf(String cpf) {
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
            return(false);

        char dig10, dig11;
        int somaacumulada, i, result, num, peso;

        // "try" - protege o código para eventuais erros de conversão de tipo (int)
        try {
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
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public boolean validaTelefone(String telefone) {
        //formato esperado: (32)99175-2829
        telefone = telefone.replaceAll("-", "");
        telefone = telefone.replaceAll(" ", "");
        if (telefone.charAt(0)!= '(' || telefone.charAt(3) != ')'){
            return false;
        }
        if (telefone.length() != 11){
            return false;
        }
        try{
            int num = Integer.parseInt(telefone);
            }
        catch(NumberFormatException erro){
            return false;
        }
        return true;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone.getTelefone();
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

//    private String getSenha() {
//        return senha;
//    }
//
//    private void setSenha(String senha) {
//        this.senha = senha;
//    }

}
