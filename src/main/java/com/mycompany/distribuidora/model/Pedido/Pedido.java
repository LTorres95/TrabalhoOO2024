package com.mycompany.distribuidora.model.Pedido;

import com.mycompany.distribuidora.model.Estoque.*;
import com.mycompany.distribuidora.model.Estoque.exceptions.*;

import java.util.*;


public class Pedido {
    private List<Produto> produtos;
    private int codigoPedido;
    private double total;
    private Estoque estoque;

    Pedido(int codigo,Estoque estoque)
    {
        produtos=new ArrayList<>();
        codigoPedido=codigo;
        total=0;
        this.estoque=estoque;
    }

    public boolean estaNaLista(int codProduto)
    {
        for(int i=0;i<produtos.size();i++)
            if(produtos.get(i).getCodigo()==codProduto)
                return true;
        return false;
    }
    private int indexOfProduto(int codProduto)
    {
        for(int i=0;i<produtos.size();i++)
            if(produtos.get(i).getCodigo()==codProduto)
                return i;
        return -1;
    }

    public void adicionaProduto(int codigoProduto,String nome,int codLote,int quantidade,Data aquisicao,int validade,double imposto) throws PedidoException
    {
        if(quantidade<0)
            throw new PedidoException("Tentativa de solicitar uma quantidade negativa de um produto!");
        if(!estoque.produtoEstaNoEstoque(codigoProduto))
        {
            throw new PedidoException("Tentativa de adicionar um produto que nao existe ao pedido!");
        }
        if(estoque.getQuantidadeIndex(estoque.codigoProdToIndex(codigoProduto))<quantidade)
        {
            throw new PedidoException("tentativa de adicionar uma quantidade do produto que esta indisponivel!");
        }
        if(estaNaLista(codigoProduto))
        {
            Produto aux = produtos.get(indexOfProduto(codigoProduto));
            produtos.remove(indexOfProduto(codigoProduto));
            if(aux.loteExiste(codLote))
            {
                Lote loteAux=aux.getLotePorCodigo(codLote);
                aux.removeLote(codLote);
                loteAux.adicionaQuantidadeLote(quantidade);
                aux.cadastrarLote(loteAux);
            }
            else
            {
                aux.cadastrarLote(aquisicao,validade,quantidade,codLote);
            }
        }
        else
        {
            Produto aux= new Produto(codigoProduto, nome, quantidade,imposto);
            aux.cadastrarLote(aquisicao,validade,quantidade,codLote);
        }
        
    }
    public void removeProduto(int codProduto,int quantidade,int codLote) throws PedidoException
    {
        if(!estaNaLista(codProduto))
            throw new PedidoException("Exception ao tentar remover produto que sequer estah na lista de produtos!");
        if(produtos.get(indexOfProduto(codProduto)).getQuantidade()<quantidade)
            throw new PedidoException("Exception ao tentar remover uma quantidade da lista de produtos maior que a quantidade presente!");
        Produto aux= produtos.get(indexOfProduto(codProduto));
        produtos.remove(indexOfProduto(codProduto));
        Lote loteAux=aux.getLotePorCodigo(codLote);
        aux.removeLote(codLote);
        loteAux.adicionaQuantidadeLote(-quantidade);
        aux.cadastrarLote(loteAux);
        produtos.add(aux);
    }
    public void removeProduto(int codProduto) throws PedidoException
    {
        if(!estaNaLista(codProduto))
            throw new PedidoException("Exception ao tentar remover produto que sequer estah na lista de produtos!");
        produtos.remove(indexOfProduto(codProduto));

    }
}
