/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.view;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author si
 */
public class Interface {

    private JFrame tela;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    
    private JTextField tfNome;
    private JTextField tfCpf;
    private JTextField tfCnpj;
    private JTextField tfEmail;
    private JTextField tfTelefone;
    private JTextField tfSenha;
    
    public void desenha() {

        tela = new JFrame("Distribuidora");
        tela.setVisible(true);
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);
        tela.setLayout(new BorderLayout());
        
        desenhaTelaInicial();
        
        tela.pack();
    }
    
    private void desenhaTelaInicial(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Tela inicial"));
        
        JButton btnLogin = new JButton("Login");
        JButton btnCadastro = new JButton("Cadastro");

        JPanel botoes = new JPanel();
        botoes.add(btnLogin);
        botoes.add(btnCadastro);
        
        painel.add(botoes, BorderLayout.SOUTH);
        tela.getContentPane().add(painel, BorderLayout.CENTER);
    }
}
