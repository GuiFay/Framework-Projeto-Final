package ComMulticast;

import Core.AutenticacaoFacade;
import Core.ComunicacaoFacade;
import Model.Usuario;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;

public class MulticastListener extends Thread {

    MulticastSocket multicast;
    DatagramPacket pacote;
    JTextArea area;

    ComunicacaoFacade fachada = ComunicacaoFacade.getInstance();
    AutenticacaoFacade aut = AutenticacaoFacade.getInstance();
    
    public MulticastListener(MulticastSocket multicast) {
        this.multicast = multicast;
    }

    public MulticastListener(MulticastSocket multicast, JTextArea area) {
        this.multicast = multicast;
        this.area = area;
    }

    @Override
    public void run() {
        String datagram , header = "", body = "";
        while (true) {
            try {
                byte[] buffer = new byte[1000];
                pacote = new DatagramPacket(buffer, buffer.length);
                multicast.receive(pacote);
                
                datagram =  new String(pacote.getData());
                header = datagram.split(ComunicacaoFacade.SEPARADOR_MSG)[0];
                body   = datagram.split(ComunicacaoFacade.SEPARADOR_MSG)[1];
                
                if( fachada.isComando(body) ){
                    executarComando(body);
                    continue;                    
                } 
                
                if( fachada.isMensagemDireta(body) ) {
                    enviarMensagemDireta(header,body);
                    continue;
                }
                
                enviarMensagemChat(header + ComunicacaoFacade.SEPARADOR_MSG + body);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void executarComando(String body) {
      //  if( ! "monitor".equals(aut.getUsuarioLogado().login) ){
      //      return;
      //  }
        if(body.startsWith(ComunicacaoFacade.COMANDO_ENTRADA)) {
            System.out.println("Entrando...");            
            return;
        }else if(body.startsWith(ComunicacaoFacade.COMANDO_SAIDA)) {
            System.out.println("Saindo...");
            return;
        }
        
        System.out.println("Comando inexistente");
    }

    private void enviarMensagemDireta(String header, String body) {
        
        String destinatario = body.split(":::")[0];
        System.out.println(destinatario);
        if(!destinatario.equals(aut.getUsuarioLogado().login)){
            fachada.addMensagemOffline(destinatario,
                    header + ComunicacaoFacade.SEPARADOR_MSG + body);
        }
        System.out.println(fachada.getMensagensOffline(aut.
                getUsuarioLogado().login).size());
    }

    private void enviarMensagemChat(String mensagem) {
        String msg = "IP " + pacote.getAddress() + " - " + mensagem;
        if (area != null) {
            area.append("\n" + msg);
        } else {
            System.out.println(msg);
        }
    }
}
