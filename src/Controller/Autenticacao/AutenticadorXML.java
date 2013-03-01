/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Autenticacao;

import Model.Usuario;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class AutenticadorXML extends Autenticacao {

    @Override
    protected ArrayList deserealizarArquivo(String caminho) throws Exception {
        
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        File file = new File(caminho + ".xml");
        
        XStream xstream = new XStream(new DomDriver());        
        ObjectInputStream in = xstream.createObjectInputStream(new FileInputStream(file));
        xstream.alias("usuarios", Usuarios.class);
        xstream.alias("usuario", Usuario.class);
        xstream.alias("size", Integer.class);
        
        Integer size = (Integer) in.readObject();
        
        for(int i = 0; i < size; i++){
            usuarios.add((Usuario) in.readObject());
        }

        in.close();
        
        
        //Object fromXML = xstream.fromXML(file);
        //usuarios = (ArrayList<Usuario>) xstream.fromXML(file,usuarios);
        return usuarios;
    }

    @Override
    protected boolean serealizarArquivo(String caminho, ArrayList<Usuario> usuarios) throws Exception {
        File file = new File(caminho + ".xml");
        XStream xstream = new XStream(new DomDriver());

        AutenticadorXML.Usuarios _usuarios_ = new AutenticadorXML.Usuarios();
        _usuarios_.set(usuarios);
        
        xstream.alias("usuario", Usuario.class );
        xstream.alias("usuarios", AutenticadorXML.Usuarios.class );
        xstream.addImplicitCollection(AutenticadorXML.Usuarios.class, "vect");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não foi gerado com sucesso");
        }
        xstream.toXML(_usuarios_, fos);

        return true;
    }

    @Override
    protected Usuario autenticar(Usuario usuario, ArrayList usuarios) {
        for (Usuario u : (ArrayList<Usuario>) usuarios) {
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
        for (Usuario u : (ArrayList<Usuario>) usuarios) {
            //FIXME Isso daqui pode ser melhorado
            if (u.login.equals(usuario.login)) {
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
        for (int i = 0; i < usuarios.size(); i++) {
            if (((Usuario) usuarios.get(i)).login.equals(usuario.login)) {
                return (Usuario) usuarios.get(i);
            }
        }
        return null;
    }
    
    
    public static class Usuarios {
        
        int size;
        ArrayList<Usuario> vect;
        
        public Usuarios() {
            vect = new ArrayList<Usuario>();
            size = 0;
        }

        public void add(Usuario u){
            size++;
            vect.add(u);
        }
        
        public void set(ArrayList<Usuario> vet){
            vect = vet;
            size = vet.size();
        }
        
    }
    
}
