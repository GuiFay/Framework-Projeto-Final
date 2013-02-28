/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Core.AutenticacaoFacade;
import Model.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Guilherme
 */
public class FormCadastroUsuario extends JFrame {

    private JLabel lCadastro;
    private JLabel lNome;
    private JLabel lSobrenome;
    private JLabel lUsername;
    private JLabel lSenha;
    private JTextField tNome;
    private JTextField tSobrenome;
    private JTextField tUsername;
    private JPasswordField pwSenha;
    JButton bCadastrar;
    JButton bVoltar;
    
    AutenticacaoFacade framework = AutenticacaoFacade.getInstance();

    public FormCadastroUsuario() {
        /**
         * DEFININDO CONFIGURAÇÕES DO PAINEL
         */
        //Título
        super("Cadastro!!!");
        //Layout
        setLayout(new GridLayout(7, 1));
        //screenSize
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 600);
        //cor de fundo
        setBackground(new Color(170, 170, 90));
    }

    public void initialize() {
        createGroup();
        setVisible(true);
    }

    private void createGroup() {

        /**
         * INSTÂNCIANDO LABELS
         */
        lCadastro = new JLabel("CADASTRO");
        lNome = new JLabel("Nome:");
        lSobrenome = new JLabel("Sobrenome:");
        lUsername = new JLabel("Username:");
        lSenha = new JLabel("Senha");

        JLabel titulo = new JLabel();
        titulo.setLayout( new FlowLayout(FlowLayout.CENTER));
        titulo.add(lCadastro);

        add(titulo);
        JPanel nome = new JPanel();

        nome.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        nome.add(lNome);

        nome.add(tNome = new JTextField(30));
        add(nome);
        /**
         * SOBRENOME
         */
        JPanel sosbrenome = new JPanel();

        sosbrenome.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        sosbrenome.add(lSobrenome);

        sosbrenome.add(tSobrenome = new JTextField(30));
        add(sosbrenome);
        /**
         * USERNAME
         */
        JPanel username = new JPanel();

        username.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        username.add(lUsername);

        username.add(tUsername = new JTextField(20));
        add(username);
        /**
         * SENHA
         */
        JPanel senha = new JPanel();

        senha.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        senha.add(lSenha);

        senha.add(pwSenha = new JPasswordField(6));
        add(senha);
        /**
         * BOTÕES
         */
        JPanel Botoes = new JPanel();

        Botoes.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        //bVoltar = new JButton("Voltar");
        bCadastrar = new JButton("Gravar");

        Botoes.add(bCadastrar);
        //Botoes.add(bVoltar);

        add(Botoes);
        event e = new event();
        bCadastrar.addActionListener(e);


    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Usuario usuario = new Usuario(tNome.getText(), tSobrenome.getText(), tUsername.getText(), pwSenha.getText());
            try {                
                framework.cadastrarUsuario(usuario);                
                JOptionPane.showMessageDialog(null,
                            "Usuário Cadastrado com sucesso!", "Cadastrado!",
                            JOptionPane.INFORMATION_MESSAGE, null);                
                setVisible(false);
            } catch (Exception ex) {         
                JOptionPane.showMessageDialog(null,
                            "Não foi possível cadastrar o Usuário!", "ATENÇÃO!!",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
