package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.PrivilegioException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.Email;
import com.mycompany.distribuidora.model.Telefone;
import com.mycompany.distribuidora.model.Pedido.Pedido;
import com.mycompany.distribuidora.model.Privilegios.Privilegio;
import com.mycompany.distribuidora.model.Estoque.*;
import com.mycompany.distribuidora.model.Estoque.exceptions.CupomException;
import com.mycompany.distribuidora.model.Estoque.exceptions.DataException;
import com.mycompany.distribuidora.model.Estoque.exceptions.EstoqueException;
import com.mycompany.distribuidora.model.Estoque.exceptions.LoteException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PedidoException;
import com.mycompany.distribuidora.model.Estoque.exceptions.ProdutoException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PromocaoException;

import java.util.*;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public abstract class Pessoa {

    protected String nome;
    protected Telefone telefone;
    protected Email email;
    protected String login;
    protected String endereco;
    protected Privilegio privilegio;
    protected Estoque estoque;


    public Pessoa(String nome, String telefoneInput, String login, String endereco,
            String emailInput,char privilegio,Estoque estoque) throws EmailException, TelefoneException {
        this.nome = nome;
        try {
            this.telefone = new Telefone(telefoneInput);
        } catch (TelefoneException e) {
            throw new TelefoneException();
        }
        this.login = this.email.getLogin();
        this.endereco = endereco;
        try {
            this.email = new Email(emailInput);
        } catch (EmailException e) {
            throw new EmailException();
        }
        this.privilegio=new Privilegio(privilegio);
        this.estoque=estoque;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone.toString();
    }

    public Email getEmail(){return email;}

    public abstract  Produto getProduto(int codProduto) throws EstoqueException,PedidoException;
    public abstract  void removeQuantidadeDeLote(int quantidade, int codProduto, int codLote) throws ProdutoException,EstoqueException;
    public abstract  Lote getLote(int codProduto, int codLote) throws ProdutoException,EstoqueException;
    public abstract  List<Produto> getProdutos() throws PedidoException;
    public  List<Cupom> getCupons()throws EstoqueException
    {
        if(privilegio.canGetCupons())
            return estoque.getCupons();
        throw new EstoqueException("Tentativa de acessar informacoes logísticas");
    }
    public  List<Promocao> getPromocoes()
    {
        return estoque.getPromocoes();
    }
    public int getQuantidade(int codProduto) throws ProdutoException
    {
        return estoque.getQuantidade(codProduto);
    }
    public   int getQuantidadeLote(int codProduto, int codLote) throws LoteException, ProdutoException,PedidoException
    {
        return estoque.getQuantidadeLote(codProduto, codLote);
    }
    public abstract  List<Produto> getNDiasProximoValidade(int nDias, Data data) throws DataException, LoteException, ProdutoException;
    public abstract  void criarCupom(int codProduto, double novoValor, int codigoCupom) throws PromocaoException, CupomException;
    public abstract  void criarPromocao(int codProduto, double novoValor) throws PromocaoException ;
    
    public abstract  void adicionarEstoque(Lote aSerAdicionado, int codproduto) throws ProdutoException,EstoqueException;
    public abstract  double getVendas() throws EstoqueException;

    public abstract  boolean estaNaLista(int codProduto) throws PedidoException;
    public abstract  void adicionaProduto(int codigoProduto, int codLote, int quantidade) throws PedidoException, ProdutoException;
    public abstract  void removeProduto(int codProduto, int quantidade, int codLote) throws PedidoException, ProdutoException;
    public abstract  void aplicaCupom(int codCupom) throws PedidoException;
    public abstract  void efetivaCompra(Estoque estoque) throws Exception;
    public abstract  int getCodigoPedido();
    
}