package com.mycompany.distribuidora.model.Usuarios;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.CPF;
import com.mycompany.distribuidora.model.Estoque.*;
import com.mycompany.distribuidora.model.Estoque.exceptions.CupomException;
import com.mycompany.distribuidora.model.Estoque.exceptions.DataException;
import com.mycompany.distribuidora.model.Estoque.exceptions.EstoqueException;
import com.mycompany.distribuidora.model.Estoque.exceptions.LoteException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PedidoException;
import com.mycompany.distribuidora.model.Estoque.exceptions.ProdutoException;
import com.mycompany.distribuidora.model.Estoque.exceptions.PromocaoException;
import com.mycompany.distribuidora.model.Pedido.Pedido;
import java.util.*;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class Cliente extends Pessoa {

    protected static int idCliente = 0;
    protected String senha;
    protected CPF cpf;
    protected Pedido pedidoAberto;
    protected boolean isPedidoAberto;
    protected List<Pedido> historicoPedidos;

    public Cliente(String nome, String telefoneInput, String login,
            String endereco, String emailInput,Estoque estoque)
            throws EmailException, TelefoneException {
        super(nome, telefoneInput,
                emailInput.substring(0, emailInput.indexOf("@")),
                endereco, emailInput,'c',estoque);
        idCliente = Cliente.idCliente++;
        pedidoAberto=null;
        isPedidoAberto=false;
        historicoPedidos=new ArrayList<>();

    }

    //implementar
    public void abrirPedido(int codigo,Estoque estoque)
    {
        pedidoAberto= new Pedido(codigo,estoque);
        isPedidoAberto=true;
    }
    public boolean inserirProduto(int codigoProduto,int codLote, int quantidade) throws ProdutoException, PedidoException{
        try
        {
            pedidoAberto.adicionaProduto(codigoProduto,codLote,quantidade);
        }
        catch(PedidoException e){
            throw new PedidoException("Tentativa de inserir um produto inexistente!");
        }
        return true;
    }


    public void retirarProduto(int codigoProduto, int codLote, int quantidade) throws PedidoException, ProdutoException {
        try
        {
            pedidoAberto.removeProduto(codigoProduto, quantidade, codLote);
        }
        catch(Exception e)
        {
            throw new PedidoException(e.getMessage());
        }

    }


    public List<Produto> consultarProdutos(Estoque estoque) {
        return estoque.getProdutos();
    }


    
    public void efetivaCompra(Estoque estoque) throws Exception{
        if(!privilegio.canFinalizarPedido())
            throw new PedidoException("Tentativa invalida de finalizar pedido");
        double aux=0;
        try
        { 
            aux = pedidoAberto.efetivaCompra();
            historicoPedidos.add(pedidoAberto);
            pedidoAberto=null;
            isPedidoAberto=false;
        }
        catch (Exception e)
        {
            throw new Exception("Impossivel efetivar compra");
        }
        estoque.addValor(aux);
    }

    //implementar
    /*public void pagamento() {
    }*/

    protected String getSenha() {
        return senha;
    }

    public String getCPF(CPF cpf) {
        return cpf.formataCPF(login);
    }

    protected void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getHistoricoPedidos(){return historicoPedidos;}
    public void abrirPedido(int codigoPedido)
    {
        if(!isPedidoAberto)
            pedidoAberto=new Pedido(codigoPedido, estoque);

    }
    public  int getCodigoPedido()
    {
        return pedidoAberto.getCodigoPedido();
    }
    public void removeProduto(int codProduto, int quantidade, int codLote) throws PedidoException, ProdutoException
    {
        if(privilegio.canRemoveProduto())
        {
            pedidoAberto.removeProduto(codProduto,quantidade,codLote);
        }
    }
    public  void adicionaProduto(int codigoProduto, int codLote, int quantidade) throws PedidoException, ProdutoException
    {
        if(!isPedidoAberto)
            throw new PedidoException("Nao ha pedido aberto!");
        Lote lAux=estoque.getLote(codigoProduto,codLote);
        try
        {
            pedidoAberto.adicionaProduto(codigoProduto, codLote, quantidade);
        }catch(Exception e)
        {  
            throw new PedidoException(e.getMessage());
        }
    }
    public  void aplicaCupom(int codCupom) throws PedidoException
    {
        if(!isPedidoAberto)
            throw new PedidoException("Nao ha pedido aberto!");
        try
        {
            pedidoAberto.aplicaCupom(codCupom);
        }
        catch(Exception e)
        {
            throw new PedidoException(e.getMessage());
        }
    }
    public boolean estaNaLista(int codProduto) throws PedidoException
    {
        if(!isPedidoAberto)
            throw new PedidoException("Nao ha pedido aberto!");
        return pedidoAberto.estaNaLista(codProduto);
    }
    public double getVendas() throws EstoqueException
    {
        if(!privilegio.canGetVendas())
            throw new EstoqueException("tentativa de acessar o total de vendas a partir de um usuario nao qualificado");
        return 0;
    }
    public  void adicionarEstoque(Lote aSerAdicionado, int codproduto) throws ProdutoException,EstoqueException
    {
        if(!privilegio.canAdicionarEstoque())
            throw new EstoqueException("tentativa de adicionar ao total do estoque a partir de um usuario nao qualificado");
    }
    public void criarPromocao(int codProduto, double novoValor) throws PromocaoException
    {
            if(!privilegio.CanCriarPromocao())
                throw new PromocaoException("Tentativa de criar uma promocao atarves de um user não qualificado");
    }
    public void criarCupom(int codProduto, double novoValor, int codigoCupom) throws PromocaoException, CupomException
    {
        if(!privilegio.canCriarCupom())
            throw new CupomException("Tentativa de criar um cupom atarves de um user não qualificado");
    }
    public List<Produto> getNDiasProximoValidade(int nDias, Data data) throws DataException, LoteException, ProdutoException
    {
        if(!privilegio.canGetNDiasProximoValidade())
            throw new ProdutoException("Tenativa de acessar dados logísticos apartir de user nao qualificado!");
        return null;
    }
    public List<Produto> getProdutos() throws PedidoException
    {
        if(!isPedidoAberto)
        {  
            throw new PedidoException("Nao ha pedido aberto!");
        }
        if(privilegio.canGetProdutos())
            return pedidoAberto.getProdutos();

        return null;
    }
    public Lote getLote(int codProduto, int codLote) throws ProdutoException,EstoqueException
    {
        if(!privilegio.canGetLote())
            throw new EstoqueException("Tentativa de acessar informacoes nao autorizadas ao usuario!");
        Lote aux=null;
        try
        {
            aux=estoque.getLote(codProduto, codLote);
        }
        catch(ProdutoException e)
        {
            throw new ProdutoException(e.getMessage());
        }
        return aux;
        
    }
    public Lote getLotePedido(int codProduto, int codLote) throws PedidoException
    {
        try {
            return getProdutos().get(pedidoAberto.indexOfProduto(codProduto)).getLotePorCodigo(codLote);
        } catch (ProdutoException e) {
            throw new PedidoException(e.getMessage());     
        }
        
    }
    public void removeQuantidadeDeLote(int quantidade, int codProduto, int codLote) throws ProdutoException,EstoqueException
    {
        if(!privilegio.canRemoveQuantidadeDeLote())
            throw new EstoqueException("Tentativa de alterar informacoes do estoque sem permicao!");
        try {
                estoque.removeQuantidadeDeLote(quantidade, codProduto, codLote);
        } catch (Exception e) {
            throw new EstoqueException(e.getMessage());
        }
    }
    public   Produto getProduto(int codProduto) throws EstoqueException,PedidoException
    {
        if(!privilegio.canGetProduto())
            throw new PedidoException("Tentativa de acessar uma informacao do estoque sem autorizacao");
        try {
            return estoque.getProduto(codProduto);
        } catch (Exception e) {
            throw new EstoqueException(e.getMessage());
        }
    }
    public Produto getPrododutoPedido(int codProduto) throws PedidoException
    {
        if(!privilegio.canGetProdutoPedido())
            throw new PedidoException("Tentativa de acessar informacoes de um pedido sem permissao");
        if(!isPedidoAberto)
            throw new PedidoException("Nao ha pedido aberto!");
        return pedidoAberto.getProdutos().get(pedidoAberto.indexOfProduto(codProduto));
    }
}