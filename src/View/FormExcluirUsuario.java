/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Autenticacao.AutenticadorTXT;
import Controller.Autenticacao.AutenticadorXML;
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
 * @author Greg
 */
public class FormExcluirUsuario extends JFrame {

    private JLabel lExcluir;
    private JLabel lSenha;
    private JTextField pwSenha;
    JButton bExcluir;
    JButton bVoltar;
    AutenticacaoFacade framework = AutenticacaoFacade.getInstance();

    public FormExcluirUsuario() {
        /**
         * DEFININDO CONFIGURAÇÕES DO PAINEL
         */
        setLayout(new GridLayout(4, 1));
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 550) / 2, (screenSize.height - 250) / 2, 550, 250);
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
        lExcluir = new JLabel("Informe a senha do Usuário para EXCLUIR");
        lSenha = new JLabel("Senha:");

        /**
         * TÍTULO
         */
        JLabel Titulo = new JLabel();
        Titulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Titulo.add(lExcluir);
        add(Titulo);

        /**
         * USERNAME
         */
        JPanel senha = new JPanel();
        senha.setLayout(new FlowLayout(FlowLayout.CENTER));
        senha.add(lSenha);
        senha.add(pwSenha = new JTextField(20));
        add(senha);

        /**
         * BOTÕES
         */
        JPanel Botoes = new JPanel();
        Botoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        //bVoltar = new JButton("Voltar");
        bExcluir = new JButton("Excluir Cadastro");
        Botoes.add(bExcluir);
        //Botoes.add(bVoltar);
        add(Botoes);

        FormExcluirUsuario.event e = new FormExcluirUsuario.event();
        //bVoltar.addActionListener(e);
        bExcluir.addActionListener(e);

        setVisible(true);
    }

    public class event implements ActionListener {

        private Class AutenticadorXML;
        private Class AutenticadorTXT;

        @Override
        public void actionPerformed(ActionEvent e) {

            Usuario tempUser;
            try {
                tempUser = framework.getUsuarioLogado();
                if (tempUser.senha.equals(pwSenha.getText())) {
                    framework.deletarUsuario(tempUser);
                    framework.setAutenticacao(AutenticadorXML.class);
                    framework.deletarUsuario(tempUser);
                    framework.setAutenticacao(AutenticadorTXT.class);
                    JOptionPane.showMessageDialog(null,
                            "Usuário Excluido com sucesso!", "Excluido!",
                            JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Senha incorreta", "Senha incorreta!",
                            JOptionPane.INFORMATION_MESSAGE, null);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Não foi possível excluir o Usuário!", "ATENÇÃO!!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
