package com.mycompany.distribuidora.exception;

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
