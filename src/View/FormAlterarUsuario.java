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
import javax.swing.*;

/**
 *
 * @author Guilherme
 */
public class FormAlterarUsuario extends JFrame {

    private JLabel lAlteracao;
    private JLabel lNome;
    private JLabel lSobrenome;
    private JLabel lUsername;
    private JLabel lSenha;
    private JTextField tNome;
    private JTextField tSobrenome;
    private JTextField tUsername;
    private JPasswordField pwSenha;
    JButton bAlteracao;
    JButton bVoltar;

    AutenticacaoFacade facade = AutenticacaoFacade.getInstance();            
    
    public FormAlterarUsuario() {
        /**
         * DEFININDO CONFIGURAÇÕES DO PAINEL
         */
        //Título
        super("Alteração de Dados!!!");
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
        
        Usuario userTemp = facade.getUsuarioLogado();
        
        tNome.setText(userTemp.nome);
        tSobrenome.setText(userTemp.sobrenome);
        tUsername.setText(userTemp.login);
        
    }

    private void createGroup() {

        /**
         * INSTÂNCIANDO LABELS
         */
        lAlteracao = new JLabel("Alteração de Dados");
        lNome = new JLabel("Nome:");
        lSobrenome = new JLabel("Sobrenome:");
        lUsername = new JLabel("Username:");
        lSenha = new JLabel("Senha");
        /**
         * TÍTULO
         */
        JLabel Titulo = new JLabel();

        Titulo.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        Titulo.add(lAlteracao);

        add(Titulo);
        /**
         * NOME
         */
        JPanel Nome = new JPanel();

        Nome.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        Nome.add(lNome);

        Nome.add(tNome = new JTextField(30));
        add(Nome);
        /**
         * SOBRENOME
         */
        JPanel Sobrenome = new JPanel();

        Sobrenome.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        Sobrenome.add(lSobrenome);

        Sobrenome.add(tSobrenome = new JTextField(30));
        add(Sobrenome);
        /**
         * USERNAME
         */
        JPanel Username = new JPanel();

        Username.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        Username.add(lUsername);

        Username.add(tUsername = new JTextField(20));
        add(Username);
        /**
         * SENHA
         */
        JPanel Senha = new JPanel();

        Senha.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        Senha.add(lSenha);

        Senha.add(pwSenha = new JPasswordField(6));
        add(Senha);
        /**
         * BOTÕES
         */
        JPanel Botoes = new JPanel();

        Botoes.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        //bVoltar = new JButton("Voltar");
        bAlteracao = new JButton("Alterar");

        Botoes.add(bAlteracao);
        //Botoes.add(bVoltar);

        add(Botoes);
        event e = new event();
        bAlteracao.addActionListener(e);


    }

    // talvez seja melhor não dar opção de alterar o username
    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Usuario usuario = new Usuario(tNome.getText(), tSobrenome.getText(), tUsername.getText(), pwSenha.getText());
            try {                
                facade.alterarUsuario(facade.getUsuarioLogado(), usuario);                
                JOptionPane.showMessageDialog(null,
                            "Usuário alterado com sucesso!", "Alterado!",
                            JOptionPane.INFORMATION_MESSAGE, null);                
            } catch (Exception ex) {         
                JOptionPane.showMessageDialog(null,
                            "Não foi possível alterar o Usuário!", "ATENÇÃO!!",
                            JOptionPane.ERROR_MESSAGE);
            }
            
           
        }
    }
}
