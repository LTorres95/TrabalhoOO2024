package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;
import com.mycompany.distribuidora.model.Usuarios.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

        // Botões para gerenciamento de produtos e vendedores
        JPanel painelBotoes = new JPanel();
        JButton btnAddProduto = new JButton("Adicionar Produto");
        JButton btnRemoverProduto = new JButton("Remover Produto");
        JButton btnCadastrarVendedor = new JButton("Cadastrar Vendedor");
        JButton btnRemoverVendedor = new JButton("Remover Vendedor");
        JButton btnComprarEstoque = new JButton("Comprar Estoque"); // Novo botão

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

        // Ação para cadastrar novo vendedor
        btnCadastrarVendedor.addActionListener((ActionEvent e) -> {
            String nomeVendedor = JOptionPane.showInputDialog(frame, "Nome do Vendedor:");
            if (nomeVendedor != null && !nomeVendedor.isEmpty()) {
                Vendedor novoVendedor = new Vendedor(nomeVendedor) {};
                vendedores.add(novoVendedor);
                vendedorListModel.addElement(novoVendedor);
            } else {
                JOptionPane.showMessageDialog(frame, "Nome do vendedor inválido.");
            }
        });

        // Ação para remover vendedor
        btnRemoverVendedor.addActionListener((ActionEvent e) -> {
            Vendedor vendedorSelecionado = vendedorList.getSelectedValue();
            if (vendedorSelecionado != null) {
                vendedores.remove(vendedorSelecionado);
                vendedorListModel.removeElement(vendedorSelecionado);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um vendedor para remover.");
            }
        });

        // Ação para comprar estoque
        btnComprarEstoque.addActionListener((ActionEvent e) -> {
            Produto produtoSelecionado = produtoList.getSelectedValue();
            if (produtoSelecionado != null) {
                String quantidadeStr = JOptionPane.showInputDialog(frame, "Quantidade a comprar:");
                try {
                    int quantidade = Integer.parseInt(quantidadeStr);
                    if (quantidade > 0) {
                        produtoSelecionado.adicionarQuantidade(quantidade);
                        produtoListModel.setElementAt(produtoSelecionado, produtoList.getSelectedIndex());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Quantidade inválida.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número válido.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para comprar.");
            }
        });

        // Adicionar botões ao painel
        painelBotoes.add(btnAddProduto);
        painelBotoes.add(btnRemoverProduto);
        painelBotoes.add(btnCadastrarVendedor);
        painelBotoes.add(btnRemoverVendedor);
        painelBotoes.add(btnComprarEstoque); // Adiciona o botão de compra de estoque

        // Adiciona painéis ao frame
        frame.add(painelCentral, BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);

        frame.setVisible(true);
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