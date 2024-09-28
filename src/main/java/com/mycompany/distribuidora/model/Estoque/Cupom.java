package com.mycompany.distribuidora.model.Estoque;

public class Cupom extends Promocao{
    
    protected int codigoCupom;
    
    Cupom(int codigoProduto,double novoValor,int codigoCupom)
    {
        super(codigoProduto,novoValor);
        this.codigoCupom=codigoCupom;
    }

    int getCodigoCupom(){return codigoCupom;}
    
}
