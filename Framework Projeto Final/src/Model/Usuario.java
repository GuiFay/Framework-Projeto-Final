/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public class Usuario implements Serializable{
    
    public String nome;
    public String sobrenome;
    public String login;
    public String senha;

    public Usuario(String nome,String sobrenome, String login, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", sobrenome=" + sobrenome + ", login=" + login + ", senha=" + senha + '}';
    }
    
    
    
    
}
