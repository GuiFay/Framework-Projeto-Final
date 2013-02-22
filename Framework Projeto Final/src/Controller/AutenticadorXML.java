/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;

/**
 *
 * @author Guilherme
 */
class AutenticadorXML implements Autenticacao {

    public AutenticadorXML() {
    }

    @Override
    public void cadastrarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet XML.");
    }

    @Override
    public void RemoverUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet XML.");
    }

    @Override
    public void AlterarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet XML.");
    }

    @Override
    public int AutenticarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet XML.");
    }
   
}
