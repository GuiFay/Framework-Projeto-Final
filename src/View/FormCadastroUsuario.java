/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DaoUsuarioTXT;
import Model.Usuario;
import com.sun.istack.internal.logging.Logger;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
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

    public FormCadastroUsuario() {

        /**
         * DEFININDO CONFIGURAÇÕES DO PAINEL
         */
        setLayout(new GridLayout(7, 1));
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 600);
        setBackground(new Color(170, 170, 90));

        /**
         * INSTÂNCIANDO LABELS
         */
        lCadastro = new JLabel("CADASTRO");
        lNome = new JLabel("Nome:");
        lSobrenome = new JLabel("Sobrenome:");
        lUsername = new JLabel("Username:");
        lSenha = new JLabel("Senha");


        /**
         * TÍTULO
         */
        JLabel Titulo = new JLabel();
        Titulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Titulo.add(lCadastro);
        add(Titulo);

        /**
         * NOME
         */
        JPanel Nome = new JPanel();
        Nome.setLayout(new FlowLayout(FlowLayout.CENTER));
        Nome.add(lNome);
        Nome.add(tNome = new JTextField(30));
        add(Nome);

        /**
         * SOBRENOME
         */
        JPanel Sobrenome = new JPanel();
        Sobrenome.setLayout(new FlowLayout(FlowLayout.CENTER));
        Sobrenome.add(lSobrenome);
        Sobrenome.add(tSobrenome = new JTextField(30));
        add(Sobrenome);

        /**
         * USERNAME
         */
        JPanel Username = new JPanel();
        Username.setLayout(new FlowLayout(FlowLayout.CENTER));
        Username.add(lUsername);
        Username.add(tUsername = new JTextField(20));
        add(Username);

        /**
         * SENHA
         */
        JPanel Senha = new JPanel();
        Senha.setLayout(new FlowLayout(FlowLayout.CENTER));
        Senha.add(lSenha);
        Senha.add(pwSenha = new JPasswordField(6));
        add(Senha);


        /**
         * BOTÕES
         */
        JPanel Botoes = new JPanel();
        Botoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        //bVoltar = new JButton("Voltar");
        bCadastrar = new JButton("Gravar");
        Botoes.add(bCadastrar);
        //Botoes.add(bVoltar);
        add(Botoes);

        event e = new event();
        //bVoltar.addActionListener(e);
        bCadastrar.addActionListener(e);

        setVisible(true);

    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Usuario usuario = new Usuario(tNome.getText(), tSobrenome.getText(), tUsername.getText(), pwSenha.getText());
            ArrayList array = new ArrayList();
            array.add(usuario);

            DaoUsuarioTXT.incluirUsuario(usuario);

            try {
                ArrayList lido = DaoUsuarioTXT.DeserializarUsuario();
                JOptionPane.showMessageDialog(null,
                        "Usuário Cadastrado com sucesso!", "Cadastrado!",
                        JOptionPane.INFORMATION_MESSAGE, null);



                //    try{
                //        StringBuffer b = new StringBuffer();
                //        String cadastro = tNome.getText()+"|"+tSobrenome.getText()+"|"+tUsername.getText()+"|"+pwSenha.getText();
                //        FileWriter stream = new FileWriter ("cadastro.txt");
                //        BufferedWriter out = new BufferedWriter (stream);
                //        out.write(cadastro);
                //        out.close();
                //        } catch (Exception ex){}
                //    
                //
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Não foi possível cadastrar o Usuário!", "ATENÇÃO!!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
