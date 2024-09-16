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
    
    private JTextField tfLogin;
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
//        tela.setResizable(false);
        
        desenhaTelaInicial();
        
        tela.pack();
    }
    
    private void desenhaTelaInicial(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Tela inicial"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        painel.setLayout(new BorderLayout());
        
        String[] tipos = {"CPF", "CNPJ"};
        JComboBox tipoLogin = new JComboBox(tipos);
        tipoLogin.setSelectedIndex(1);
        
        JPanel login = new JPanel();
        JPanel painelLabel = new JPanel();
        
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Login"));
        
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfLogin = new JTextField(20);
        
        painelField.add(tfLogin);
//        tfLogin --> verificar se é cpf ou cnpj usando as classes de tratamento a serem criadas
        
//        consertar a formatação disso pra aparecer os 2:
        JPanel componentes = new JPanel();
        componentes.add(tipoLogin);
        componentes.add(painelField);
        
        painel.add(componentes, BorderLayout.CENTER);
//        painel.add(tipoLogin);
//        painel.add(painelField);
        
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaCadastro();
            }
            
        });

        JPanel botoes = new JPanel();
        botoes.add(btnEntrar);
        botoes.add(btnCadastrar);
        
        painel.add(botoes, BorderLayout.SOUTH);
        
        tela.getContentPane().add(painel, BorderLayout.CENTER);
    }
    
    private void desenhaCadastro(){
        JFrame painel = new JFrame("Cadastro");
//        painel.setBorder(BorderFactory.createTitledBorder("Cadastro"));
        painel.setSize(WIDTH, HEIGHT);
//        painel.setPreferredSize(new Dimension(500, 500));
        painel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painel.setVisible(true);
        
        JPanel cadastro = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
//        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Nome"));
        //FAZER CASO PARA EMPRESA E PESSOA FISICA:
        //SE PESSOA FISICA ----> painelLabel.add(new JLabel("CPF"));
        //SE PESSOA JURIDICA ---> painelLabel.add(new JLabel("CNPJ"));
        painelLabel.add(new JLabel("Telefone"));
        painelLabel.add(new JLabel("Email"));
        painelLabel.add(new JLabel("Senha"));
        
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfNome = new JTextField(20);
        tfEmail = new JTextField(20);
        //** PESSOA FISICA || PESSOA JURIDICA **
        tfTelefone = new JTextField(20);
        tfSenha = new JTextField(20);
        
        painelField.add(tfNome);
        painelField.add(tfEmail);
        painelField.add(tfTelefone);
        painelField.add(tfSenha);
        
        cadastro.add(painelLabel);
        cadastro.add(painelField);
        
        painel.setLayout(new BorderLayout());
        painel.add(cadastro, BorderLayout.CENTER);
        
        JButton btnFimCadastro = new JButton("Finazizar Cadastro");
        JButton btnVoltaTela = new JButton("Voltar para tela inicial");
        btnVoltaTela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaTelaInicial();
                painel.dispose();
            }
        });
//        CRIAR PERSISTENCIA PARA NOVO CADASTRO
//        btnFimCadastro.addActionListener(new AdicionarCadastro(this));
        JPanel botoes = new JPanel();
        botoes.add(btnFimCadastro);
        botoes.add(btnVoltaTela);
        
        painel.add(botoes, BorderLayout.SOUTH);
        
        tela.getContentPane().add(painel, BorderLayout.CENTER);
    }
}
