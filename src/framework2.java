/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Guilherme
 */
public class framework2 {
    
    public static final USUARIOJAEXISTENTE = 2;
    public static final USUARIOCADASTRADO = 1;
    
    //lista de usuarios cadastrados do framework
    private static Usuario listaCadastrados;
    private static int numCadastrados;
    
    public static int addUser(Usuario user){
    Usuario temp = listaCadastrados;
    //percorre lista de usuarios
    while(temp != null){
        if(temp.getNome().equals(user.getNome()))//encontrando um elemento igual
            break;//interrompe a repetição
        }
    
    if(temp != null)//interrompendo a repeticao no meio da lista = aluno ja existe
        return USUARIOJAEXISTENTE;
    else 
    }
    
    
    
    
}
