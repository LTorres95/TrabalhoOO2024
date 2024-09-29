package com.mycompany.distribuidora.model.Estoque;

import com.mycompany.distribuidora.model.Estoque.exceptions.CupomException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PromocaoException;

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
