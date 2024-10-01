package com.mycompany.distribuidora.view;

import com.mycompany.distribuidora.exception.EmailException;
import com.mycompany.distribuidora.exception.TelefoneException;
import com.mycompany.distribuidora.model.Usuarios.Pessoa;
import com.mycompany.distribuidora.model.Usuarios.Usuario;
import com.mycompany.distribuidora.persistencia.PessoaPersistencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private PessoaPersistencia pessoaPersistencia;

    public Interface() {
        pessoaPersistencia = new PessoaPersistencia() {
            @Override
            public void save(java.util.List<Pessoa> itens) {
                throw new UnsupportedOperationException("Nao suportado");
            }
        };
    }

    public void desenha() {

        tela = new JFrame("Distribuidora");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);
        tela.setLayout(new BorderLayout());

        desenhaTelaInicial();

        tela.pack();
        tela.setVisible(true);
    }

    private void desenhaTelaInicial() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Tela inicial"));
        painel.setLayout(new BorderLayout());

        String[] tipos = {"CPF", "CNPJ"};
        JComboBox tipoLogin = new JComboBox(tipos);
        tipoLogin.setSelectedIndex(1);

        JPanel login = new JPanel();
        JPanel painelLabel = new JPanel();

        painelLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        painelLabel.add(new JLabel("Login"));

        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        tfUsuario = new JTextField(20);

        JPanel secondPainelField = new JPanel();
        secondPainelField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
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

    private void desenhaCadastro() {
        JDialog painel = new JDialog(tela, "Cadastro", true);
        painel.setSize(WIDTH, HEIGHT);
        painel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel cadastro = new JPanel();
        cadastro.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));

        cadastro.add(new JLabel("Nome:"));
        tfNome = new JTextField(20);
        cadastro.add(tfNome);

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
        btnFimCadastro.addActionListener(e -> {
            try {
                finalizarCadastro();
            } catch (EmailException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TelefoneException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JButton btnVoltaTela = new JButton("Voltar para tela inicial");
        btnVoltaTela.addActionListener(e -> {
            painel.dispose();
            desenhaTelaInicial();
        });

        JPanel botoes = new JPanel();
        botoes.add(btnFimCadastro);
        botoes.add(btnVoltaTela);

        painel.getContentPane().add(botoes, BorderLayout.SOUTH);
        painel.setVisible(true);
    }

    private void finalizarCadastro() throws EmailException, TelefoneException {
        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();
        String email = tfEmail.getText();
        String senha = tfSenha.getText();
        
        Pessoa usuario = new Pessoa(nome, telefone, senha, "ruas dos bobos numero 0", email,'c') {};

        pessoaPersistencia.save(usuario);

        JOptionPane.showMessageDialog(tela, "Cadastro finalizado com sucesso!");
    }

}
