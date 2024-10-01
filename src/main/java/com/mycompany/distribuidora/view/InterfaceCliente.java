package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class InterfaceCliente {

    private JFrame frame;
    private JList<Produto> produtoList;
    private JList<Produto> carrinhoList;
    private DefaultListModel<Produto> produtoListModel;
    private DefaultListModel<Produto> carrinhoListModel;
    private List<Produto> estoque;
    private List<Produto> carrinho;

    public InterfaceCliente(List<Produto> estoque) {
        this.estoque = estoque;
        this.carrinho = new ArrayList<>();
        inicializa();
    }

    private void inicializa() {
        frame = new JFrame("Carrinho de Compras");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Lista de produtos disponíveis
        produtoListModel = new DefaultListModel<>();
        produtoList = new JList<>(produtoListModel);
        produtoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carregarProdutos();

        // Lista de produtos no carrinho
        carrinhoListModel = new DefaultListModel<>();
        carrinhoList = new JList<>(carrinhoListModel);
        carrinhoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Painéis de rolagem para as listas
        JScrollPane produtoScrollPane = new JScrollPane(produtoList);
        JScrollPane carrinhoScrollPane = new JScrollPane(carrinhoList);

        JPanel controlPanel = new JPanel(new GridLayout(1, 2));
        controlPanel.add(produtoScrollPane);
        controlPanel.add(carrinhoScrollPane);

        // Botões para adicionar, remover e finalizar o pedido
        JPanel botoesPainel = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        JButton btnRemover = new JButton("Remover do Carrinho");
        JButton btnFinalizar = new JButton("Finalizar Pedido");
        JButton btnEncerrar = new JButton("Encerrar Compra");

        // Ação para adicionar produto ao carrinho
        btnAdicionar.addActionListener((ActionEvent e) -> {
            Produto produtoSelecionado = produtoList.getSelectedValue();
            if (produtoSelecionado != null) {
                carrinho.add(produtoSelecionado);
                carrinhoListModel.addElement(produtoSelecionado);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para adicionar.");
            }
        });

        // Ação para remover produto do carrinho
        btnRemover.addActionListener((ActionEvent e) -> {
            Produto produtoSelecionado = carrinhoList.getSelectedValue();
            if (produtoSelecionado != null) {
                carrinho.remove(produtoSelecionado);
                carrinhoListModel.removeElement(produtoSelecionado);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para remover.");
            }
        });

        // Ação para finalizar o pedido
        btnFinalizar.addActionListener((ActionEvent e) -> {
            if (!carrinho.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Pedido finalizado com sucesso! Quantidade de itens: " + carrinho.size());
                carrinho.clear();
                carrinhoListModel.clear(); // Limpa o carrinho após finalização
            } else {
                JOptionPane.showMessageDialog(frame, "Seu carrinho está vazio.");
            }
        });

        // Ação para encerrar a compra sem finalizar
        btnEncerrar.addActionListener((ActionEvent e) -> {
            int resposta = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja encerrar a compra?", "Encerrar Compra", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                frame.dispose(); // Fecha a janela
            }
        });

        botoesPainel.add(btnAdicionar);
        botoesPainel.add(btnRemover);
        botoesPainel.add(btnFinalizar);
        botoesPainel.add(btnEncerrar);

        // Adiciona os painéis à janela principal
        frame.add(controlPanel, BorderLayout.CENTER);
        frame.add(botoesPainel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void carregarProdutos() {
        for (Produto produto : estoque) {
            produtoListModel.addElement(produto);
        }
    }
}
