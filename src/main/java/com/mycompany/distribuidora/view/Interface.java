                        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.distribuidora.view;


import com.mycompany.distribuidora.model.Usuario;
import com.mycompany.distribuidora.persistencia.UsuarioPersistencia;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private JTextField tfUsuario;
    private JTextField tfNome;
    private JTextField tfCpf;
    private JTextField tfCnpj;
    private JTextField tfEmail;
    private JTextField tfTelefone;
    private JTextField tfSenha;

    private UsuarioPersistencia usuarioPersistencia;

    public Interface() {
        usuarioPersistencia = new UsuarioPersistencia();
    }

    public void desenha() {

        tela = new JFrame("Distribuidora");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);
        tela.setLayout(new BorderLayout());
//        tela.setResizable(false);

        desenhaTelaInicial();

        tela.pack();
        tela.setVisible(true);
    }

    private void desenhaTelaInicial(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Tela inicial"));
//        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        tfUsuario = new JTextField(20);

        JPanel secondPainelField = new JPanel();
        secondPainelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfSenha = new JTextField(20);

        painelField.add(tfUsuario);
        secondPainelField.add(tfSenha);

        JPanel componentes = new JPanel();
        componentes.add(tipoLogin);
        componentes.add(painelField);
        componentes.add(secondPainelField);

        painel.add(componentes, BorderLayout.CENTER);


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
        JDialog painel = new JDialog(tela, "Cadastro", true);
//        painel.setBorder(BorderFactory.createTitledBorder("Cadastro"));
        painel.setSize(WIDTH, HEIGHT);
//        painel.setPreferredSize(new Dimension(500, 500));
        painel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel cadastro = new JPanel();
        cadastro.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));

        cadastro.add(new JLabel("Nome:"));
        tfNome = new JTextField(20);
        cadastro.add(tfNome);

        //FAZER CASO PARA EMPRESA E PESSOA FISICA:
        //SE PESSOA FISICA ----> painelLabel.add(new JLabel("CPF"));
        //SE PESSOA JURIDICA ---> painelLabel.add(new JLabel("CNPJ"));
        cadastro.add(new JLabel("Telefone:"));
        tfTelefone = new JTextField(20);
        cadastro.add(tfTelefone);

        cadastro.add(new JLabel("Email:"));
        tfEmail = new JTextField(20);
        cadastro.add(tfEmail);

        cadastro.add(new JLabel("Senha:"));
        tfSenha = new JTextField(20);
        cadastro.add(tfSenha);

        painel.getContentPane().add(cadastro, BorderLayout.CENTER);


        painel.setLayout(new BorderLayout());
        painel.add(cadastro, BorderLayout.CENTER);

        JButton btnFimCadastro = new JButton("Finazizar Cadastro");
        btnFimCadastro.addActionListener(e -> finalizarCadastro());

        JButton btnVoltaTela = new JButton("Voltar para tela inicial");
        btnVoltaTela.addActionListener(e -> {
            painel.dispose();
            desenhaTelaInicial();
        });
//        CRIAR PERSISTENCIA PARA NOVO CADASTRO
//        btnFimCadastro.addActionListener(new AdicionarCadastro(this));
        JPanel botoes = new JPanel();
        botoes.add(btnFimCadastro);
        botoes.add(btnVoltaTela);

        painel.getContentPane().add(botoes, BorderLayout.SOUTH);
        painel.setVisible(true);
    }
    private void finalizarCadastro() {
        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();
        String email = tfEmail.getText();
        String senha = tfSenha.getText();

        Usuario usuario = new Usuario(nome, telefone, email, senha);

        usuarioPersistencia.salvarUsuario(usuario);

        JOptionPane.showMessageDialog(tela, "Cadastro finalizado com sucesso!");
    }
}
