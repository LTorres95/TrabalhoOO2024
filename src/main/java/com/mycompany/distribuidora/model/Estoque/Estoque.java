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
    
   private int codigoProdToIndex(int codigo) throws ProdutoException
   {
    for(int i=0;i<produtos.size();i++)
        if(produtos.get(i).getCodigo()==codigo)
            return i;
    throw new ProdutoException("Produto com codigo " + codigo + " nao encontrado");    
   }
   public List<Produto>getProdutos(){return produtos;}
   public List<Cupom>getCupons(){return cupons;}
   public List<Promocao>getPromocoes(){return promocoes;}
   
   public int getQuantidadeIndex(int i) throws ProdutoException
   {
       if(i < 0 || i >= produtos.size()) 
           throw new ProdutoException("Indice do produto invalido: " + i);
       return produtos.get(i).getQuantidade();
   }
   
   public int getQuantidade(int codProduto) throws ProdutoException
   {
        for(int i=0;i<produtos.size();i++)
        {
            if(produtos.get(i).getCodigo()==codProduto)
                return produtos.get(i).getQuantidade();
        }
        throw new ProdutoException("Codigo do produto " + codProduto + " nao encontrado");   
   }

   public int getQuantidadeLote(int codProduto,int codLote) throws LoteException, ProdutoException
   {
        for(int i=0;i<produtos.size();i++)
        {
            if(produtos.get(i).getCodigo()==codProduto)
            {
                return produtos.get(i).getLotePorCodigo(codLote).getQuantidade();
            }
        }
        throw new LoteException("Lote com codigo " + codLote + " nao encontrado no produto " + codProduto);
   }
   public List<Produto> getNDiasProximoValidade(int nDias,Data data) throws DataException, LoteException, ProdutoException
   {
        if(nDias < 0) {
            throw new DataException("O numero de dias nao pode ser negativo!");
        }
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
   public double darBaixa(int codProduto,int codLote,int quantidade) throws ProdutoException, LoteException
   {
        Produto produto = produtos.get(codigoProdToIndex(codProduto));
        if(!produto.loteCodigoExiste(codLote)) {
            throw new LoteException("Lote com codigo " + codLote + " nao encontrado!");
        }
        produto.retirarQuantidade(codLote, quantidade);
        for(int i=0;i<promocoes.size();i++)
        {
            if(codProduto==promocoes.get(i).getCodigoProduto()){
                return promocoes.get(i).getValor()*quantidade;
            }
        }
        return produtos.get(codigoProdToIndex(codProduto)).getPreco()*quantidade;
        
        
        
   }
   public double darBaixaCupom(int codProduto,int codLote,int quantidade,int codCupom)  throws CupomException, ProdutoException
   {
        if(!cupomExiste(codCupom))
        {
            throw new CupomException("Cupom com codigo " + codCupom + " não existe!");
        }
        for(int i=0;i<cupons.size();i++)
        {
            if(codProduto==cupons.get(i).getCodigoProduto()){
                produtos.get(codigoProdToIndex(codProduto)).retirarQuantidade(codLote, quantidade);
                return cupons.get(i).getValor()*quantidade;
            }
       }
       throw new CupomException("Nao existe cupom para o produto com codigo " + codProduto);
   }

   public void criarCupom(int codProduto,double novoValor,int codigoCupom) throws PromocaoException, CupomException
   {
        Cupom c = null;
        try{
           c=new Cupom(codProduto, novoValor, codigoCupom);
        }catch (CupomException e) {
           System.out.println("Erro ao criar um cupom " + e.getMessage());
        }
        cupons.add(c);
   }
   public void criarPromocao(int codProduto,double novoValor) throws PromocaoException
   {
        Promocao p = null;
        try{
            p=new Promocao(codProduto,novoValor);
        }catch (PromocaoException ex) {
           System.out.println("Erro ao criar uma promocao " + ex.getMessage());
        }
        promocoes.add(p);
   }

   public void adicionarProduto(Produto aSerAdicionado)
   {
        produtos.add(aSerAdicionado);
   }
   public void adicionarEstoque(Lote aSerAdicionado,int codproduto) throws ProdutoException
   {
        int index=codigoProdToIndex(codproduto);
        Produto aux = produtos.get(index);
        produtos.remove(index);
        aux.cadastrarLote(aSerAdicionado);
        produtos.add(aux);
   }
}
