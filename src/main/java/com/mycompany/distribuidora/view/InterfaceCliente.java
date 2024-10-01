package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.model.Estoque.Produto;

import javax.swing.*;
import java.util.List;


public class InterfaceCliente {
    
    private JFrame frame;
    private JList<Produto> produtoList;
    private JList<Produto> carrinhoList;
    private DefaultListModel<Produto> produtoListModel;
    private DefaultListModel<Produto> carrinhoListModel;
    private List<Produto> estoque;
    private List<Produto> carrinho;

}