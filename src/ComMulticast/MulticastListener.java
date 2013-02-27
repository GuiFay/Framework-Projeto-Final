package ComMulticast;

import Core.ComunicacaoFacade;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import javax.swing.JTextArea;

public class MulticastListener extends Thread {

    MulticastSocket multicast;
    DatagramPacket pacote;
    JTextArea area;
    ComunicacaoFacade fachada;

    public MulticastListener(MulticastSocket multicast) {
        this.multicast = multicast;
        fachada = new ComunicacaoFacade();
    }

    public MulticastListener(MulticastSocket multicast, JTextArea area) {
        this.multicast = multicast;
        this.area = area;
        fachada = new ComunicacaoFacade();
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
                    enviarMensagemDireta();
                    continue;
                }
                
                enviarMensagemChat(header + ComunicacaoFacade.SEPARADOR_MSG + body);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void executarComando(String body) {
        System.out.println("Executando comando");
    }

    private void enviarMensagemDireta() {
        System.out.println("Enviando msg");
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
