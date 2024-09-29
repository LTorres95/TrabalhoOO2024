package com.mycompany.distribuidora.model.Estoque;

import com.mycompany.distribuidora.model.Estoque.exceptions.LoteException;
import com.mycompany.distribuidora.model.Estoque.exceptions.ProdutoException;
import java.util.*;

public class Produto {

    private String nome;
    private int codigo;
    private double preco;
    private List<Lote> lotes;
    private double imposto;

    private boolean valid(int number, int lowestLimit, int greatest) {
        if (number > lowestLimit & number <= greatest) {
            return true;
        }
        return false;
    }

    private int getIndexLoteCodigo(int cod) {
        int aux = 0;
        for (int i = 0; i < lotes.size(); i++) {
            if (lotes.get(i).getCodigoLote() == codigo) {
                aux = i;
            }
        }
        return aux;
    }

    public int getCodigo() {
        return codigo;
    }

    public Produto(int codigo, String nome, double preco, double imposto) throws ProdutoException {
        if (preco < 0) {
            throw new ProdutoException("O preço do produto não pode ser negativo.");
        }
        if (imposto < 0 || imposto > 1) {
            throw new ProdutoException("O imposto deve estar entre 0 e 1.");
        }
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.imposto = imposto;
        lotes = new ArrayList<>();
    }

    public int getQuantidade() {
        int tot = 0;
        for (int i = 0; i < lotes.size(); i++) {
            tot += lotes.get(i).getQuantidade();
        }
        return tot;
    }

    public boolean loteIndiceExiste(int index) throws ProdutoException {
        if (index < 0 & index >= lotes.size()) {
            throw new ProdutoException("O índice do lote está fora dos limites.");
        }
        return lotes.isEmpty();
    }

    public boolean loteCodigoExiste(int codigo) {
        for (int i = 0; i < lotes.size(); i++) {
            if (lotes.get(i).getCodigoLote() == codigo) {
                return true;
            }
        }
        return false;
    }

    public Lote getLotePorIndice(int i) throws ProdutoException // uso fora da classe : se loteIndiceExiste(i) -> getLotePorIndice(i)
    {
        if (!loteIndiceExiste(i)) {
            throw new ProdutoException("Lote não encontrado no índice especificado.");
        }
        return lotes.get(i);
    }

    public Lote getLotePorCodigo(int codigo) throws ProdutoException // usofora da classe : se loteExiste(codigo) -> getLotePorCodigo(codigo)
    {
        for (int i = 0; i < lotes.size(); i++) {
            if (lotes.get(i).getCodigoLote() == codigo) {
                return lotes.get(i);
            }
        }
        throw new ProdutoException("Lote não encontrado para o código fornecido.");
    }

    public int getNLotes() {
        return lotes.size();
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public Double getImposto() {
        return imposto;
    }

    public void retirarQuantidade(int codigoLote, int quantidade) throws ProdutoException // se um lote foi passado por parametro , existe produto que foi escaneado que é desse lote.Assim não há necessidade de verificar se o lote existe;
    {
        Lote aux = getLotePorCodigo(codigoLote);
        lotes.remove(getIndexLoteCodigo(codigoLote));
        try {
            aux.removeQuantidade(quantidade);
        } catch (LoteException e) {
            throw new ProdutoException("Erro ao retirar quantidade do lote " + e.getMessage());
        }
        lotes.add(aux);
    }

    public void cadastrarLote(Data dataAquisicao, int validade, int quantidade, int codigoLote) throws ProdutoException {
        try {
            Lote aux = new Lote(dataAquisicao, validade, quantidade, codigoLote);
            lotes.add(aux);
        } catch (LoteException e) {
            throw new ProdutoException("Erro ao cadastrar o lote " + e.getMessage());
        }

    }

    public void cadastrarLote(Lote lote) {
        lotes.add(lote);
    }

    public void alterarPreco(Double valor) throws ProdutoException {
        if (valor < 0) {
            throw new ProdutoException("O valor do preço não pode ser negativo.");
        }
        preco = valor;

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
    public list<Lote> getLotes(){return lotes;}
}
