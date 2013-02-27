/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Core.AutenticacaoFacade;
import Model.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Guilherme
 */
public class TelaInicial extends JFrame {

    private JLabel lMensagem;
    private JLabel lUsername;
    private JLabel lSenha;
    private JTextField tUsername;
    private JPasswordField pwSenha;
    JButton bEntrar;
    JButton bCadastrar;
    AutenticacaoFacade framework = AutenticacaoFacade.getInstance();

    public TelaInicial() {

        /**
         * DEFININDO CONFIGURAÇÕES DO PAINEL
         */
        setLayout(new GridLayout(4, 1));
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 550) / 2, (screenSize.height - 250) / 2, 550, 250);
        setBackground(new Color(170, 170, 90));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Framework DAS");


    }

    public void initialize() {
        createGroup();
        setVisible(true);
    }

    private void createGroup() {


        /**
         * INSTÂNCIANDO LABELS
         */
        lMensagem = new JLabel("Bem Vindo!!!");
        lUsername = new JLabel("Username:");
        lSenha = new JLabel("Senha:");

        /**
         * TÍTULO
         */
        JLabel Mensagem = new JLabel();
        Mensagem.setLayout(new FlowLayout(FlowLayout.CENTER));
        Mensagem.add(lMensagem);
        add(Mensagem);

        /**
         * NOME
         */
        JPanel Username = new JPanel();
        Username.setLayout(new FlowLayout(FlowLayout.CENTER));
        Username.add(lUsername);
        Username.add(tUsername = new JTextField(15));
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

        bEntrar = new JButton("Entrar", new ImageIcon("icons/arrow.png"));
        Botoes.add(bEntrar);

        bCadastrar = new JButton("Novo Aqui?", new ImageIcon("icons/user--plus.png"));
        Botoes.add(bCadastrar);

        add(Botoes);

        event e = new event();
        bEntrar.addActionListener(e);
        bCadastrar.addActionListener(e);

    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Usuario u = new Usuario(null, null,
                    tUsername.getText(), pwSenha.getText());

            if (e.getSource() == bCadastrar) {
                FormCadastroUsuario cadastro = new FormCadastroUsuario();
                cadastro.initialize();

            }

            //Teste
            if ((e.getSource() == bEntrar)) {

                try {
                    if (framework.autenticarUsuario(u)) {
                        MulticastView view = new MulticastView();
                        view.initialize();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Eeeta menino!", "VISH",
                                JOptionPane.ERROR_MESSAGE, null);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
