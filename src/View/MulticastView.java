package View;

import ComMulticast.MulticastListener;
import ComMulticast.MulticastSender;
import ComMulticast.MulticastPeer;
import Core.AutenticacaoFacade;
import Core.ComunicacaoFacade;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JButton sair;
    private JButton excluir;
    private JButton alterar;
    private JTextArea mensagens;
    private JTextField mensagem;
    private JButton enviar;

    private String nick;
    
    AutenticacaoFacade facade = AutenticacaoFacade.getInstance();
    ComunicacaoFacade com = ComunicacaoFacade.getInstance();
    
    
    public MulticastView() {
        super("Chat");
        setSize(800, 600);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        com.initMultcast();
    }

    public void initialize() {
        createGroup();
        JOptionPane.showMessageDialog(null,
                "Bem vindo ao Chat " + facade.getUsuarioLogado().nome
                + "!", "Bem Vindo!!",
                JOptionPane.INFORMATION_MESSAGE, null);
        
            nick = facade.getUsuarioLogado().login;
            
            com.entrarChat(mensagens, facade.getUsuarioLogado().login);
        System.out.println("Numero de mensagens na fila " + com.getMensagensOffline(facade.getUsuarioLogado().login).size());
          
        if(com.isMensagensOffline(facade.getUsuarioLogado().login)){
            mensagens.append("\nVocê tem mensagens offline!!: \n");
            ArrayList<Object> temp = com.getMensagensOffline(facade.getUsuarioLogado().login);
            if(temp == null || temp.size() == 0)
            {
                
            } else {
                for(int i =0; i < temp.size(); i +=2 ){
                    mensagens.append("\n"+ temp.get(i+1));
                }
                mensagens.append("\n");
            }
            com.eraseMesagensOffline(facade.getUsuarioLogado().login);
        } else {
            mensagens.append("\nVocê nao tem mensagens offline\n");
        }
         

            mensagens.append("\nEntrei no chat...");
            ativarBt(true);
            mensagem.requestFocus();
        
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
       
        lMensagem = new JLabel("Logado Como:"  +  facade.getUsuarioLogado().nome);
        lMensagem.setFont(new Font("Arial",Font.BOLD,26));
        
        pageStart.add(lMensagem);
        //bt. alterar
        alterar = new JButton("Alterar Cadastro");
        pageStart.add(alterar);
        alterar.addActionListener(new AlterarListener());
        //bt. excluir
        excluir = new JButton("Excluir Cadastro");
        pageStart.add(excluir);
        excluir.addActionListener(new ExcluirListener());
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

    class SairListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            com.sairChat();
            mensagens.append("\nSai do chat...");
            setVisible(false);
             JOptionPane.showMessageDialog(null,
                "Usuário " + facade.getUsuarioLogado().nome
                + "Deslogado", "LogOut",
                JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    class EnviarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!mensagem.getText().equals("")) {
                com.enviarMsg(mensagem.getText(), nick);

                mensagem.setText("");
                mensagem.requestFocus();
            }
        }
    }
}
