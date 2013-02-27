/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Autenticacao;

import Model.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public class AutenticadorTXT extends Autenticacao {

    @Override
    protected ArrayList deserealizarArquivo(String caminho) throws Exception {
        FileInputStream fis = new FileInputStream(caminho);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList usuarios = (ArrayList) ois.readObject();
        ois.close();
        fis.close();
        return usuarios;
    }

    @Override
    protected boolean serealizarArquivo(String caminho,ArrayList<Usuario> usuarios) throws Exception {
        FileOutputStream fos;
        fos = new FileOutputStream(caminho);
        ObjectOutputStream oos;
        oos = new ObjectOutputStream(fos);
        oos.writeObject(usuarios);
        oos.flush();
        oos.close();
        return true;
    }
    
    @Override
    protected Usuario autenticar(Usuario usuario, ArrayList usuarios) {
        for (Usuario u : (ArrayList<Usuario>)usuarios) {
            if (u.login.equals(usuario.login) && u.senha.equals(usuario.senha)) {
                return u;
            }
        }
        return null;
    }

    @Override
    protected boolean alterar(Usuario usuarioAntigo, Usuario usuarioNovo, ArrayList usuarios) {
        boolean retorno = remover(usuarioAntigo, usuarios);
        retorno = retorno && cadastrar(usuarioNovo, usuarios);
        return retorno;
    }

    @Override
    protected boolean remover(Usuario usuario, ArrayList usuarios) {
        for (Usuario u : ( ArrayList<Usuario> ) usuarios ) {
            //FIXME Isso daqui pode ser melhorado
            if ( u.login.equals(usuario.login) ) {
                usuarios.remove(u);
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean cadastrar(Usuario usuario, ArrayList usuarios) {
        usuarios.add(usuario);
        return true;
    }

    @Override
    protected Usuario recuperar(Usuario usuario, ArrayList usuarios) {
        for(int i = 0; i < usuarios.size(); i++){
            if ( ((Usuario)usuarios.get(i)).login.equals(usuario.login) ) {
                return (Usuario) usuarios.get(i);
            }
        }
        return null ;
    }

}