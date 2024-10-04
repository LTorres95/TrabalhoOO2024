package com.mycompany.distribuidora.exception;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class PessoaException extends Exception{
    public PessoaException(String functionality,String invoker)
    {
        super(invoker+" tentou utilizar metodo "+functionality+" , que esta alem de seus privilegios.");
    }
    
}