/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Controller.Serviços.ServicoChat;
import Controller.Serviços.Servicos;
import Model.Usuario;
import java.util.ArrayList;
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
    public static final String COMANDO_ATUALIZAR_LISTA = "[COMANDO][TIPO_ATUALIZAR]";
    
    static ComunicacaoFacade instance = null;
    ArrayList<Object> mensagens_offline;
    
    private ComunicacaoFacade() {
        servicos = new ServicoChat();
        mensagens_offline  = new ArrayList<Object>();
    }        
    
    public static ComunicacaoFacade getInstance() {
        if(instance == null){
            instance = new ComunicacaoFacade();
        }
        return instance;
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

    public boolean isMensagensOffline(String login) {
        for(int i=0; i < mensagens_offline.size(); i +=2){
            if(login.equals( (String) mensagens_offline.get(i) ) ){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Object> getMensagensOffline(String login) {
        ArrayList<Object> temp = new ArrayList<Object>();
        for(int i=0; i < mensagens_offline.size(); i+=2){
            if(login.equals( (String) mensagens_offline.get(i)) ){
                temp.add(mensagens_offline.get(i));
                temp.add(mensagens_offline.get(i+1));
            }
        }
        return temp;
    }
    
    public void addMensagemOffline(String username, String msg){
        mensagens_offline.add(username);
        mensagens_offline.add(msg);
    }

    public void eraseMesagensOffline(String login) {
        for(int i=0; i < mensagens_offline.size(); i+=2){
            if(login.equals( (String) mensagens_offline.get(i)) ){
                mensagens_offline.remove(i);
                mensagens_offline.remove(i+1);
            }
        }
    }

    
}
