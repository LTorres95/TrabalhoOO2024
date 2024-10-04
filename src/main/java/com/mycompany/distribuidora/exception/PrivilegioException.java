package com.mycompany.distribuidora.exception;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class PrivilegioException extends Exception{
    public PrivilegioException()
    {
        super("Tentativa de criar um privilegio para um tipo de ususario inexistente!");
    }
    public PrivilegioException(String s)
    {
        super(s);
    }
}
