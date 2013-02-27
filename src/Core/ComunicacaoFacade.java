/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Controller.Serviços.ServicoChat;
import Controller.Serviços.Servicos;
import javax.swing.JTextArea;

/**
 *
 * @author Magnon
 */
public class ComunicacaoFacade {

    Servicos servicos;
    public static final String SEPARADOR_MSG = " >>> ";
    public static final String COMANDO_ENTRADA = "[COMANDO][TIPO_ENTRADA]";
    public static final String COMANDO_SAIDA = "[COMANDO][TIPO_SAIDA]";
    
    public ComunicacaoFacade() {
        servicos = new ServicoChat();
    }        
    
    public boolean isComando(String msg) {        
        return servicos.isComando(msg);
    }

    public boolean isMensagemDireta(String msg) {
        return servicos.isMensagemDireta(msg);
    }
    
    public void initMultcast(){
        servicos.initMultcast();
    }

    public void entrarChat(JTextArea mensagens, String nick) {
        servicos.entrarChat(mensagens);
        servicos.enviarMensagem(ComunicacaoFacade.COMANDO_ENTRADA, nick);
    }

    public void sairChat() {
        servicos.sairChat();
    }

    public void enviarMsg(String text, String nick) {
        servicos.enviarMensagem(text, nick);
    }
    
}
