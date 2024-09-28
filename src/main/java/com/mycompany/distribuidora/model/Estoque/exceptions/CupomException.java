package com.mycompany.distribuidora.model.Estoque.exceptions;

public class CupomException extends Exception{
    public CupomException()
    {
        super("Esse cupom nao existe!");
    }
}
