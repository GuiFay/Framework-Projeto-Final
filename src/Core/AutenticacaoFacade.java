package Core;

import Controller.Autenticacao.Autenticacao;
import Controller.Autenticacao.AutenticadorTXT;

import Model.Usuario;

public class AutenticacaoFacade {

    private Autenticacao at = null;
    private static AutenticacaoFacade instance = null;
   
    private AutenticacaoFacade() {
    
    }
    
    public static AutenticacaoFacade getInstance(){
        if(instance == null){
            instance = new AutenticacaoFacade();
        }
        return instance;
    }
    
    public boolean autenticarUsuario(Usuario u) throws Exception{
        at = getAutenticacao();
        if(at.AutenticarUsuario(u) == null)
            return false;
        return true;
            
    }
    
    public boolean alterarUsuario(Usuario uAntigo, Usuario uNovo ) throws Exception{
        at = getAutenticacao();
        return at.AlterarUsuario(uAntigo, uNovo);
    }
    
    public boolean cadastrarUsuario(Usuario u) throws Exception{
        at = getAutenticacao();
        return at.CadastrarUsuario(u);
    }
    
    public boolean listarUsuarioComoLogado(Usuario u) throws Exception{
        at = getAutenticacao();
        return at.listarUsuarioComoLogado(u);
    }
    
    public boolean deletarUsuario(Usuario u) throws Exception{
        at = getAutenticacao();
        return at.RemoverUsuario(u);
    }
    
    public Usuario getUsuarioLogado() {
        at = getAutenticacao();
        return at.getUsuarioLogado();
    }
    
    private Autenticacao getAutenticacao() {
        if(at == null){
            //Autenticacao padrao
            at = obterMetodoAutenticacao( AutenticadorTXT.class);
        }
        return at;
    }
    
    public void setAutenticacao(Class t){
        at = obterMetodoAutenticacao(t);
    }
    
    
     private Autenticacao obterMetodoAutenticacao(Class t) {
        Autenticacao autenticação = null;
        
        try {
            autenticação = (Autenticacao) t.newInstance();
        } catch (InstantiationException ex) {
            System.out.println("Impossivel inicar a instancia da "
                    + "classe de autenticacao");
        } catch (IllegalAccessException ex) {
            System.out.println("Impossivel inicar a instancia da "
                    + "classe de autenticacao devido a acesso ilegal");
        }
        
        return autenticação;
    }
    
}
