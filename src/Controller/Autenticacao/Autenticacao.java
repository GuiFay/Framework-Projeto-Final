/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Autenticacao;

import Model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public abstract class Autenticacao {
    
    public static final int  XML = 1;
    public static final int  TXT = 2;
    
    Usuario usuarioLogado = null;
    
    //frozen
    public boolean CadastrarUsuario(Usuario u) throws Exception{
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo();
            cadastrar(u, usuarios);
            serealizarArquivo(usuarios);
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    }
    public boolean RemoverUsuario(Usuario u) throws Exception{
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo();
            remover(u, usuarios);
            serealizarArquivo(usuarios);
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
        
    }
    public boolean AlterarUsuario(Usuario uAntigo, Usuario uNovo) throws Exception {
        
        boolean retorno = true; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            usuarios = deserealizarArquivo();
            alterar(uAntigo, uNovo, usuarios);
            serealizarArquivo(usuarios);
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
            usuarios = deserealizarArquivo();
            retorno = recuperar(u, usuarios);
            serealizarArquivo(usuarios);
            //HOsts SPOTS -- END
        } catch (Exception e){
            throw e;
        }
        
        return retorno;
    }
    
    public Usuario AutenticarUsuario(Usuario u) throws Exception{

        Usuario retorno = null; 
        ArrayList<Usuario> usuarios = null;
        
        try {
            //HOsts SPOTS -- Beguin
            usuarios = deserealizarArquivo();
            
            retorno = autenticar(u, usuarios);
            usuarioLogado = retorno;
            
            serealizarArquivo(usuarios);
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
    protected abstract ArrayList deserealizarArquivo() throws Exception;
    protected abstract boolean serealizarArquivo(ArrayList<Usuario> usuarios) throws Exception;
    
    //hot
    protected abstract Usuario autenticar(Usuario usuario, ArrayList usuarios);
    protected abstract boolean alterar(Usuario usuarioAntigo, Usuario usuarioNovo, ArrayList usuarios);
    protected abstract boolean remover(Usuario usuario, ArrayList usuarios);
    protected abstract boolean cadastrar(Usuario usuario, ArrayList usuarios);
    protected abstract Usuario recuperar(Usuario usuario, ArrayList usuarios);
    
}
