package com.mycompany.distribuidora.model.Estoque;

public class Promocao {
    protected int codigoProduto;
    protected double novoValor;

    Promocao(int codigoProduto,double novoValor)
    {
        this.codigoProduto=codigoProduto;
        this.novoValor=novoValor;
    }
    public int getCodigoProduto(){return codigoProduto;}
    public double getValor(){return novoValor;}
}
