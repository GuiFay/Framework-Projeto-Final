package View;

import ComMulticast.MulticastListener;
import ComMulticast.MulticastSender;
import ComMulticast.MulticastPeer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class MulticastView extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -1202136001945439361L;
    private JPanel painel;
    private JButton entrar;
    private JButton sair;
    private JTextArea mensagens;
    private JTextField mensagem;
    private JButton enviar;
    MulticastPeer peer;
    MulticastListener listener;
    MulticastSender sender;
    private String nick;

    public MulticastView() {
        super("Chat");
        setSize(800, 600);
        setLocationRelativeTo(null);
        peer = new MulticastPeer("229.50.50.50", 7010);
    }

    public void initialize() {
        createGroup();
        JOptionPane.showMessageDialog(null,
                        "Usuário Cadastrado com sucesso!", "Cadastrado!",
                        JOptionPane.INFORMATION_MESSAGE, null);
        // pack();
        setVisible(true);
        
    }

    private void createGroup() {
        painel = new JPanel();
        add(painel);
        painel.setLayout(new BorderLayout());
        // Painel no page start
        JPanel pageStart = new JPanel();
        painel.add(pageStart, BorderLayout.PAGE_START);
        // Bt. entrar.
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
