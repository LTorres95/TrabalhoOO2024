/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.persistencia.PessoaPersistencia;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author ltmar
 */
public class Login {
    
    private JFrame telaLogin;
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private JTextField tfLogin;
    private JTextField tfNome;
    private JTextField tfCPF;
    private JTextField tfEmail;
    private JTextField tfEndereco;
    private JTextField tfTelefone;
    private JTextField tfSenha;
    private JTextField tfString;

    private PessoaPersistencia pessoaPersistencia;
    

    
    public void inicializa() {
        telaLogin = new JFrame("Estoque de produtos");
        telaLogin.setSize(WIDTH, HEIGHT);
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setLayout(new BorderLayout());
        
        
        desenhaCadastro();
        desenhaLogin();
        
        telaLogin.setVisible(true);

        
    }
    
    private void desenhaCadastro() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário de Cadastro"));

        // Painéis para rótulos e campos de texto
        JPanel cadastroLabel = new JPanel();
        JPanel painelField = new JPanel();

        // Layout dos rótulos
        cadastroLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        cadastroLabel.add(new JLabel("Nome"));
        cadastroLabel.add(new JLabel("Email"));
        cadastroLabel.add(new JLabel("CPF"));
        cadastroLabel.add(new JLabel("Telefone"));
        cadastroLabel.add(new JLabel("Senha"));
        cadastroLabel.add(new JLabel("String input(GERENTE)"));

        // Criação dos campos de entrada
        tfNome = new JTextField(20);
        tfEmail = new JTextField(20);
        tfCPF = new JTextField(20);
        tfEndereco = new JTextField(20);
        tfSenha = new JTextField(20);
        tfString = new JTextField(20);

        // Layout dos campos de texto
        painelField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        painelField.add(tfNome);
        painelField.add(tfEmail);
        painelField.add(tfCPF);
        painelField.add(tfEndereco);
        painelField.add(tfSenha);
        painelField.add(tfString);

        // Configuração do painel principal
        painel.setLayout(new BorderLayout());
        painel.add(cadastroLabel, BorderLayout.WEST); // Adiciona rótulos à esquerda
        painel.add(painelField, BorderLayout.EAST); // Adiciona campos de texto ao centro

        
        // Cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        // btnCadastrar.addActionListener(new CadastraPessoa(this)); // Lógica de cadastro a ser implementada

        JPanel botoes = new JPanel();
        botoes.add(btnCadastrar);

        painel.add(botoes, BorderLayout.SOUTH); 

        // Adiciona o painel de cadastro à tela principal
        telaLogin.getContentPane().add(painel, BorderLayout.WEST);
    }
    
       private void desenhaLogin(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário"));

        JPanel formulario = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        painelLabel.add(new JLabel("Login (parte antes do @ no email)"));
        painelLabel.add(new JLabel("Senha"));
        
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        tfLogin = new JTextField(20);
        tfSenha = new JTextField(20);
        
        painelField.add(tfLogin);
        painelField.add(tfSenha);

        formulario.add(painelLabel);
        formulario.add(painelField);

        painel.setLayout(new BorderLayout());
        painel.add(formulario, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        //btnAdicionar.addActionListener(new Login(this));
        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        painel.add(botoes, BorderLayout.SOUTH);
        telaLogin.getContentPane().add(painel, BorderLayout.EAST);
    }
    
    private void auxLogin(){
        
    }
}
