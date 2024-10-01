package com.mycompany.distribuidora.exception;

public class PessoaException extends Exception{
    public PessoaException(String functionality,String invoker)
    {
        super(invoker+" tentou utilizar metodo "+functionality+" , que esta alem de seus privilegios.");
    }
    
}
