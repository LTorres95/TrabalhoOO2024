package com.mycompany.distribuidora.model.Pedido;

import com.mycompany.distribuidora.model.Estoque.*;
import com.mycompany.distribuidora.model.Estoque.exceptions.*;

import java.util.*;

public class Pedido {

    private List<Produto> produtos;
    private int codigoPedido;
    private double total;
    private Estoque estoque;
    private List<Integer> cupons;

    Pedido(int codigo, Estoque estoque) {
        produtos = new ArrayList<>();
        codigoPedido = codigo;
        total = 0;
        this.estoque = estoque;
        cupons = new ArrayList<>();
    }

    public boolean estaNaLista(int codProduto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codProduto) {
                return true;
            }
        }
        return false;
    }

    private int indexOfProduto(int codProduto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codProduto) {
                return i;
            }
        }
        return -1;
    }

    public void adicionaProduto(int codigoProduto, int codLote, int quantidade) throws PedidoException, ProdutoException {
        if (quantidade < 0) {
            throw new PedidoException("Tentativa de solicitar uma quantidade negativa de um produto!");
        }
        if (!estoque.produtoEstaNoEstoque(codigoProduto)) {
            throw new PedidoException("Tentativa de adicionar um produto que nao existe ao pedido!");
        }
        if (estoque.getQuantidadeIndex(estoque.codigoProdToIndex(codigoProduto)) < quantidade) {
            throw new PedidoException("tentativa de adicionar uma quantidade do produto que esta indisponivel!");
        }
        if (estaNaLista(codigoProduto)) {
            Produto aux = produtos.get(indexOfProduto(codigoProduto));
            produtos.remove(indexOfProduto(codigoProduto));
            if (aux.loteExiste(codLote)) {
                Lote loteAux = aux.getLotePorCodigo(codLote);
                aux.removeLote(codLote);
                loteAux.adicionaQuantidadeLote(quantidade);
                aux.cadastrarLote(loteAux);
            } else {
                int index = estoque.codigoProdToIndex(codigoProduto);
                Data aquisicao = estoque.getProdutos().get(index).getLotePorCodigo(codLote).getAquisicao();
                int validade = estoque.getProdutos().get(index).getLotePorCodigo(codLote).getValidade();
                aux.cadastrarLote(aquisicao, validade, quantidade, codLote);
            }
        } else {
            int index = estoque.codigoProdToIndex(codigoProduto);
            Lote aux = estoque.getProdutos().get(index).getLotePorCodigo(codLote);
            aux.adicionaQuantidadeLote(-aux.getQuantidade());
            aux.adicionaQuantidadeLote(quantidade);
            Produto prodAux = new Produto(codigoProduto, estoque.getProdutos().get(index).getNome(), quantidade, estoque.getProdutos().get(index).getImposto());
            prodAux.cadastrarLote(aux);
        }

    }

    public void removeProduto(int codProduto, int quantidade, int codLote) throws PedidoException, ProdutoException {
        if (!estaNaLista(codProduto)) {
            throw new PedidoException("Exception ao tentar remover produto que sequer estah na lista de produtos!");
        }
        if (produtos.get(indexOfProduto(codProduto)).getQuantidade() < quantidade) {
            throw new PedidoException("Exception ao tentar remover uma quantidade da lista de produtos maior que a quantidade presente!");
        }
        Produto aux = produtos.get(indexOfProduto(codProduto));
        produtos.remove(indexOfProduto(codProduto));
        Lote loteAux = aux.getLotePorCodigo(codLote);
        aux.removeLote(codLote);
        loteAux.adicionaQuantidadeLote(-quantidade);
        aux.cadastrarLote(loteAux);
        produtos.add(aux);
    }

    public void removeProduto(int codProduto) throws PedidoException {
        if (!estaNaLista(codProduto)) {
            throw new PedidoException("Exception ao tentar remover produto que sequer estah na lista de produtos!");
        }
        produtos.remove(indexOfProduto(codProduto));

    }

    public void aplicaCupom(int codCupom) throws PedidoException {
        if (!estoque.cupomExiste(codCupom)) {
            throw new PedidoException("Tentativa de utilizar um cupom que nao existe!");
        }

        cupons.add(codCupom);
    }

    public double efetivaCompra() throws ProdutoException {
        double auxTotal = 0;
        List<Integer> itensComCupom = new ArrayList<>();
        Set<Produto> efetivados = new HashSet<>();
        List<Cupom> cuponsEstoque = estoque.getCupons();
        for (int i = 0; i < cupons.size(); i++) // baixa com cupom
        {
            int codigoProdutoDesseCupom = estoque.getCupomPorCodigo(cupons.get(i)).getCodigoProduto();
            int quantidadeParaCupom = estoque.getProduto(codigoProdutoDesseCupom).getQuantidade();
            double precoDesseCupom = estoque.getCupomPorCodigo(cupons.get(i)).getValor();
            auxTotal += precoDesseCupom * quantidadeParaCupom;
            for (int j = 0; j < produtos.get(indexOfProduto(codigoProdutoDesseCupom)).getNLotes(); j++) {
                int codigoDeLoteAux = produtos.get(indexOfProduto(codigoProdutoDesseCupom)).getLotePorIndice(j).getCodigoLote();
                int quantidadeLote = produtos.get(indexOfProduto(codigoProdutoDesseCupom)).getLotePorIndice(j).getQuantidade();
                estoque.removeQuantidadeDeLote(quantidadeLote, codigoProdutoDesseCupom, codigoDeLoteAux);
            }
            efetivados.add(produtos.get(indexOfProduto(codigoProdutoDesseCupom)));
        }
        for (int i = 0; i < produtos.size(); i++) // baixa com promocao
        {
            if (!efetivados.contains(produtos.get(i))) {
                efetivados.add(produtos.get(i));
                int codigoDoProduto = produtos.get(i).getCodigo();
                int quantidadeDoProduto = produtos.get(i).getQuantidade();
                for (int j = 0; j < estoque.getPromocoes().size(); j++) {
                    if (estoque.getPromocoes().get(j).getCodigoProduto() == produtos.get(i).getCodigo()) {
                        Produto paux = produtos.get(indexOfProduto(codigoDoProduto));
                        auxTotal += estoque.getPromocoes().get(j).getValor() * paux.getQuantidade();
                        for (int k = 0; k < paux.getNLotes(); k++) {
                            estoque.removeQuantidadeDeLote(paux.getLotePorIndice(k).getQuantidade(), codigoDoProduto, paux.getLotePorIndice(k).getCodigoLote());
                        }
                    }
                }

            }
        }
        for (int i = 0; i < produtos.size(); i++)//baixa normal
        {
            if (!efetivados.contains(produtos.get(i))) {
                efetivados.add(produtos.get(i));
                int codigoDoProduto = produtos.get(i).getCodigo();
                int quantidadeDoProduto = produtos.get(i).getQuantidade();
                auxTotal += produtos.get(i).getPreco();
                for (int j = 0; j < produtos.get(i).getNLotes(); j++) {
                    Lote lAux = produtos.get(i).getLotePorIndice(j);
                    estoque.removeQuantidadeDeLote(lAux.getQuantidade(), codigoDoProduto, lAux.getCodigoLote());
                }
            }
        }
        return auxTotal;
    }
}
