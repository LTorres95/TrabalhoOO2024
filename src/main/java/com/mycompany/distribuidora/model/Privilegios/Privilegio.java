package com.mycompany.distribuidora.model.Privilegios;

import com.mycompany.distribuidora.exception.PrivilegioException;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class Privilegio {
    protected int valorPrivilegio;

    public Privilegio(char privilegio)
    {
        switch(privilegio)
        {
            case 'c':   //cliente
                valorPrivilegio = 0;
            break;

            case 'v':   //vendedor
                valorPrivilegio=1;
            break;

            case 'g':   //gerente
                valorPrivilegio=2;
            break;
        }

    }

    public int getPrivilegio(){return valorPrivilegio;}

    public boolean canAddPedido()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }

    public boolean canRemoveProduto()
    {
        if(valorPrivilegio==1)
            return false;
        return true;
    }
    public boolean canAplicaCupom()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }
    public boolean canEfetivaCompra()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }
    public boolean canCriarCupom()
    {
        if(valorPrivilegio==2)
            return true;
        return false;
    }
    public boolean CanCriarPromocao()
    {
        if(valorPrivilegio==2)
            return true;
        return false;
    }
    public boolean canAdicionarEstoque()
    {
        if(valorPrivilegio==0)
            return false;
        return true;
    }
    public boolean canAdicionarProduto()
    {
        if(valorPrivilegio==0)
            return false;
        return true;
    }
    public boolean canGetNDiasProximoValidade()
    {
        if(valorPrivilegio==0)
            return false;
        return true;
    }
    public boolean canGetCupons()
    {
        if(valorPrivilegio==2)
            return true;
        return false;
    }
    public boolean canFinalizarPedido()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }
    public boolean canRemoveQuantidadeDeLote()
    {
        if(valorPrivilegio==2)
            return true;
        return false;
    }
    public boolean canGetVendas()
    {
        if(valorPrivilegio==1)
            return false;
        return true;

    }
    public boolean canGetProduto(){return true;}
    public boolean canGetLote(){return true;}
    public boolean canGetProdutos(){return true;}
    public boolean canGetPromocoes(){return true;}
    public boolean canGetQuantidade(){return true;}
    public boolean canGetQuantidadeLote(){return true;}
    public boolean canEstaNaLista(){
        if(valorPrivilegio==0 )
            return true;
        return false;
    }
    public boolean canAdicionaProduto()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }
    public boolean canGetCodigoPedido()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }
    public boolean canGetProdutoPedido()
    {
        if(valorPrivilegio==0)
            return true;
        return false;
    }
}

