/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import java.io.File;

/**
 *
 * @author Guilherme
 */
class AutenticadorTXT implements Autenticacao {

    public AutenticadorTXT() {
    }

    @Override
    public void cadastrarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet TXT.");
    }

    @Override
    public void RemoverUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet TXT.");
    }

    @Override
    public void AlterarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet TXT.");
    }

    @Override
    public int AutenticarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet TXT.");
    }
}