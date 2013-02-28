/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Autenticacao;

import Core.ComunicacaoFacade;
import Model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public abstract class Autenticacao {
    
    public static final int  XML = 1;
    public static final int  TXT = 2;
    
    public static final String  caminho_cadastro = "Cadastro2";
    public static final String  caminho_lista = "ListaOnline";
    
    //ComunicacaoFacade com = new ComunicacaoFacade();
    
    Usuario usuarioLogado = null;
    
    //frozen
    public boolean CadastrarUsuario(Usuario u) throws Exception{
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo(caminho_cadastro);
            cadastrar(u, usuarios);
            serealizarArquivo(caminho_cadastro,usuarios);
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    }
    
    public boolean listarUsuarioComoLogado(Usuario u) throws Exception{
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo(caminho_lista);
            cadastrar(u, usuarios);
            serealizarArquivo(caminho_lista,usuarios);
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    }
    
    public boolean RemoverUsuario(Usuario u) throws Exception{
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo(caminho_cadastro);
            remover(u, usuarios);
            serealizarArquivo(caminho_cadastro,usuarios);
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    }
    public boolean AlterarUsuario(Usuario uAntigo, Usuario uNovo) throws Exception {
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo(caminho_cadastro);
            alterar(uAntigo, uNovo, usuarios);
            serealizarArquivo(caminho_cadastro,usuarios);
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    }
    
    public Usuario RecuperarUsuario (Usuario u ) throws Exception{
        Usuario retorno = null; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            //HOsts SPOTS -- Beguin
            usuarios = deserealizarArquivo(caminho_cadastro);
            retorno = recuperar(u, usuarios);
            serealizarArquivo(caminho_cadastro,usuarios);
            //HOsts SPOTS -- END
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
    }
    
    public Usuario AutenticarUsuario(Usuario u) throws Exception{

        Usuario retorno = null; 
        ArrayList<Usuario> usuarios = null;
        ArrayList<Usuario> usuarios_online = null;
        
        try {
            //HOsts SPOTS -- Beguin
            usuarios = deserealizarArquivo(caminho_cadastro);
            retorno = autenticar(u, usuarios);
            usuarioLogado = retorno;
            serealizarArquivo(caminho_cadastro,usuarios);
            
            ///////Isso deveria ser feito remotamente/////////
//            com.atualizarLista();
           /*
            deserealizarArquivo(caminho_lista);
            usuarios_online = new ArrayList<Usuario>();
            retorno.setSenha("");
            cadastrar(retorno, usuarios_online);
            serealizarArquivo(caminho_lista, usuarios_online);
            */ 
            //////////////////////////////////////////////////
            
            //HOsts SPOTS -- END
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    } 
    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
    //hot
    protected abstract ArrayList deserealizarArquivo(String caminho) throws Exception;
    protected abstract boolean serealizarArquivo(String caminho,ArrayList<Usuario> usuarios) throws Exception;
    
    //hot
    protected abstract Usuario autenticar(Usuario usuario, ArrayList usuarios);
    protected abstract boolean alterar(Usuario usuarioAntigo, Usuario usuarioNovo, ArrayList usuarios);
    protected abstract boolean remover(Usuario usuario, ArrayList usuarios);
    protected abstract boolean cadastrar(Usuario usuario, ArrayList usuarios);
    protected abstract Usuario recuperar(Usuario usuario, ArrayList usuarios);
    
}
