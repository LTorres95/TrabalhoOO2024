package com.mycompany.distribuidora.model.Estoque;

import com.mycompany.distribuidora.model.Estoque.exceptions.LoteException;

public class Lote {

    private Data dataAquisicao;
    private int validade; // numero de dias
    private int quantidadeDeItens;
    private int codigoLote;

    Lote(Data dataAquisicao, int validade, int quantidadeDeItens, int codigoLote) throws LoteException {
        if (validade <= 0) {
            throw new LoteException("A validade deve ser maior que 0.");
        }
        if (quantidadeDeItens < 0) {
            throw new LoteException("A quantidade de itens não pode ser negativa.");
        }
        this.dataAquisicao = dataAquisicao;
        this.validade = validade;
        this.quantidadeDeItens = quantidadeDeItens;
        this.codigoLote = codigoLote;
    }

    public Data getAquisicao() {
        return dataAquisicao;
    }

    public int getValidade() {
        return validade;
    }

    public int diasAteVencimento(Data date) throws LoteException {
        if (date == null) {
            throw new LoteException("A data fornecida é nula.");
        }
        int d1 = date.toDays(), d2 = dataAquisicao.toDays();
        return d1 - d2;
    }

    public int getQuantidade() {
        return quantidadeDeItens;
    }

    public int getCodigoLote() {
        return codigoLote;
    }

    public boolean removeQuantidade(int quantidade) throws LoteException {
        if (quantidade < 0) {
            throw new LoteException("A quantidade a ser removida não pode ser negativa.");
        }
        if (quantidade > quantidadeDeItens) {
            throw new LoteException("A quantidade a ser removida excede a quantidade disponível no lote.");
        }
        quantidadeDeItens = quantidadeDeItens - quantidade;
        return true;
    }

    public void adicionaQuantidadeLote(int q) {
        quantidadeDeItens += q;
    }

}
