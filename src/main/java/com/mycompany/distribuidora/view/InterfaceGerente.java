package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;
import com.mycompany.distribuidora.model.Usuarios.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
        inicializa();
    }

    private void inicializa() {
        frame = new JFrame("Interface do Gerente");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painéis para produtos e vendedores
        produtoListModel = new DefaultListModel<>();
        JList<Produto> produtoList = new JList<>(produtoListModel);
        produtoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        vendedorListModel = new DefaultListModel<>();
        JList<Vendedor> vendedorList = new JList<>(vendedorListModel);
        vendedorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        carregarProdutos();
        carregarVendedores();
    }
    
    private void carregarProdutos() {
        for (Produto produto : estoque) {
            produtoListModel.addElement(produto);
        }
    }

    private void carregarVendedores() {
        for (Vendedor vendedor : vendedores) {
            vendedorListModel.addElement(vendedor);
        }
    }

}
