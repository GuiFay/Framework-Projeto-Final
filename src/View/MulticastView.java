package View;

import ComMulticast.MulticastListener;
import ComMulticast.MulticastSender;
import ComMulticast.MulticastPeer;
import Core.AutenticacaoFacade;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class MulticastView extends JFrame {

    /**
     *
     */
    private JLabel lMensagem;
    private static final long serialVersionUID = -1202136001945439361L;
    private JPanel painel;
    private JButton entrar;
    private JButton sair;
    private JButton excluir;
    private JButton alterar;
    private JTextArea mensagens;
    private JTextField mensagem;
    private JButton enviar;
    MulticastPeer peer;
    MulticastListener listener;
    MulticastSender sender;
    private String nick;
    AutenticacaoFacade facade = AutenticacaoFacade.getInstance();

    public MulticastView() {
        super("Chat");
        setSize(800, 600);
        setLocationRelativeTo(null);
        peer = new MulticastPeer("229.50.50.50", 7010);
    }

    public void initialize() {
        createGroup();
        JOptionPane.showMessageDialog(null,
                "Bem vindo ao Chat " + facade.getUsuarioLogado().nome
                + "!", "Bem Vindo!!",
                JOptionPane.INFORMATION_MESSAGE, null);
        // pack();
        setVisible(true);

    }

    private void createGroup() {
        /**
         * TÍTULO
         */
        painel = new JPanel();
        add(painel);
        painel.setLayout(new BorderLayout());
        // Painel no page start
        JPanel pageStart = new JPanel();
        painel.add(pageStart, BorderLayout.PAGE_START);
        //bt. alterar
        alterar = new JButton("Alterar Cadastro");
        pageStart.add(alterar);
        alterar.addActionListener(new AlterarListener());
        //bt. excluir
        excluir = new JButton("Excluir Cadastro");
        pageStart.add(excluir);
        excluir.addActionListener(new ExcluirListener());
        // Bt. entrar
        entrar = new JButton("Entrar");
        pageStart.add(entrar);
        entrar.addActionListener(new EntrarListener());
        // Bt. Sair.
        sair = new JButton("Sair");
        pageStart.add(sair);
        sair.addActionListener(new SairListener());
        sair.setEnabled(false);
        // Area de mensagens.
        mensagens = new JTextArea();
        mensagens.setEditable(false);
        painel.add(mensagens, BorderLayout.CENTER);
        JPanel pageEnd = new JPanel();
        pageEnd.setLayout(new BoxLayout(pageEnd, BoxLayout.LINE_AXIS));
        painel.add(pageEnd, BorderLayout.PAGE_END);
        mensagem = new JTextField();
        pageEnd.add(mensagem);
        mensagem.addActionListener(new EnviarListener());
        enviar = new JButton("Enviar");
        pageEnd.add(enviar);
        enviar.addActionListener(new EnviarListener());
    }

    private void ativarBt(boolean ativar) {
        entrar.setEnabled(!ativar);
        sair.setEnabled(ativar);
        mensagem.setEnabled(ativar);
        enviar.setEnabled(ativar);
    }

    //evendo de alterar cadastro
    private static class AlterarListener implements ActionListener {

        public AlterarListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FormAlterarUsuario alterar = new FormAlterarUsuario();
            alterar.initialize();
        }
    }

    //evento de excluir cadastro
    class ExcluirListener implements ActionListener {

        public ExcluirListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FormExcluirUsuario excluir = new FormExcluirUsuario();
            excluir.initialize();
        }
    }

    class EntrarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            nick = JOptionPane.showInputDialog("Digite o seu Nick");
            peer.joinChat();
            sender = new MulticastSender(peer.getSocket(), peer.getGrupo());
            listener = new MulticastListener(peer.getSocket(), mensagens);
            listener.start();
            mensagens.append("\nEntrei no chat...");
            ativarBt(true);
            mensagem.requestFocus();
        }
    }

    class SairListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            peer.leaveChat();
            mensagens.append("\nSai do chat...");
            ativarBt(false);
        }
    }

    class EnviarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!mensagem.getText().equals("")) {
                sender.sendMsg(mensagem.getText(), nick);
                // mensagens.append("\nEu: " + mensagem.getText());
                mensagem.setText("");
                mensagem.requestFocus();
            }
        }
    }
}
