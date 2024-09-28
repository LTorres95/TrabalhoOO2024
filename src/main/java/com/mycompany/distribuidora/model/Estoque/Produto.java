package com.mycompany.distribuidora.model.Estoque;

import java.util.*;

public class Produto
{
    private String nome;
    private int codigo;
    private double preco;
    private List<Lote> lotes;
    private double imposto;
    
    private boolean valid(int number,int lowestLimit,int greatest)
    {
        if(number>lowestLimit & number<=greatest)
            return true;
        return false;
    }
    
    private int getIndexLoteCodigo(int cod)
    {
        int aux=0;
        for(int i=0;i<lotes.size();i++)
        {
            if(lotes.get(i).getCodigoLote()==codigo)
                aux=i;
        }
        return aux;
    }
    public int getCodigo(){return codigo;}
    public Produto(int codigo,String nome,double preco,double imposto)
    {
        this.nome=nome;
        this.codigo=codigo;
        this.preco=preco;
        this.imposto=imposto;
        lotes=new ArrayList<>();
    }
    public int getQuantidade()
    {
        int tot=0;
        for(int i=0;i<lotes.size();i++)
            tot+=lotes.get(i).getQuantidade();
        return tot;
    }
    public boolean loteIndiceExiste(int index)
    {
        if(index>=0 & index<lotes.size())
            return lotes.isEmpty();
        return false;
    }
    public boolean loteCodigoExiste(int codigo)
    {
        for(int i=0;i<lotes.size();i++)
        {
            if(lotes.get(i).getCodigoLote()==codigo)
                return true;
        }
        return false;
    }
    public Lote getLotePorIndice(int i)      // uso fora da classe : se loteIndiceExiste(i) -> getLotePorIndice(i)
    {
        return lotes.get(i);
    }
    public Lote getLotePorCodigo(int codigo)    // usofora da classe : se loteExiste(codigo) -> getLotePorCodigo(codigo)
    {
        for(int i=0;i<lotes.size();i++)
        {
            if(lotes.get(i).getCodigoLote()==codigo)
                return lotes.get(i);
        }
        return null;
    }
    public int getNLotes(){return lotes.size();}
    public double getPreco()
    {
        return preco;
    }
    public String getNome()
    {
        return nome;
    }
    public Double getImposto()
    {
        return imposto;
    }

    public void retirarQuantidade(int codigoLote,int quantidade) // se um lote foi passado por parametro , existe produto que foi escaneado que é desse lote.Assim não há necessidade de verificar se o lote existe;
    {
        Lote aux=getLotePorCodigo(codigoLote);
        lotes.remove(getIndexLoteCodigo(codigoLote));
        aux.removeQuantidade(quantidade);
        lotes.add(aux);
    }
    public void cadastrarLote(Data dataAquisicao,int validade,int quantidade,int codigoLote)
    {
        Lote aux=new Lote(dataAquisicao,validade,quantidade,codigoLote);
        lotes.add(aux);
    }
    public void cadastrarLote(Lote lote)
    {
        lotes.add(lote);
    }
    public void alterarPreco(Double valor)
    {
        preco=valor;

    }
    public boolean loteExiste(int codLote)
    {
        for(int i=0;i<lotes.size();i++)
            if(lotes.get(i).getCodigoLote()==codLote)
                return true;
        return false;
    }
    public void removeLote(int codLote)
    {
        for(int i=0;i<lotes.size();i++)
            if(lotes.get(i).getCodigoLote()==codLote)
                lotes.remove(i);
    }
}