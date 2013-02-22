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
public interface Autenticacao {
    
    public static int  XML = 1;
    public static int  TXT = 2;
    
    public void cadastrarUsuario(Usuario u);
    public void RemoverUsuario(Usuario u);
    public void AlterarUsuario(Usuario u);
    
    public int AutenticarUsuario(Usuario u); 
}
