package com.mycompany.distribuidora.model.Estoque;

import com.mycompany.distribuidora.model.Estoque.exceptions.PromocaoException;

public class Promocao {
    protected int codigoProduto;
    protected double novoValor;

    Promocao(int codigoProduto,double novoValor) throws PromocaoException
    {
        if (codigoProduto <= 0) {
            throw new PromocaoException("O código do produto é inválido. Deve ser maior que 0.");
        }
        if (novoValor <= 0) {
            throw new PromocaoException("O valor da promoção é inválido. Deve ser maior que 0.");
        }
        this.codigoProduto=codigoProduto;
        this.novoValor=novoValor;
    }
    public int getCodigoProduto(){return codigoProduto;}
    public double getValor(){return novoValor;}
}
