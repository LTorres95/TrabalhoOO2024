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
        
        // Painéis de rolagem para as listas
        JScrollPane produtoScrollPane = new JScrollPane(produtoList);
        JScrollPane vendedorScrollPane = new JScrollPane(vendedorList);

        JPanel painelCentral = new JPanel(new GridLayout(1, 2));
        painelCentral.add(produtoScrollPane);
        painelCentral.add(vendedorScrollPane);

        // Botões para gerenciamento de produtos
        
        JButton btnAddProduto = new JButton("Adicionar Produto");
        JButton btnRemoverProduto = new JButton("Remover Produto");

        // Ação para adicionar produto ao estoque
        btnAddProduto.addActionListener((ActionEvent e) -> {
            String nomeProduto = JOptionPane.showInputDialog(frame, "Nome do Produto:");
            if (nomeProduto != null && !nomeProduto.isEmpty()) {
                Produto novoProduto = new Produto(nomeProduto);
                estoque.add(novoProduto);
                produtoListModel.addElement(novoProduto);
            } else {
                JOptionPane.showMessageDialog(frame, "Nome do produto inválido.");
            }
        });

        // Ação para remover produto do estoque
        btnRemoverProduto.addActionListener((ActionEvent e) -> {
            Produto produtoSelecionado = produtoList.getSelectedValue();
            if (produtoSelecionado != null) {
                estoque.remove(produtoSelecionado);
                produtoListModel.removeElement(produtoSelecionado);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para remover.");
            }
        });

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
