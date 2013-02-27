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
public class AutenticadorXML extends Autenticacao {

    @Override
    protected ArrayList deserealizarArquivo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean serealizarArquivo(ArrayList<Usuario> usuarios) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Usuario autenticar(Usuario usuario, ArrayList usuarios) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean alterar(Usuario usuarioAntigo, Usuario usuarioNovo, ArrayList usuarios) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean remover(Usuario usuario, ArrayList usuarios) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean cadastrar(Usuario usuario, ArrayList usuarios) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Usuario recuperar(Usuario usuario, ArrayList usuarios) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
   
}
