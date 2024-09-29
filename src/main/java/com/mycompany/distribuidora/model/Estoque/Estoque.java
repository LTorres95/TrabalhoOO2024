package com.mycompany.distribuidora.model.Estoque;

import java.util.*;
import com.mycompany.distribuidora.model.Estoque.exceptions.*;

public  class Estoque {
    private List<Produto> produtos;
    private List<Cupom> cupons;
    private List<Promocao> promocoes;

    Estoque()
    {
        produtos=new ArrayList<>();
        cupons=new ArrayList<>();
        promocoes= new ArrayList<>();
    }
    
   
   public Produto getProduto(int codProduto)
   {
        for(int i=0;i<produtos.size();i++)
            if(produtos.get(i).getCodigo()==codProduto)
                return produtos.get(i);
        return null;
   }
   public void removeQuantidadeDeLote(int quantidade,int codProduto,int codLote)
   {
    Produto pAux=getProduto(codProduto);
    Lote lAux=pAux.getLotePorCodigo(codLote);
    lAux.adicionaQuantidadeLote(quantidade);
    pAux.removeLote(codLote);
    pAux.cadastrarLote(lAux);
    produtos.remove(codigoProdToIndex(codProduto));
    produtos.add(pAux);
   }
   public Lote getLote(int codProduto,int codLote)
   {
        return getProduto(codProduto).getLotePorCodigo(codLote);
   }
   public int codigoProdToIndex(int codigo)
   {
    for(int i=0;i<produtos.size();i++)
        if(produtos.get(i).getCodigo()==codigo)
            return i;
        return -1;
   }
   public Cupom getCupomPorCodigo(int codCupom)
   {
        for(int i=0;i<cupons.size();i++)
            if(cupons.get(i).getCodigoCupom()==codCupom)
                return cupons.get(i);
        return null;
   }
   public List<Produto>getProdutos(){return produtos;}
   public List<Cupom>getCupons(){return cupons;}
   public List<Promocao>getPromocoes(){return promocoes;}
   public int getQuantidadeIndex(int i)
   {
        return produtos.get(i).getQuantidade();
   }
   public int getQuantidade(int codProduto)
   {
        for(int i=0;i<produtos.size();i++)
        {
            if(produtos.get(i).getCodigo()==codProduto)
                return produtos.get(i).getQuantidade();
        }
        return -1;
   }

   public int getQuantidadeLote(int codProduto,int codLote)
   {
        for(int i=0;i<produtos.size();i++)
        {
            if(produtos.get(i).getCodigo()==codProduto)
            {
                return produtos.get(i).getLotePorCodigo(codLote).getQuantidade();
            }
        }
        return -1;
   }
   public List<Produto> getNDiasProximoValidade(int nDias,Data data)
   {
        List<Produto> retorno= new ArrayList<>();
        for(int i=0;i<produtos.size();i++)
        {
            Produto aux=produtos.get(i);
            Produto aux1=new Produto(aux.getCodigo(), aux.getNome(),aux.getPreco(),aux.getImposto());
            for(int j=0;j<aux.getNLotes();j++)
            {
                int diasAteVencimento=aux.getLotePorIndice(j).diasAteVencimento(data);
                if(diasAteVencimento>=0 & diasAteVencimento<=nDias)
                    aux1.cadastrarLote(aux.getLotePorIndice(j));
            }
            retorno.add(aux1);
        }
        return retorno;
   }
   
   public boolean cupomExiste(int codCupom)
   {
        for(int i=0;i<cupons.size();i++)
            if(cupons.get(i).getCodigoCupom()==codCupom)
                return true;
        return false;
   }
   public double darBaixa(int codProduto,int codLote,int quantidade)
   {
        produtos.get(codigoProdToIndex(codProduto)).retirarQuantidade(codLote, quantidade);
        for(int i=0;i<promocoes.size();i++)
        {
            if(codProduto==promocoes.get(i).getCodigoProduto()){
                return promocoes.get(i).getValor()*quantidade;
            }
        }
        return produtos.get(codigoProdToIndex(codProduto)).getPreco()*quantidade;
        
        
        
   }
   public double darBaixaCupom(int codProduto,int codLote,int quantidade,int codCupom)  throws CupomException
   {
        if(!cupomExiste(codCupom))
        {
            throw new CupomException();
        }
        for(int i=0;i<cupons.size();i++)
        {
            if(codProduto==cupons.get(i).getCodigoProduto()){
                produtos.get(codigoProdToIndex(codProduto)).retirarQuantidade(codLote, quantidade);
                return cupons.get(i).getValor()*quantidade;
            }
       }
        

       return -1.0;
   }

   public void criarCupom(int codProduto,double novoValor,int codigoCupom)
   {
        Cupom c=new Cupom(codProduto, novoValor, codigoCupom);
        cupons.add(c);
   }
   public void criarPromocao(int codProduto,double novoValor)
   {
        Promocao p=new Promocao(codProduto,novoValor);
        promocoes.add(p);
   }

   public void adicionarProduto(Produto aSerAdicionado)
   {
        produtos.add(aSerAdicionado);
   }
   public void adicionarEstoque(Lote aSerAdicionado,int codproduto)
   {
        int index=codigoProdToIndex(codproduto);
        Produto aux = produtos.get(index);
        produtos.remove(index);
        aux.cadastrarLote(aSerAdicionado);
        produtos.add(aux);
   }
   public boolean produtoEstaNoEstoque(int codProduto)
   {
        
        for(int i=0;i<produtos.size();i++)
        {
            if(produtos.get(i).getCodigo()==codProduto)
                return true;
        }
        return false;
   }
}
