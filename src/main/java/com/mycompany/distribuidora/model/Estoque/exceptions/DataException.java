package com.mycompany.distribuidora.model.Estoque.exceptions;

public class DataException extends Exception{
    public DataException()
    { 
        super("A data inserida eh invalida!");
    }
}
