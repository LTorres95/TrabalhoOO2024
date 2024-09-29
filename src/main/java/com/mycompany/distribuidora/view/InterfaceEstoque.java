/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;
import com.mycompany.distribuidora.model.Estoque.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author joao
 */
public class InterfaceEstoque {
    private JFrame frame;
    private JList<Produto> produtoList;
    private DefaultListModel<Produto> listModel;
    private Estoque estoque;
    
    public InterfaceEstoque(Estoque estoque){
        this.estoque = estoque;
        inicializa();
    }

    private void inicializa() {
        frame = new JFrame("Estoque de produtos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        listModel = new DefaultListModel<>();
        produtoList = new JList<>(listModel);
        produtoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        carregarProdutos();
        
        JScrollPane scrollPane = new JScrollPane(produtoList);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel botoesPainel = new JPanel();
        JButton btnDetalhes = new JButton("Ver detalhes");
        btnDetalhes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produtoSelecionado = produtoList.getSelectedValue();
                if (produtoSelecionado != null)
                    mostrarDetalhes(produtoSelecionado);
                else
                    JOptionPane.showMessageDialog(frame, "Selecione um produto.");
            }
        });
        botoesPainel.add(btnDetalhes);
        
        frame.add(botoesPainel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void carregarProdutos() {
        listModel.clear();
        List<Produto> produtos = estoque.getProdutos();
        for(Produto produto : produtos)
            listModel.addElement(produto);
    }
    
    private void mostrarDetalhes(Produto produto){
        String detalhes = String.format("Nome: %s\nCódigo: %d\nPreço: %.2f\nQuantidade: %d",
                produto.getNome(), produto.getCodigo(), produto.getPreco(), produto.getQuantidade());
        JOptionPane.showMessageDialog(frame, detalhes, "Detalhes do Produto", JOptionPane.INFORMATION_MESSAGE);

    }
    
}
