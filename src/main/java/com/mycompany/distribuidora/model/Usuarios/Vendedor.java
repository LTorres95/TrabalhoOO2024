package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.CPF;
import com.mycompany.distribuidora.model.Estoque.Data;
import com.mycompany.distribuidora.model.Estoque.Estoque;
import com.mycompany.distribuidora.model.Estoque.Lote;
import com.mycompany.distribuidora.model.Estoque.Produto;
import com.mycompany.distribuidora.model.Estoque.exceptions.CupomException;
import com.mycompany.distribuidora.model.Estoque.exceptions.DataException;
import com.mycompany.distribuidora.model.Estoque.exceptions.EstoqueException;
import com.mycompany.distribuidora.model.Estoque.exceptions.LoteException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PedidoException;
import com.mycompany.distribuidora.model.Estoque.exceptions.ProdutoException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PromocaoException;
import java.util.List;

/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class Vendedor extends Pessoa {

    protected static int idVendedor = 0;
    protected String senha;
    protected CPF cpf;
    
    //protected String nome;
    public Vendedor(String nome, String telefoneInput, String login, String endereco, 
            String emailInput, char privilegio, Estoque estoque) throws EmailException, TelefoneException {
        super(nome, telefoneInput, login, endereco, emailInput, privilegio, estoque);
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
    }

    public Vendedor(String nome, String telefoneInput, String login,
            String endereco, String emailInput, Estoque estoque)
        throws EmailException, TelefoneException {

        super(nome, telefoneInput,
                emailInput.substring(0, emailInput.indexOf("@")),
                endereco, emailInput,'v', estoque);
        idVendedor = Vendedor.idVendedor++;
    }
    //public void Venda(){};

    @Override
    public Produto getProduto(int codProduto) throws EstoqueException, PedidoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeQuantidadeDeLote(int quantidade, int codProduto, int codLote) throws ProdutoException, EstoqueException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lote getLote(int codProduto, int codLote) throws ProdutoException, EstoqueException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Produto> getProdutos() throws PedidoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Produto> getNDiasProximoValidade(int nDias, Data data) throws DataException, LoteException, ProdutoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void criarCupom(int codProduto, double novoValor, int codigoCupom) throws PromocaoException, CupomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void criarPromocao(int codProduto, double novoValor) throws PromocaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void adicionarEstoque(Lote aSerAdicionado, int codproduto) throws ProdutoException, EstoqueException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getVendas() throws EstoqueException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean estaNaLista(int codProduto) throws PedidoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void adicionaProduto(int codigoProduto, int codLote, int quantidade) throws PedidoException, ProdutoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeProduto(int codProduto, int quantidade, int codLote) throws PedidoException, ProdutoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void aplicaCupom(int codCupom) throws PedidoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void efetivaCompra(Estoque estoque) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getCodigoPedido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
