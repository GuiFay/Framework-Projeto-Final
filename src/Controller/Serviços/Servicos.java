/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Serviços;

import ComMulticast.MulticastListener;
import ComMulticast.MulticastPeer;
import ComMulticast.MulticastSender;
import javax.swing.JTextArea;

/**
 *
 * @author Guilherme
 */
public abstract class Servicos {
    
    public static final String COMANDO = "[COMANDO][TIPO";
    public static final String MENSAGEM_DIRETA = ":::";
    
    public static final int PORTA_PADRAO = 7010;
    public static final String ENDERECO_MULTCAST = "229.50.50.50";
    
    MulticastPeer peer;
    MulticastSender sender;
    MulticastListener listener;
    
    public abstract int pesquisarStatus();
    public abstract void  SelecionarUsuario();
    public abstract String escreverMensagem();
    public abstract void enviarMensagem();
    
    public boolean isComando(String msg){
        if(msg.startsWith(COMANDO)) return true;
        return false;
    }
    
    public boolean isMensagemDireta(String msg){
        if(msg.contains(":::")) return true;
        return false;
    }
    
    public void initMultcast() {
        peer = new MulticastPeer(ENDERECO_MULTCAST, PORTA_PADRAO);
    }

    public void entrarChat(JTextArea mensagens) {
        peer.joinChat();
        sender = new MulticastSender(peer.getSocket(), peer.getGrupo());
        listener = new MulticastListener(peer.getSocket(), mensagens);
        listener.start();
    }

    public void sairChat() {
        peer.leaveChat();
    }

    public void enviarMensagem(String text, String nick) {
        sender.sendMsg(text, nick);
    }
    
}
