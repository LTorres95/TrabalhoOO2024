package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;
import com.mycompany.distribuidora.model.Usuarios.Vendedor;


import javax.swing.*;
import java.util.List;

public class InterfaceGerente {
    
    private JFrame frame;
    private DefaultListModel<Produto> produtoListModel;
    private DefaultListModel<Vendedor> vendedorListModel;
    private List<Produto> estoque;
    private List<Vendedor> vendedores;

    public InterfaceGerente(List<Produto> estoque, List<Vendedor> vendedores) {
        this.estoque = estoque;
        this.vendedores = vendedores;
    }

}
