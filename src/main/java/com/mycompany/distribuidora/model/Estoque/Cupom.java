package com.mycompany.distribuidora.model.Estoque;

import com.mycompany.distribuidora.model.Estoque.exceptions.CupomException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PromocaoException;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class Cupom extends Promocao {

    protected int codigoCupom;

    Cupom(int codigoProduto, double novoValor, int codigoCupom) throws PromocaoException, CupomException {
        super(codigoProduto, novoValor);
        if (codigoCupom <= 0) {
            throw new CupomException("O código do cupom é inválido. Deve ser maior que 0.");
        }

        if (novoValor <= 0) {
            throw new CupomException("O valor do cupom é inválido. Deve ser maior que 0.");
        }
        this.codigoCupom = codigoCupom;
    }

    public int getCodigoCupom() {
        return codigoCupom;
    }

}