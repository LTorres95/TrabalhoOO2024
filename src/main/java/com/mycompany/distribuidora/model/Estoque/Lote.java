package com.mycompany.distribuidora.model.Estoque;

public class Lote {
    private Data dataAquisicao;
    private int validade; // numero de dias
    private int quantidadeDeItens;
    private int codigoLote;


    Lote(Data dataAquisicao,int validade,int quantidadeDeItens,int codigoLote)
    {
        this.dataAquisicao=dataAquisicao;
        this.validade=validade;
        this.quantidadeDeItens=quantidadeDeItens;
        this.codigoLote=codigoLote;
    }
    public Data getAquisicao(){return dataAquisicao;}
    
    public int getValidade(){return validade;}
    
    public int diasAteVencimento(Data date)
    {
        int d1=date.toDays(),d2=dataAquisicao.toDays();
        return d1-d2;
    }

    public int getQuantidade(){return quantidadeDeItens;}

    public int getCodigoLote(){return codigoLote;}

    public boolean removeQuantidade(int quantidade)
    {
        if(quantidade>=0 & quantidade<=quantidadeDeItens)
        {
            quantidadeDeItens=quantidadeDeItens-quantidade;
            return true;
        }
        return false;
    }
    

}
