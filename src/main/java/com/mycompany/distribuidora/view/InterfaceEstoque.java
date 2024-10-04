package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;
import com.mycompany.distribuidora.model.Estoque.Estoque;
import com.mycompany.distribuidora.model.Estoque.Lote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/*
Pedro Souza Pinheiro da Silva Araujo - 202165560C
Lucas Torres Martins - 202135023
Gustavo do Bem Ferreira - 202065036AC
João Nilson Quintão Barros - 202276007
*/
public class InterfaceEstoque {

    private JFrame frame;
    private JList<Produto> produtoList;
    private JList<Lote> loteList;
    private DefaultListModel<Lote> loteListModel;
    private Estoque estoque;

    public InterfaceEstoque(Estoque estoque) {
        this.estoque = estoque;
        inicializa();
    }

    private void inicializa() {
        frame = new JFrame("Estoque de produtos");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DefaultListModel<Produto> produtoListModel = new DefaultListModel<>();
        produtoList = new JList<>(produtoListModel);
        produtoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        produtoList.addListSelectionListener(e -> carregarLotes());

        loteListModel = new DefaultListModel<>();
        loteList = new JList<>(loteListModel);
        loteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        carregarProdutos(produtoListModel);

        JScrollPane produtoScrollPane = new JScrollPane(produtoList);
        JScrollPane loteScrollPane = new JScrollPane(loteList);
//        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(1, 2));
        controlPanel.add(produtoScrollPane);
        controlPanel.add(loteScrollPane);

        JPanel botoesPainel = new JPanel();
        JButton btnDetalhes = new JButton("Ver detalhes");
        btnDetalhes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lote loteSelecionado = loteList.getSelectedValue();
                if (loteSelecionado != null) {
                    mostrarDetalhes(loteSelecionado);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um lote.");
                }
            }
        });
        botoesPainel.add(btnDetalhes);

        frame.add(controlPanel, BorderLayout.CENTER);
        frame.add(botoesPainel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void carregarProdutos(DefaultListModel<Produto> produtoListModel) {
        loteListModel.clear();
        for (Produto produto : estoque.getProdutos()) {
            produtoListModel.addElement(produto);
        }
    }

    private void carregarLotes() {
        loteListModel.clear();
        Produto produtoSelecionado = produtoList.getSelectedValue();
        if (produtoSelecionado != null) {
            List<Lote> lotes = (List<Lote>) produtoSelecionado.getLotes();
            for (Lote lote : lotes) {
                loteListModel.addElement(lote);
            }
        }
    }

    private void mostrarDetalhes(Lote lote) {
        String detalhes = String.format("Código do Lote: %d\nQuantidade: %d\nValidade: %s",
                lote.getCodigoLote(), lote.getQuantidade(), lote.getValidade());
        JOptionPane.showMessageDialog(frame, detalhes, "Detalhes do Produto", JOptionPane.INFORMATION_MESSAGE);

    }

}